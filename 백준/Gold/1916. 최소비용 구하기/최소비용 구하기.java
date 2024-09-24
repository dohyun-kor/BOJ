import java.io.*;
import java.util.*;

public class Main
{
	static class town implements Comparable<town> {
		int pos,cost;
		town(int pos, int cost) {
			this.pos = pos;
			this.cost = cost;
		}

		@Override
		public int compareTo(town t1) {
			return this.cost-t1.cost;
		}
	}

	static int djk[];
	static ArrayList<town> townList[];
	static boolean visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		townList = new ArrayList[N+1];
		for(int i=0; i<N+1; i++) {
			townList[i] = new ArrayList<>();
		}

		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			townList[start].add(new town(end, cost));
		}
		st = new StringTokenizer(br.readLine());
		int targetstart = Integer.parseInt(st.nextToken());
		int targetend = Integer.parseInt(st.nextToken());

		djk = new int[N+1];
		visited = new boolean[N+1];
		Arrays.fill(djk, Integer.MAX_VALUE);
		find(targetstart);
        
        // System.out.println(Arrays.toString(djk));
		System.out.println(djk[targetend]);
	}

	static void find(int targetstart) {
		PriorityQueue<town> pq = new PriorityQueue<>();
		pq.add(new town(targetstart, 0));
		djk[targetstart] = 0;

		while(!pq.isEmpty()) {
			town t = pq.poll();
			int pos = t.pos;
			int cost = t.cost;
			
			if(visited[pos]) continue;
            visited[pos] = true;
            
			for(town nextnode : townList[pos]) {
				if(djk[nextnode.pos] > cost + nextnode.cost) {
					djk[nextnode.pos] = cost + nextnode.cost;
					pq.add(new town(nextnode.pos, djk[nextnode.pos]));
				}
			}
		}
	}
}
