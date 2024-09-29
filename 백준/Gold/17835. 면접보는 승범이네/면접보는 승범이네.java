import java.io.*;
import java.util.*;

public class Main
{
    static class node implements Comparable<node>{
        int pos;
        long cost;
        node(int pos, long cost){
            this.pos = pos;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(node n){
            return (int)(this.cost - n.cost);
        }
    }
    
    static int N,M,K;
    static ArrayList<node> nodeList[];
    static long djk[];
    static long result[];
    static int interview[];
    static boolean visited[];
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		nodeList = new ArrayList[N+1];
		result = new long[N+1];
		for(int i=0; i<N+1; i++){
		    nodeList[i] = new ArrayList<>();
		}
        
        Arrays.fill(result, Long.MAX_VALUE);
		
		
		for(int i=0; i<M; i++){
		    st = new StringTokenizer(br.readLine());
		    int start = Integer.parseInt(st.nextToken());
		    int end = Integer.parseInt(st.nextToken());
		    int cost = Integer.parseInt(st.nextToken());
		    nodeList[end].add(new node(start, cost));
		}
		interview = new int[K];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++){
		    interview[i] = Integer.parseInt(st.nextToken());
		    result[interview[i]] = 0;
		}
        dijkstra();

		int max_i = 0;
		long max_v = 0;
		for(int i=1; i<N+1; i++){
		    if(max_v < result[i]){
		        max_v = result[i];
		        max_i = i;
		    }
		}
		System.out.println(max_i);
		System.out.println(max_v);
	}
	
	static void dijkstra(){
		djk = new long[N+1];
	    Arrays.fill(djk, Long.MAX_VALUE);
	    visited = new boolean[N+1];
	    PriorityQueue<node> pq = new PriorityQueue<>();
	    for(int num: interview){
	        pq.add(new node(num, 0));
	        djk[num] = 0;
	    }
	    
	    
	    while(!pq.isEmpty()){
	        node curnode = pq.poll();
	        if(visited[curnode.pos]) continue;
	        if(result[curnode.pos] < curnode.cost) continue;
	        visited[curnode.pos] = true;
	        
	        for(node nextnode: nodeList[curnode.pos]){
	            if(djk[nextnode.pos] > curnode.cost + nextnode.cost){
	                djk[nextnode.pos] = curnode.cost + nextnode.cost;
	                if(result[nextnode.pos] > djk[nextnode.pos]){
	                    result[nextnode.pos] = djk[nextnode.pos];
	                }
	                else if(result[nextnode.pos] < djk[nextnode.pos]) continue;
	                pq.add(new node(nextnode.pos, djk[nextnode.pos]));
	            }
	        }
	    }
	}
	
}