import java.io.*;
import java.util.*;

public class Main
{
    static int N,K,map[][],L;
    static ArrayList<info> infoList;
    static int dx[] = {0,1,0,-1};
    static int dy[] = {1,0,-1,0};
    
    
    static class info{
        int idx;
        char dir;
        info(int idx, char dir){
            this.idx = idx;
            this.dir = dir;
        }
    }
    
    static class snake{
        int x,y,dir;
        snake(int x, int y, int dir){
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        map = new int[N][N];
        
        
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a-1][b-1] = Integer.MAX_VALUE;
        }
        
        L = Integer.parseInt(br.readLine());
        infoList = new ArrayList<>();
        for(int i=0; i<L; i++){
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            
            infoList.add(new info(idx+1, dir));
        }
        
        System.out.println(simul());
        
	}
	
	static int simul(){
	    Stack<snake> stack = new Stack<>();
	    Queue<int[]> tailinfo = new LinkedList<>();
	    stack.add(new snake(0,0,0));
	    tailinfo.add(new int[]{0,0});
	    int cnt = 0;
	    int infoidx = 0;
	    info target = infoList.get(infoidx);
	    while(true){
	        snake cur = stack.pop();
	        cnt++;
	        int curx = cur.x;
	        int cury = cur.y;
	        int dir = cur.dir;
	        if(infoidx < infoList.size() && target.idx == cnt){
	            dir = convert(dir, target.dir);
	            if(infoidx+1<infoList.size()){
	                infoidx++;
	                target = infoList.get(infoidx);
	            }
	        }
	        
	        int ni = curx+dx[dir];
	        int nj = cury+dy[dir];
	        if(ni<0 || N<=ni || nj<0 || N<=nj || map[ni][nj]==1) return cnt;
	        if(map[ni][nj] == Integer.MAX_VALUE){
	            map[ni][nj] = 1;
	            stack.add(new snake(ni,nj,dir));
	            tailinfo.add(new int[]{ni,nj});
	        }else{
	            map[ni][nj] = 1;
	            stack.add(new snake(ni,nj,dir));
	            tailinfo.add(new int[]{ni,nj});
	            int remove[] = tailinfo.poll();
	            map[remove[0]][remove[1]] = 0;
	        }
	        
	    }
	}
	
	static int convert(int curdir , char movedir){
	    if(movedir == 'D'){
	        return (curdir+1)%4;
	    }else{
	        return (curdir+3)%4;
	    }
	}
}