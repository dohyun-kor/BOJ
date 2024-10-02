import java.io.*;
import java.util.*;


public class Main
{
    static int N,M,K;
    static long djk[][];
    static ArrayList<node> nodeList[];
    
    static class node implements Comparable<node>{
        int pos;
        long cost;
        int count;
        
        node(int pos, long cost, int count){
            this.pos = pos;
            this.cost = cost;
            this.count = count;
        }
        
        @Override
        public int compareTo(node n){
            return Long.compare(this.cost, n.cost);
        }
    }
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		djk = new long[K+1][N+1];
		for(int i=0; i<K+1; i++){
		    Arrays.fill(djk[i], Long.MAX_VALUE);
		}
		nodeList = new ArrayList[N+1];
		for(int i=0; i<N+1; i++){
		    nodeList[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++){
		    st = new StringTokenizer(br.readLine());
		    int start = Integer.parseInt(st.nextToken());
		    int end = Integer.parseInt(st.nextToken());
		    long cost = Long.parseLong(st.nextToken());
		    
		    nodeList[start].add(new node(end, cost, 0));
		    nodeList[start].add(new node(end, 0, 1));
		    nodeList[end].add(new node(start, cost, 0));
		    nodeList[end].add(new node(start, 0, 1));
		}
		
		dijkstra();
		long result = Long.MAX_VALUE;
        for(int i=0; i<K+1; i++){
            if(result > djk[i][N]) result = djk[i][N];
        }
		System.out.println(result);
		
	}
	
	static void dijkstra(){
	    boolean visited[][] = new boolean[N+1][K+1];
	    PriorityQueue<node> pq = new PriorityQueue<>();
	    pq.add(new node(1, 0, 0));

	    djk[0][1] = 0;
	    
	    while(!pq.isEmpty()){
	        node curnode = pq.poll();
	        if(visited[curnode.pos][curnode.count]) continue;
	        visited[curnode.pos][curnode.count] = true;
	        
	        for(node nextnode : nodeList[curnode.pos]){
	            if(curnode.count + nextnode.count > K) continue;
	            if(djk[curnode.count + nextnode.count][nextnode.pos] > curnode.cost + nextnode.cost){
	                djk[curnode.count + nextnode.count][nextnode.pos] = curnode.cost + nextnode.cost;
	                pq.add(new node(nextnode.pos, djk[curnode.count + nextnode.count][nextnode.pos], curnode.count + nextnode.count));
	            }
	        }
	    }
	}
}