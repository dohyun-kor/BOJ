import java.io.*;
import java.util.*;

public class Main
{
	static int N,M,W;
	static ArrayList<node> nodeList;
	static String result;

	static class node {
		int x,y,cost;
		node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<T+1; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			nodeList = new ArrayList<>();

			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				nodeList.add(new node(start,end,cost));
				nodeList.add(new node(end,start,cost));
			}

			for(int i=0; i<W; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				nodeList.add(new node(start,end,-cost));
			}
			result = "NO";
			for(int i=1; i<N+1; i++){
			    bellman(i);
			    if(result == "YES") break;
			}
            
			System.out.println(result);
		}
	}
	
	static void bellman(int n){
	    int dist[] = new int[N+1];
	    Arrays.fill(dist, Integer.MAX_VALUE);
	    dist[n] = 0;
	    for(int i=1; i<=N; i++){
	        boolean update = false;
	        for(node curnode : nodeList){
	            if(dist[curnode.x] == Integer.MAX_VALUE) continue;
	            if(dist[curnode.y] > dist[curnode.x] + curnode.cost){
	                dist[curnode.y] = dist[curnode.x] + curnode.cost;
	                update = true;
	                if(i==N){
	                    result = "YES";
	                    return;
	                }
	            }
	        }
	        if(update == false) return;
	    }
	}
}