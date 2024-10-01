import java.io.*;
import java.util.*;

public class Main
{
    static int N,M;
    static long djk[];
    static ArrayList<node> nodeList[];
    
    static class node implements Comparable<node>{
        int pos;
        long cost;
        int idx;
        node(int pos, long cost, int idx){
            this.pos = pos;
            this.cost = cost;
            this.idx = idx;
        }
        
        @Override
        public int compareTo(node n){
            return Long.compare(this.cost,n.cost);
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        djk = new long[N+1];
        Arrays.fill(djk, Long.MAX_VALUE);
        
        nodeList = new ArrayList[N+1];
        for(int i=0; i<N+1; i++){
            nodeList[i] = new ArrayList<>();
        }
        
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = i+1;
            
            nodeList[start].add(new node(end,cost ,cost));
            nodeList[end].add(new node(start,cost ,cost));
        }
        dijkstra();
        System.out.println(djk[N]);
    }
    
    static void dijkstra(){
        boolean visited[] = new boolean[N+1];
        PriorityQueue<node> pq = new PriorityQueue<>();
        pq.add(new node(1, 0, 0));
        djk[1] = 0;
        
        while(!pq.isEmpty()){
            node curnode = pq.poll();
            if(visited[curnode.pos]) continue;
            visited[curnode.pos] = true;
            
            for(node nextnode : nodeList[curnode.pos]){
                int dist = 0;
                if(nextnode.idx - curnode.idx > 0){
                    dist = nextnode.idx - curnode.idx;
                }
                else{
                    dist = M - curnode.idx + nextnode.idx;
                }
                if(djk[nextnode.pos] > curnode.cost + dist){
                    djk[nextnode.pos] = curnode.cost + dist;
                    pq.add(new node(nextnode.pos, djk[nextnode.pos], nextnode.idx));
                }
            }
        }
        
        
    }
}