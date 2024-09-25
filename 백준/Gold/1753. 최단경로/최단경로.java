import java.io.*;
import java.util.*;

public class Main
{
    static class node implements Comparable<node>{
        int pos, cost;
        node(int pos, int cost){
            this.pos = pos;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(node n1){
            return this.cost-n1.cost;
        }
    }
    
        static ArrayList<node> nodeList[];
        static int djklist[];
        static int V,E;
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(br.readLine());
		nodeList = new ArrayList[V+1];
		djklist = new int[V+1];
		
		Arrays.fill(djklist, Integer.MAX_VALUE);
		
		for(int i=0; i<V+1; i++){
		    nodeList[i] = new ArrayList<>();
		}
		
		for(int i=0; i<E; i++){
		    st = new StringTokenizer(br.readLine());
		    int start = Integer.parseInt(st.nextToken());
		    int end = Integer.parseInt(st.nextToken());
		    int cost = Integer.parseInt(st.nextToken());
		    
		    nodeList[start].add(new node(end, cost));
		}
		
		djk(s);
		for(int i=1; i<V+1; i++){
		    if(djklist[i] == Integer.MAX_VALUE){
		        System.out.println("INF");
		        continue;
		    }
		    System.out.println(djklist[i]);
		}
	}
	
	
	static void djk(int start){
	    boolean visited[] = new boolean[V+1];
	    PriorityQueue<node> pq = new PriorityQueue<>();
	    pq.add(new node(start,0));
	    djklist[start] = 0;
	    
	    while(!pq.isEmpty()){
	        node curnode = pq.poll();
	        if(visited[curnode.pos]) continue;
	        visited[curnode.pos] = true;
	        
	        for(node next : nodeList[curnode.pos]){
	            if(djklist[next.pos] > curnode.cost + next.cost){
	                djklist[next.pos] = curnode.cost + next.cost;
	                pq.add(new node(next.pos , djklist[next.pos]));
	            }
	        }
	    }
	    
	}
	
}