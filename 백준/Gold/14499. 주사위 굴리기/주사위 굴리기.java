import java.io.*;
import java.util.*;

public class Main
{
    static int N,M,x,y,K,map[][],order[];
    static int dx[] = {0,0,-1,1};
    static int dy[] = {1,-1,0,0};
    
    static class dice{
        int x,y,one,two,three,four,five,six;
        
        dice(int x, int y, int one, int two, int three, int four, int five, int six){
            this.x = x;
            this.y = y;
            this.one = one;
            this.two = two;
            this.three = three;
            this.four = four;
            this.five = five;
            this.six = six;
        }
    }
    
    
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
	    st = new StringTokenizer(br.readLine());
	    N = Integer.parseInt(st.nextToken());
	    M = Integer.parseInt(st.nextToken());
	    x = Integer.parseInt(st.nextToken());
	    y = Integer.parseInt(st.nextToken());
	    K = Integer.parseInt(st.nextToken());
	    
	    map = new int[N][M];
	    order = new int[K];
	    
	    for(int i=0; i<N; i++){
	        st = new StringTokenizer(br.readLine());
	        for(int j=0; j<M; j++){
	            map[i][j] = Integer.parseInt(st.nextToken());
	        }
	    }
	    
	    st = new StringTokenizer(br.readLine());
	    for(int i=0; i<K; i++){
	        order[i] = Integer.parseInt(st.nextToken()) - 1;
	    }
	    
	    simulate();
	}
	
	static void simulate(){
	    Queue<dice> q = new ArrayDeque<>();
	    q.add(new dice(x,y,0,0,0,0,0,0));
	    for(int dir : order){
	        dice cur = q.peek();
	        int curx = cur.x;
	        int cury = cur.y;
	        
	        int ni = cur.x + dx[dir];
	        int nj = cur.y + dy[dir];
	        if(ni<0 || N<=ni || nj<0 || M<=nj) continue;
	        q.poll();
	        if(dir == 0){
	            int bottom = cur.three;
	            if(map[ni][nj] == 0){
	                map[ni][nj] = bottom;
	            }else{
	                bottom = map[ni][nj];
	                map[ni][nj] = 0;
	            }
	            q.add(new dice(ni,nj, cur.four , cur.two , cur.one , cur.six, cur.five , bottom));
	            System.out.println(cur.four);
	        }
	        else if(dir == 1){
	            int bottom = cur.four;
	            if(map[ni][nj] == 0){
	                map[ni][nj] = bottom;
	            }else{
	                bottom = map[ni][nj];
	                map[ni][nj] = 0;
	            }
	            q.add(new dice(ni,nj, cur.three , cur.two , cur.six , cur.one, cur.five , bottom));
	            System.out.println(cur.three);

	        }
	        else if(dir == 2){
	            int bottom = cur.two;
	            if(map[ni][nj] == 0){
	                map[ni][nj] = bottom;
	            }else{
	                bottom = map[ni][nj];
	                map[ni][nj] = 0;
	            }
	            q.add(new dice(ni,nj, cur.five , cur.one , cur.three , cur.four, cur.six , bottom));
	            System.out.println(cur.five);
	        }
	        else{
	            int bottom = cur.five;
	            if(map[ni][nj] == 0){
	                map[ni][nj] = bottom;
	            }else{
	                bottom = map[ni][nj];
	                map[ni][nj] = 0;
	            }
	            q.add(new dice(ni,nj, cur.two , cur.six , cur.three , cur.four, cur.one , bottom));
	            System.out.println(cur.two);
	        }
	        
	    }
	}


}