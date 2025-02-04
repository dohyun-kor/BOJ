import java.io.*;
import java.util.*;

public class Main{
    
    static int N,P,K;
    static ArrayList<graph> g[];
    
    static class graph{
        int next,cost,count;
        
        graph(int next, int cost){
            this.next = next;
            this.cost = cost;
            this.count = 0;
        }
        
        graph(int next, int cost, int count){
            this.next = next;
            this.cost = cost;
            this.count = count;
        }
    }
    
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        g = new ArrayList[N+1];
        
        for(int i=0; i<N+1; i++){
            g[i] = new ArrayList<>();
        }
        
        for(int i=0; i<P; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            g[start].add(new graph(end, cost));
            g[end].add(new graph(start, cost));
        }
        
        int start = 0;
        int end = 1_000_000;
        int result = -1;
        while(start<=end){
            int mid = (start+end)/2;
            if(dijk(mid)){
                result = mid;
                end = mid-1;
            }else{
                start = mid+1;
            }
        }
        System.out.println(result);
    }
    
    static boolean dijk(int mid){
        Queue<graph> q = new LinkedList<>();
        boolean visited[][] = new boolean[N+1][K+1];
        q.add(new graph(1,0));
        while(!q.isEmpty()){
            graph curgraph = q.poll();
            int curpoint = curgraph.next;
            int count = curgraph.count;
            
            if(curpoint == N) return true;
            
            if(visited[curpoint][count]) continue;
            visited[curpoint][count] = true;
            
            for(graph nextgraph : g[curpoint]){
                if(nextgraph.cost > mid){
                    if(count+1 <= K){
                        q.add(new graph(nextgraph.next,0, count+1));
                    }
                }else{
                    q.add(new graph(nextgraph.next,0, count));
                }
            }
        }
        return false;
    }
}