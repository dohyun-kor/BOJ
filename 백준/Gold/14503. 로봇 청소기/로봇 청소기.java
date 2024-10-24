import java.io.*;
import java.util.*;


public class Main
{
    static int N,M,map[][],answer;
    static int dx[] = {-1,0,1,0};
    static int dy[] = {0,1,0,-1};
    static boolean isclean[][];
    static Queue<robot> q = new ArrayDeque<>();
    
    static class robot{
        int x,y,dir;
        
        robot(int x, int y, int dir){
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
	    
	    st = new StringTokenizer(br.readLine());
		int starti = Integer.parseInt(st.nextToken());
		int startj = Integer.parseInt(st.nextToken());
		int startdir = Integer.parseInt(st.nextToken());
		q.add(new robot(starti, startj, startdir));
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++){
		    st = new StringTokenizer(br.readLine());
		    for(int j=0; j<M; j++){
		        map[i][j] = Integer.parseInt(st.nextToken());
		    }
		}
		answer = 0;
		isclean = new boolean[N][M];
		simulate();
		System.out.println(answer);
	}
	
	static void simulate(){
	    while(!q.isEmpty()){
	        robot cur = q.poll();
	        int curx = cur.x;
	        int cury = cur.y;
	        int curdir = cur.dir;
	        
	        if(map[curx][cury] == 0 && isclean[curx][cury] == false) {
	            isclean[curx][cury] = true;
	            answer++;
	        }
	        
            if(checking(curx, cury)){
                int changedir = curdir;
                while(true){
                    changedir = (changedir+3)%4;
                    int nexti = curx+dx[changedir];
                    int nextj = cury+dy[changedir];
                    if(nexti < 0 || N<=nexti || nextj<0 || M<=nextj) continue;
                    if(map[nexti][nextj] == 0 && isclean[nexti][nextj] == false){
                        q.add(new robot(nexti, nextj, changedir));
                        break;
                    }
                }
            }
            else{
                int ni = curx - dx[curdir];
                int nj = cury - dy[curdir];
                if(ni<0 || N<=ni || nj<0 || M<=nj || map[ni][nj] == 1) continue;
                q.add(new robot(ni, nj, curdir));
            }
	        
	    }
	}
	
	static boolean checking(int x, int y){
	    for(int i=0; i<4; i++){
	        int ni = x + dx[i];
	        int nj = y + dy[i];
	        if(ni<0 || N<=ni || nj<0 || M<=nj) continue;
	        if(map[ni][nj] == 0 && isclean[ni][nj] == false){
	            return true;
	        }
	    }
	    return false;
	}
}