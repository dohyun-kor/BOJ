import java.io.*;
import java.util.*;

public class Main
{
    static int N,M,map[][];
    static ArrayList<int[]> camera;
    static int dx[] = {0,1,0,-1};
    static int dy[] = {1,0,-1,0};
    static int answer;
    
    static class cam{
        int x,y,dir;
        cam(int x, int y, int dir){
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		camera = new ArrayList<>();
		int minus_v = 0;
		
		for(int i=0; i<N; i++){
		    st = new StringTokenizer(br.readLine());
		    for(int j=0; j<M; j++){
		        map[i][j] = Integer.parseInt(st.nextToken());
		        if(0<map[i][j]) minus_v++;
		        if(0<map[i][j] && map[i][j]<6){
		            camera.add(new int[]{i,j});
		        }
		    }
		}
		answer = Integer.MIN_VALUE;
		int dir[] = new int[camera.size()];
		comb(0,dir);
		System.out.println(N*M-minus_v-answer);
	}
	
	static void comb(int depth, int dir[]){
	    if(depth == camera.size()){
	        answer = Math.max(answer, go(dir));
	        return;
	    }
	    
	    for(int i=0; i<4; i++){
	        dir[depth] = i;
	        comb(depth+1, dir);
	    }
	}
	
	static int go(int dir[]){
	    boolean visited[][] = new boolean[N][M];
	    int idx = 0;
	    int cnt = 0;
	    while(idx<camera.size()){
	        int curcamera[] = camera.get(idx);
	        int curdir = dir[idx];
	        int curx = curcamera[0];
	        int cury = curcamera[1];
	        
	        if(map[curx][cury] == 1){
	            Stack<int[]> stack = new Stack<>();
	            stack.add(new int[] {curx, cury});
	            while(!stack.isEmpty()){
	                int cur[] = stack.pop();
	                int currx = cur[0];
	                int curry = cur[1];
	                int ni = currx + dx[curdir];
	                int nj = curry + dy[curdir];
	                if(ni<0 || N<=ni || nj<0 || M<=nj || map[ni][nj] == 6) continue;
	                if(map[ni][nj] > 0){
	                    stack.add(new int[] {ni, nj});
	                }else{
	                    visited[ni][nj] = true;
	                    stack.add(new int[] {ni, nj});
	                }
	            }
	        }
	        
	        else if(map[curx][cury] == 2){
	            Queue<cam> q = new LinkedList<>();
	            if(curdir == 0 || curdir == 2){
	                q.add(new cam(curx, cury, 0));
	                q.add(new cam(curx, cury, 2));
	            }else{
	                q.add(new cam(curx, cury, 1));
	                q.add(new cam(curx, cury, 3));
	            }
	            while(!q.isEmpty()){
	                cam curcam = q.poll();
	                int currx = curcam.x;
	                int curry = curcam.y;
	                int currdir = curcam.dir;
	                
	                int ni = currx + dx[currdir];
	                int nj = curry + dy[currdir];
	                if(ni<0 || N<=ni || nj<0 || M<=nj || map[ni][nj] == 6) continue;
	                if(map[ni][nj] > 0){
	                    q.add(new cam(ni, nj, currdir));
	                }
	                else{
	                    visited[ni][nj] = true;
	                    q.add(new cam(ni, nj, currdir));
	                }
	            }
	        }
	        
	        else if(map[curx][cury] == 3){
	            Queue<cam> q = new LinkedList<>();
	            if(curdir == 0){
	                q.add(new cam(curx, cury, 0));
	                q.add(new cam(curx, cury, 1));
	            }
	            else if(curdir == 1){
	                q.add(new cam(curx, cury, 1));
	                q.add(new cam(curx, cury, 2));
	            }
	            else if(curdir == 2){
	                q.add(new cam(curx, cury, 2));
	                q.add(new cam(curx, cury, 3));
	            }
	            else{
	                q.add(new cam(curx, cury, 3));
	                q.add(new cam(curx, cury, 0));
	            }
	            while(!q.isEmpty()){
	                cam curcam = q.poll();
	                int currx = curcam.x;
	                int curry = curcam.y;
	                int currdir = curcam.dir;
	                
	                int ni = currx + dx[currdir];
	                int nj = curry + dy[currdir];
	                if(ni<0 || N<=ni || nj<0 || M<=nj || map[ni][nj] == 6) continue;
	                if(map[ni][nj] > 0){
	                    q.add(new cam(ni, nj, currdir));
	                }
	                else{
	                    visited[ni][nj] = true;
	                    q.add(new cam(ni, nj, currdir));
	                }
	            }
	        }
	        
	        else if(map[curx][cury] == 4){
	            Queue<cam> q = new LinkedList<>();
	            if(curdir == 0){
	                q.add(new cam(curx, cury, 0));
	                q.add(new cam(curx, cury, 1));
	                q.add(new cam(curx, cury, 3));
	            }
	            else if(curdir == 1){
	                q.add(new cam(curx, cury, 1));
	                q.add(new cam(curx, cury, 2));
	                q.add(new cam(curx, cury, 0));
	            }
	            else if(curdir == 2){
	                q.add(new cam(curx, cury, 2));
	                q.add(new cam(curx, cury, 3));
	                q.add(new cam(curx, cury, 1));
	            }
	            else{
	                q.add(new cam(curx, cury, 3));
	                q.add(new cam(curx, cury, 0));
	                q.add(new cam(curx, cury, 2));
	            }
	            while(!q.isEmpty()){
	                cam curcam = q.poll();
	                int currx = curcam.x;
	                int curry = curcam.y;
	                int currdir = curcam.dir;
	                
	                int ni = currx + dx[currdir];
	                int nj = curry + dy[currdir];
	                if(ni<0 || N<=ni || nj<0 || M<=nj || map[ni][nj] == 6) continue;
	                if(map[ni][nj] > 0){
	                    q.add(new cam(ni, nj, currdir));
	                }
	                else{
	                    visited[ni][nj] = true;
	                    q.add(new cam(ni, nj, currdir));
	                }
	            }
	        }
	        
	        else if(map[curx][cury] == 5){
	            Queue<cam> q = new LinkedList<>();
                q.add(new cam(curx, cury, 0));
                q.add(new cam(curx, cury, 1));
                q.add(new cam(curx, cury, 2));
                q.add(new cam(curx, cury, 3));
	            while(!q.isEmpty()){
	                cam curcam = q.poll();
	                int currx = curcam.x;
	                int curry = curcam.y;
	                int currdir = curcam.dir;
	                
	                int ni = currx + dx[currdir];
	                int nj = curry + dy[currdir];
	                if(ni<0 || N<=ni || nj<0 || M<=nj || map[ni][nj] == 6) continue;
	                if(map[ni][nj] > 0){
	                    q.add(new cam(ni, nj, currdir));
	                }
	                else{
	                    visited[ni][nj] = true;
	                    q.add(new cam(ni, nj, currdir));
	                }
	            }
	        }
	        idx++;
	    }
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(visited[i][j]) cnt++;
            }
        }
        return cnt;
	}
}