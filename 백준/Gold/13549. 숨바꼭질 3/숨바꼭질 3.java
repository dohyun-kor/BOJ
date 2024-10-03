import java.io.*;
import java.util.*;

public class Main
{
	static int N,K,result,djk[];

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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		djk = new int[100001];
		Arrays.fill(djk, Integer.MAX_VALUE);
		result = Integer.MAX_VALUE;
		if(N<K) {
			dijkstra();
			result = djk[K];
		}
		else if(N>K) result = N-K;
		else result = 0;

		System.out.println(result);
	}

	static void dijkstra() {
		boolean visited[] = new boolean[100001];
		PriorityQueue<node> pq = new PriorityQueue<>();
		pq.add(new node(N,0));
		djk[N] = 0;
		while(!pq.isEmpty()) {
			node cur = pq.poll();
			int pos = cur.pos;
			int count = cur.cost;
			if(pos > K) {
				djk[K] = Math.min(djk[K], count+pos-K);
				continue;
			}
			if(pos == K) {
				return;
			}
			if(visited[pos]) continue;
			visited[pos] = true;
			if(pos*2 <= 100000) {
				if(djk[pos*2] > count) {
					djk[pos*2] = count;
					pq.add(new node(pos*2, djk[pos*2]));
				}
			}
			if(pos+1 <= 100000) {
				if(djk[pos+1] > count+1) {
					djk[pos+1] = count+1;
					pq.add(new node(pos+1, djk[pos+1]));
				}
			}
			if(pos-1 >= 0) {
				if(djk[pos-1] > count+1) {
					djk[pos-1] = count+1;
					pq.add(new node(pos-1, djk[pos-1]));
				}
			}


		}
	}
}