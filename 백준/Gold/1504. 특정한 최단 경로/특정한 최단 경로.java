import java.io.*;
import java.util.*;

public class Main
{
    static class node implements Comparable<node>{
        int pos,cost;
        node(int pos, int cost){
            this.pos = pos;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(node n){
            return this.cost - n.cost;
        }
    }
    
    static ArrayList<node> nodeList[];
    static int N,E;
    static int targetstart,targetend;
    static int djk[][];
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int INF = Integer.MAX_VALUE;
        
        
        djk = new int[N+1][N+1];
        
        for(int i=0; i<N+1; i++){
            Arrays.fill(djk[i], INF);
        }
        
        nodeList = new ArrayList[N+1];
        for(int i=0; i<N+1; i++){
            nodeList[i] = new ArrayList<>();
        }
        
        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            nodeList[start].add(new node(end, cost));
            nodeList[end].add(new node(start, cost));
        }
        
        st = new StringTokenizer(br.readLine());
        int targetone = Integer.parseInt(st.nextToken());
        int targettwo = Integer.parseInt(st.nextToken());
        
        dijkstra(1);
        dijkstra(targetone);
        dijkstra(targettwo);
        
        // for(int i=0; i<9; i++){
        //     System.out.println(Arrays.toString(djk[i]));
        // }
        
        int result;
        result = Math.min(calculate(1, targetone, targettwo) , calculate(1, targettwo, targetone));
        if(result == Integer.MAX_VALUE) result = -1;
        System.out.println(result);
        
    }
    
    static void dijkstra(int start){
        boolean visited[] = new boolean[N+1];
        PriorityQueue<node> pq = new PriorityQueue<>();
        pq.add(new node(start,0));
        djk[start][start] = 0;
        
        while(!pq.isEmpty()){
            node cur = pq.poll();
            if(visited[cur.pos]) continue;
            visited[cur.pos] = true;
            
            for(node next : nodeList[cur.pos]){
                if(djk[start][next.pos] > cur.cost + next.cost){
                    djk[start][next.pos] = cur.cost + next.cost;
                    pq.add(new node(next.pos, djk[start][next.pos]));
                }
            }
        }
    }
    
    static int calculate(int start, int one, int two){
        int result =0;
        if(djk[start][one] == Integer.MAX_VALUE || djk[one][two] == Integer.MAX_VALUE || djk[two][N] == Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }
        else{
            return djk[start][one] + djk[one][two] + djk[two][N];
        }
    }
}