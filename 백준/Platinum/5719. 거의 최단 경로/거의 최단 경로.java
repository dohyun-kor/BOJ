import java.io.*;
import java.util.*;

public class Main
{
	static int N,M,S,D,djk[],djk2[];
	static ArrayList<node> nodeList[];
	static HashSet<int []> paths[];

	static class node implements Comparable<node> {
		int pos,cost;

		node(int pos, int cost) {
			this.pos = pos;
			this.cost = cost;
		}

		@Override
		public int compareTo(node n) {
			return this.cost - n.cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while(true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if(N==0 && M==0) return;

			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			nodeList = new ArrayList[N];
			paths = new HashSet[N];
			for(int i=0; i<N; i++) {
				nodeList[i] = new ArrayList<>();
				paths[i] = new HashSet<>();
			}

			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				nodeList[start].add(new node(end, cost));
			}
			djk = new int[N];
			Arrays.fill(djk, Integer.MAX_VALUE);
			dijkstra();

			djk2 = new int[N];
			Arrays.fill(djk2, Integer.MAX_VALUE);
			dijkstra2();

			if(djk2[D] == Integer.MAX_VALUE) {
				System.out.println(-1);
			} else {
				System.out.println(djk2[D]);
			}
		}
	}

	static void dijkstra() {
		PriorityQueue<node> q = new PriorityQueue<>();
		boolean visited[] = new boolean[N];
		q.add(new node(S,0));

		while(!q.isEmpty()) {
			node curnode = q.poll();
			if(visited[curnode.pos]) continue;
			visited[curnode.pos] = true;
			for(node nextnode : nodeList[curnode.pos]) {
				if(djk[nextnode.pos] > curnode.cost + nextnode.cost) {
					djk[nextnode.pos] = curnode.cost + nextnode.cost;
					paths[nextnode.pos].clear();
					for(int path[] : paths[curnode.pos]) {
						paths[nextnode.pos].add(path);
					}
					paths[nextnode.pos].add(new int[] {curnode.pos, nextnode.pos});
					q.add(new node(nextnode.pos, djk[nextnode.pos]));
				}
				else if(djk[nextnode.pos] == curnode.cost + nextnode.cost) {
					for(int path[] : paths[curnode.pos]) {
						paths[nextnode.pos].add(path);
					}
					paths[nextnode.pos].add(new int[] {curnode.pos, nextnode.pos});
				}
			}
		}
	}

	static void dijkstra2() {
		PriorityQueue<node> q = new PriorityQueue<>();
		boolean visited[] = new boolean[N];
		boolean flag = true;
		q.add(new node(S,0));
		
		while(!q.isEmpty()) {
			node curnode = q.poll();
			if(visited[curnode.pos]) continue;
			visited[curnode.pos] = true;
			for(node nextnode : nodeList[curnode.pos]) {
			    flag = true;
				for(int used[] : paths[D]) {
					if(Arrays.equals(used, new int[] {curnode.pos, nextnode.pos})) {
						flag = false;
					}
				}
				if(flag) {
					if(djk2[nextnode.pos] > curnode.cost + nextnode.cost) {
						djk2[nextnode.pos] = curnode.cost + nextnode.cost;
						q.add(new node(nextnode.pos, djk2[nextnode.pos]));
					}
				}
			}
		}
	}
}