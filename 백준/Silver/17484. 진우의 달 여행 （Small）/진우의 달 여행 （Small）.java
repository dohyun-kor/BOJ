import java.io.*;
import java.util.*;

public class Main{
    
    static int dx[] = {1,1,1};
    static int dy[] = {-1,0,1};
    
    static int map[][];
    static int result;
    static int N,M;
    
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        result = Integer.MAX_VALUE;
        bfs();
        
        System.out.println(result);
    }
    
    static void bfs(){
        Queue<int[]> q = new ArrayDeque<>();
        for(int i=0; i<M; i++){
            q.add(new int[]{0,i,-1,map[0][i]});
        }
        while(!q.isEmpty()){
            int cur[] = q.poll();
            int curx = cur[0];
            int cury = cur[1];
            int dir = cur[2];
            int cost = cur[3];
            for(int k=0; k<3; k++){
                int nx = curx + dx[k];
                int ny = cury + dy[k];
                if(ny<0 || M<=ny || k==dir) continue;
                if(N<=nx){
                    result = Math.min(result, cost);
                    continue;
                }
                
                q.add(new int[] {nx, ny, k, cost+map[nx][ny]});
            }
        }
    }
}