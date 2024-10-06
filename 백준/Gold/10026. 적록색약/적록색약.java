import java.io.*;
import java.util.*;

public class Main
{
    static int N,result1,result2;
    static char map[][];
    static boolean visited[][];
    static boolean visited2[][];
    static int dx[] = {0,1,0,-1};
    static int dy[] = {1,0,-1,0};
    
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];
        visited2 = new boolean[N][N];
        for(int i=0; i<N; i++){
            String s = br.readLine();
            for(int j=0; j<N; j++){
                map[i][j] = s.charAt(j);
            }
        }
        result1 = 0;
        result2 = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(!visited[i][j]) bfs(i,j);
                if(!visited2[i][j]) bfs2(i,j);
            }
        }
        System.out.println(result1 + " " + result2);
	}
	
	static void bfs(int x, int y){
	    char color = map[x][y];
	    Queue<int []> q = new LinkedList<>();
        q.add(new int[] {x,y});
        visited[x][y] = true;
        while(!q.isEmpty()){
            int cur[] = q.poll();
            int curx = cur[0];
            int cury = cur[1];
            for(int k=0; k<4; k++){
                int ni = curx + dx[k];
                int nj = cury + dy[k];
                if(ni<0 || N<=ni || nj<0 || N<=nj || visited[ni][nj] || map[ni][nj]!=color) continue;
                visited[ni][nj] = true;
                q.add(new int[] {ni, nj});
            }
        }
        result1++;
	}
	
	static void bfs2(int x, int y){
	    char color = map[x][y];
	    Queue<int []> q = new LinkedList<>();
	    q.add(new int[] {x,y});
	    visited2[x][y] = true;
	    while(!q.isEmpty()){
	        int cur[] = q.poll();
	        int curx = cur[0];
	        int cury = cur[1];
	        for(int k=0; k<4; k++){
	            int ni = curx + dx[k];
	            int nj = cury + dy[k];
	            if(ni<0 || N<=ni || nj<0 || N<=nj || visited2[ni][nj]) continue;
	            if(color == 'R' || color == 'G'){
	                if(map[ni][nj] == 'R' || map[ni][nj] == 'G'){
	                    visited2[ni][nj] = true;
	                    q.add(new int[] {ni, nj});
	                }
	            }
	            else{
	                if(map[ni][nj] == 'B'){
	                    visited2[ni][nj] = true;
	                    q.add(new int[] {ni, nj});
	                }
	            }
	        }
	    }
	    result2++;
	}
}