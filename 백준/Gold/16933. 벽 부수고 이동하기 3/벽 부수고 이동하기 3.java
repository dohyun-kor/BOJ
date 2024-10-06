import java.io.*;
import java.util.*;

public class Main
{
    static int N,M,K,map[][];
    static int dx[] = {0,1,0,-1};
    static int dy[] = {1,0,-1,0};
    
	public static void main(String[] args)throws IOException {
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
	    System.out.println(bfs());
    }
    
    static int bfs(){
        Queue<int []> q = new LinkedList<>();
        boolean visited[][][][] = new boolean[N][M][K+1][2];
        q.add(new int[] {0,0,0,0,1});
        visited[0][0][0][0] = true;
        while(!q.isEmpty()){
            int cur[] = q.poll();
            int curx = cur[0];
            int cury = cur[1];
            int wall = cur[2];
            int time = cur[3];
            int count = cur[4];
            if(curx == N-1 && cury == M-1) return count;
            for(int k=0; k<4; k++){
                int ni = curx + dx[k];
                int nj = cury + dy[k];
                if(ni<0 || N<=ni || nj<0 || M<=nj || visited[ni][nj][wall][time]) continue;
                if(time == 0){
                    if(map[ni][nj] == 1 && wall<K){
                        if(visited[ni][nj][wall+1][(time+1)%2]) continue;
                        visited[ni][nj][wall+1][(time+1)%2] = true;
                        q.add(new int[] {ni,nj,wall+1,(time+1)%2,count+1});
                    }
                    if(map[ni][nj] == 0){
                        if(visited[ni][nj][wall][(time+1)%2]) continue;
                        visited[ni][nj][wall][(time+1)%2] = true;
                        q.add(new int[] {ni,nj,wall,(time+1)%2, count+1});
                    }
                }
                else{
                    if(!visited[curx][cury][wall][(time+1)%2]){
                        visited[curx][cury][wall][(time+1)%2] = true;
                        q.add(new int[] {curx,cury,wall,(time+1)%2, count+1});
                    }
                    if(map[ni][nj] == 0){
                        if(visited[ni][nj][wall][(time+1)%2]) continue;
                        visited[ni][nj][wall][(time+1)%2] = true;
                        q.add(new int[] {ni,nj,wall,(time+1)%2, count+1});
                    }
                }
            }
        }
        return -1;
    }
}