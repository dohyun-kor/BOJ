import java.io.*;
import java.util.*;

public class Main{
    
    static int N,M,parent[][],depth[];
    static long cost[];
    static ArrayList<int[]> graph[];
    static int MAX = 18;
    
    
    static class node{
        int num,depth;
        long cost;
        node(int num, int depth, long cost){
            this.num = num;
            this.depth = depth;
            this.cost = cost;
        }
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        
        parent = new int[N+1][MAX];
        depth = new int[N+1];
        cost = new long[N+1];
        graph = new ArrayList[N+1];
        for(int i=0; i<N+1; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new int[] {v, w});
            graph[v].add(new int[] {u, w});
        }
        
        dfs();
        sparseTable();
        
        
        M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int anc = lca(a,b);
            
            if(order == 1){
                long result = cost[a] - cost[anc] + cost[b] - cost[anc];
                sb.append(result).append("\n");
                
            }else{
                int k = Integer.parseInt(st.nextToken());
                k--;
                if(depth[a] - depth[anc] >= k){ // a에서 k번 조상 찾기
                    int s = a;
                    for(int j = MAX-1; j>=0; j--){
                        if(k >= (1<<j)){
                            s = parent[s][j];
                            k -= (1<<j);
                        }
                    }
                    sb.append(s).append("\n");
                }else{ // b에서 조상 찾기
                    int nk = depth[a] - depth[anc] + depth[b] - depth[anc] - k;
                    int ss = b;
                    for(int j = MAX-1; j>=0; j--){
                        if(nk >= (1<<j)){
                            ss = parent[ss][j];
                            nk -= (1<<j);
                        }
                    }
                    sb.append(ss).append("\n");
                }
            }
            
        }
        System.out.println(sb);
    }
    
    static void dfs(){
        Stack<node> stack = new Stack<>();
        stack.add(new node(1,1,0));
        
        while(!stack.isEmpty()){
            node cur = stack.pop();
            int num = cur.num;
            int curdepth = cur.depth;
            long curcost = cur.cost;
            
            depth[num] = curdepth;
            cost[num] = curcost;
            
            for(int[] next : graph[num]){
                if(depth[next[0]] != 0) continue;
                parent[next[0]][0] = num;
                long newcost = curcost + next[1];
                stack.add(new node (next[0], curdepth+1, newcost));
            }
        }
    }
    
    static void sparseTable(){
        for(int i=1; i<MAX; i++){
            for(int node=1; node<N+1; node++){
                parent[node][i] = parent[parent[node][i-1]][i-1];
            }
        }
    }
    
    static int lca(int a , int b){
        if(depth[a] < depth[b]){
            int tempt = a;
            a = b;
            b = tempt;
        }
        
        for(int i=MAX-1; i>=0; i--){
            if(depth[a] - depth[b] >= (1<<i)){
                a = parent[a][i];
            }
        }
        
        if(a==b) return a;
        
        for(int i=MAX-1; i>=0; i--){
            if(parent[a][i] == parent[b][i]) continue;
            a = parent[a][i];
            b = parent[b][i];
        }
        return parent[a][0];
    }
}