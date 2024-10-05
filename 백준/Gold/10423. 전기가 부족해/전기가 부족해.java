import java.io.*;
import java.util.*;

public class Main
{
    static int N,M,K,power[],result;
    static ArrayList<node> nodeList[];
    
    static class node implements Comparable<node>{
        int pos,cost;
        node(int pos, int cost){
            this.pos = pos;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(node n){
            return this.cost-n.cost;
        }
    }
    
	public static void main(String[] args)throws IOException{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
	    st = new StringTokenizer(br.readLine());
	    N = Integer.parseInt(st.nextToken());
	    M = Integer.parseInt(st.nextToken());
	    K = Integer.parseInt(st.nextToken());

        power = new int[K];
	    st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++){
            power[i] = Integer.parseInt(st.nextToken());
        }
        
        nodeList = new ArrayList[N+1];
        for(int i=0; i<N+1; i++){
            nodeList[i] = new ArrayList<>();
        }
        
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            
            nodeList[start].add(new node(end, cost));
            nodeList[end].add(new node(start, cost));
        }
        result = 0;
        prim();
        System.out.println(result);
	}
	
	static void prim(){
	    PriorityQueue<node> pq = new PriorityQueue<>();
	    boolean visited[] = new boolean[N+1];
	    for(int i=0; i<K; i++){
	        visited[power[i]] = true;
	        for(node nextnode : nodeList[power[i]]){
	            pq.add(new node(nextnode.pos, nextnode.cost));
	        }
	    }
	    while(!pq.isEmpty()){
	        node curnode = pq.poll();
	        if(visited[curnode.pos]) continue;
	        visited[curnode.pos] = true;
	        result += curnode.cost;
	        for(node nextnode : nodeList[curnode.pos]){
	            pq.add(new node(nextnode.pos, nextnode.cost));
	        }
	    }
	}
}