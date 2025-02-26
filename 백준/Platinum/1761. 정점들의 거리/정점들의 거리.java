import java.io.*;
import java.util.*;

public class Main{
    
    static int N, M, parent[][], depth[], distance[];
    static final int MAXV = 17;
    static ArrayList<int[]> list[]; 
    
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        
        parent = new int[N+1][MAXV];
        depth = new int[N+1];
        distance = new int[N+1];
        list = new ArrayList[N+1];
        for(int i=0; i<N+1; i++){
            list[i] = new ArrayList<>();
        }
        
        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(new int[] {b, c});
            list[b].add(new int[] {a, c});
        }
        dfs();
        sparseTable();
        
        M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int anc = lca(s, e);
            System.out.println(-distance[anc] + distance[s] - distance[anc] + distance[e]);
        }
    }
    
    static void dfs(){
        Stack<int[]> stack = new Stack<>();
        stack.add(new int[] {1, 1});
        parent[1][0] = 1;
        while(!stack.isEmpty()){
            int cur[] = stack.pop();
            int point = cur[0];
            int curdepth = cur[1];
            for(int next[] : list[point]){
                if(parent[next[0]][0] != 0) continue;
                parent[next[0]][0] = point;
                depth[next[0]] = curdepth+1;
                distance[next[0]] = distance[point] + next[1];
                stack.add(new int[] {next[0], curdepth+1});
            }
        }
    }
    
    static void sparseTable(){
        for(int i=1; i<MAXV; i++){
            for(int j=1; j<N+1; j++){
                parent[j][i] = parent[parent[j][i-1]][i-1];
            }
        }
    }
    
    static int lca(int a, int b){
        if(depth[b] < depth[a]){
            int tempt = a;
            a = b;
            b = tempt;
        }
        
        for(int i=MAXV-1; i>=0; i--){
            if(depth[b] - depth[a] >= (1<<i)){
                b = parent[b][i];
            }
        }
        if(a == b) return a;
        
        for(int i=MAXV-1; i>=0; i--){
            if(parent[a][i] != parent[b][i]){
                a = parent[a][i];
                b = parent[b][i];
            }
        }
        return parent[a][0];
    }
}