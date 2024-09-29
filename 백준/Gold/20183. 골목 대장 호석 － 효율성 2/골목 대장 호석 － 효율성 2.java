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
            return Long.compare(this.cost, n.cost);
        }
    }
    
    static int N,M,A,B;
    static long C;
    static ArrayList<node> nodeList[];
    static long djk[];
    static long result;
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Long.parseLong(st.nextToken());
		
        result = Long.MAX_VALUE;
		nodeList = new ArrayList[N+1];
		for(int i=0; i<N+1; i++){
		    nodeList[i] = new ArrayList<>();
		}
		
		long e = 0;
		for(int i=0; i<M; i++){
		    st = new StringTokenizer(br.readLine());
		    int start = Integer.parseInt(st.nextToken());
		    int end = Integer.parseInt(st.nextToken());
		    long cost = Long.parseLong(st.nextToken());
		    e = Math.max(e, cost);
		    nodeList[start].add(new node(end, cost));
		    nodeList[end].add(new node(start, cost));
		}
		
		long s = 0;
		while(s<=e){
		    long mid = (long)(s+e)/2;
		    if(go(mid)){
		        result = mid;
		        e = mid-1;
		    }
		    else{
		        s = mid+1;
		    }
		}
		if(result == Long.MAX_VALUE){
		    System.out.print(-1);
		}
		else{
		    System.out.print(result);
		}
		
	}
	
	static boolean go(long mid){
	    djk = new long[N+1];
	    Arrays.fill(djk, Long.MAX_VALUE);
	    
	    boolean visited[] = new boolean[N+1];
	    PriorityQueue<node> pq = new PriorityQueue<>();
	    pq.add(new node(A,0));
	    djk[A] = 0;
	    
	    while(!pq.isEmpty()){
	        node curnode = pq.poll();
	        
	        if(visited[curnode.pos]) continue;
	        visited[curnode.pos] = true;
	        for(node nextnode : nodeList[curnode.pos]){
	            if(nextnode.cost > mid) continue;
	            if(curnode.cost + nextnode.cost > C) continue;
	            if(nextnode.pos == B) return true;
	            if(djk[nextnode.pos] > curnode.cost + nextnode.cost){
	                djk[nextnode.pos] = curnode.cost + nextnode.cost;
	                pq.add(new node(nextnode.pos, djk[nextnode.pos]));
	            }
	        }
	    }
	    return false;
	}
}