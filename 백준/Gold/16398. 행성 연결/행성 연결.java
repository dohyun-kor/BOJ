import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main
{
	static ArrayList<flow> flowList[];
	static int N;
	static long result;
		
	static class flow implements Comparable<flow>{
		int to,cost;
		public flow(int to, int cost) {
			this.to= to;
			this.cost = cost;
		}
		
	
		@Override
		public int compareTo(flow f) {
			return this.cost - f.cost;
		}
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		flowList = new ArrayList[N+1];
		for(int i=1; i<N+1; i++) {
			flowList[i] = new ArrayList<>();
		}
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<N+1; j++) {
				int cost = Integer.parseInt(st.nextToken());
				flowList[i].add(new flow(j,cost));
			}
		}
		result = 0;
		prim(1);
		
		System.out.println(result);
		
	}
	
	static void prim(int start) {
		PriorityQueue<flow> pq = new PriorityQueue<>();
		boolean visited[] = new boolean[N+1];
		pq.add(new flow(start,0));
		
		while(!pq.isEmpty()) {
			flow f = pq.poll();
			int node = f.to;
			int cost = f.cost;
			if(visited[node]) continue;
			visited[node] = true;
			result += cost;
			
			for(flow next : flowList[node]) {
				if(!visited[next.to]) {
					pq.add(next);
				}
			}
		}
	}

}