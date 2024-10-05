import java.io.*;
import java.util.*;

public class Main
{
	static int N,W[],info[][];
	static ArrayList<node> nodeList[];
	static long result;
	static boolean visited[];
	static PriorityQueue<node> pq;

	static class node implements Comparable<node> {
		int pos, cost;
		node(int pos, int cost) {
			this.pos = pos;
			this.cost = cost;
		}

		@Override
		public int compareTo(node n) {
			return this.cost - n.cost;
		}
	}

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		W = new int[N+1];
		nodeList = new ArrayList[N+1];
		for(int i=0; i<N+1; i++) {
			nodeList[i] = new ArrayList<>();
		}
		for(int i=1; i<N+1; i++) {
			W[i] = Integer.parseInt(br.readLine());
			nodeList[0].add(new node(i, W[i]));
		}
		info = new int[N+1][N+1];
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<N+1; j++) {
				info[i][j] = Integer.parseInt(st.nextToken());
				if(i==j) continue;
				nodeList[i].add(new node(j, info[i][j]));
			}
		}
        result = 0;
		prim();

		System.out.println(result);
	}

	static void prim() {
		visited = new boolean[N+1];
		pq = new PriorityQueue<>();
		pq.add(new node(0,0));
		while(!pq.isEmpty()) {
			node curnode = pq.poll();
			if(visited[curnode.pos]) continue;
			visited[curnode.pos] = true;
			if(curnode.cost > W[curnode.pos]) {
				result += W[curnode.pos];
			}
			else {
				result += curnode.cost;
			}
			for(node nextnode : nodeList[curnode.pos]) {
				pq.add(new node(nextnode.pos, nextnode.cost));
			}
		}
	}
}