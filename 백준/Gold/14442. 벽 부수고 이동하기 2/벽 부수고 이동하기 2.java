import java.io.*;
import java.util.*;

public class Main
{
    static int N,M,K,map[][],result;
    static int dx[] = {0, 1, 0, -1};
    static int dy[] = {1, 0, -1, 0};
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i=0; i<N; i++){
		    String s = br.readLine();
		    for(int j=0; j<M; j++){
		        map[i][j] = Character.getNumericValue(s.charAt(j));
		    }
		}
		result = -1;
		bfs();
		System.out.println(result);
		
	}
	
	static void bfs(){
	    boolean visited[][][] = new boolean[N+1][M+1][K+1];
	    Queue<int[]> q = new LinkedList<>();
	    q.add(new int[] {0,0,0,1});
	    visited[0][0][0] = true;
	    while(!q.isEmpty()){
	        int cur[] = q.poll();
	        int curx = cur[0];
	        int cury = cur[1];
	        int count = cur[2];
	        int dist = cur[3];
	        if(curx == N-1 && cury == M-1){
	            result = dist;
	            return;
	        }
	        for(int k=0; k<4; k++){
	            int ni = curx + dx[k];
	            int nj = cury + dy[k];
	            if(ni<0 || N<=ni || nj<0 || M<=nj || count>K || visited[ni][nj][count]) continue;
	            visited[ni][nj][count] = true;
	            if(map[ni][nj] == 0){
	                q.add(new int[] {ni, nj, count, dist+1});
	            }
	            else{
	                q.add(new int[] {ni, nj, count+1, dist+1});
	            }
	        }
	    }
	}
}