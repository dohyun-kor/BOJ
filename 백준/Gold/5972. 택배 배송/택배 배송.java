import java.io.*;
import java.util.*;

public class Main{
    
    static int N,M;
    static ArrayList<Node> nodeList[];
    static int dijk[];
    static class Node implements Comparable<Node>{
        int e,v;
        
        Node(int e, int v){
            this.e = e;
            this.v = v;
        }
        
        @Override
        public int compareTo(Node n){
            return this.v-n.v;
        }
    }
    
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nodeList = new ArrayList[N+1];
        dijk = new int[N+1];
        Arrays.fill(dijk, Integer.MAX_VALUE);
        for(int i=0; i<N+1; i++){
            nodeList[i] = new ArrayList<>();
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            nodeList[s].add(new Node(e,v));
            nodeList[e].add(new Node(s,v));
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0));
        boolean visited[] = new boolean[N+1];
        while(!pq.isEmpty()){
            Node curnode = pq.poll();
            int pos = curnode.e;
            int cost = curnode.v;
            if(visited[pos]) continue;
            visited[pos] = true;
            for(Node nextNode : nodeList[pos]){
                int newcost = cost + nextNode.v;
                if(newcost < dijk[nextNode.e]){
                    dijk[nextNode.e] = newcost;
                    pq.add(new Node(nextNode.e, newcost));
                }
            }
        }
        System.out.println(dijk[N]);
    }
}