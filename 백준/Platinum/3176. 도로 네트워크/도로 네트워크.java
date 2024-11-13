import java.io.*;
import java.util.*;

public class Main{
    
    static int N, K, parent[][], depth[], maxv[][] , minv[][];
    static ArrayList<int[]> graph[];
    static int MAX = 18;
    static long max_v, min_v;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        
        parent = new int[N+1][MAX];
        maxv = new int[N+1][MAX];
        minv = new int[N+1][MAX];
        for(int i=0; i<N+1; i++){
            Arrays.fill(minv[i], Integer.MAX_VALUE);
        }
        depth = new int[N+1];
        graph = new ArrayList[N+1];
        for(int i=0; i<N+1; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph[A].add(new int[] {B,C});
            graph[B].add(new int[] {A,C});
        }
        
        dfs();
        sparseTable();
        StringBuilder sb = new StringBuilder();
        K = Integer.parseInt(br.readLine());
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            min_v = Long.MAX_VALUE;
            max_v = Long.MIN_VALUE;
            int D = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            lca(D,E);
            sb.append(min_v).append(" ").append(max_v).append("\n");
        }
        System.out.println(sb);
        
    }
    
    static void dfs(){
        Stack<int[]> stack = new Stack<>();
        stack.add(new int[] {1,1});
        while(!stack.isEmpty()){
            int cur[] = stack.pop();
            int num = cur[0];
            int curdepth = cur[1];
            depth[num] = curdepth;
            for(int[] next : graph[num]){
                if(depth[next[0]] != 0) continue;
                stack.add(new int[]{next[0], curdepth+1});
                parent[next[0]][0] = num;
                maxv[next[0]][0] = next[1];
                minv[next[0]][0] = next[1];
            }
        }
    }
    
    static void sparseTable(){
        for(int i=1; i<MAX; i++){
            for(int node=1; node<N+1; node++){
                maxv[node][i] = Math.max(maxv[node][i] , Math.max(maxv[parent[node][i-1]][i-1], maxv[node][i-1]));
                minv[node][i] = Math.min(minv[node][i] , Math.min(minv[parent[node][i-1]][i-1], minv[node][i-1]));
                parent[node][i] = parent[parent[node][i-1]][i-1];
            }
        }
    }
    
    static void lca(int a, int b){
        if(depth[a] < depth[b]){
            int tempt = a;
            a = b;
            b = tempt;
        }
        
        for(int i=MAX-1; i>=0; i--){
            if(depth[a] - depth[b] >= (1<<i)){
                max_v = Math.max(max_v, maxv[a][i]);
                min_v = Math.min(min_v, minv[a][i]);
                a = parent[a][i];
            }
        }
        if(a==b) return;
        
        for(int i=MAX-1; i>=0; i--){
            if(parent[a][i] != parent[b][i]){
                max_v = Math.max(max_v, Math.max(maxv[a][i] , maxv[b][i]));
                min_v = Math.min(min_v, Math.min(minv[a][i] , minv[b][i]));
                a = parent[a][i];
                b = parent[b][i];
            }
        }
        max_v = Math.max(max_v, Math.max(maxv[a][0], maxv[b][0]));
        min_v = Math.min(min_v, Math.min(minv[a][0], minv[b][0]));
        return;
    }
}