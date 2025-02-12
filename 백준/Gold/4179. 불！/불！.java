import java.io.*;
import java.util.*;

public class Main{
    
    static int R,C;
    static char map[][];
    static Queue<Jihoon> jihoon;
    static Queue<Fire> fire;
    static boolean visited[][];
    static int dx[] = {0,1,0,-1};
    static int dy[] = {1,0,-1,0};
    
    static class Fire{
        int x,y,time;
        
        Fire(int x, int y, int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    
    static class Jihoon{
        int x,y,time;
        
        Jihoon(int x, int y, int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    
    
    public static void main(String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        map = new char[R][C];
        visited = new boolean[R][C];
        jihoon = new ArrayDeque<>();
        fire = new ArrayDeque<>();
        
        
        for(int i=0; i<R; i++){
            String s = br.readLine();
            for(int j=0; j<C; j++){
                map[i][j] = s.charAt(j);
                if(map[i][j] == 'J'){
                    jihoon.add(new Jihoon(i,j,0));
                }
                if(map[i][j] == 'F'){
                    fire.add(new Fire(i,j,0));
                    visited[i][j] = true;
                }
            }
        }
        int result = simul();
        if(result== -1){
            System.out.println("IMPOSSIBLE");
        }else{
            System.out.println(result);
        }
    }
    
    static int simul(){
        int turn = 1;
        while(!jihoon.isEmpty()){
            while(!fire.isEmpty() && fire.peek().time < turn){
                Fire curfire = fire.poll();
                int firex = curfire.x;
                int firey = curfire.y;
                int firetime = curfire.time;
                for(int k=0; k<4; k++){
                    int nfx = firex + dx[k];
                    int nfy = firey + dy[k];
                    if(nfx<0 || nfy <0 || nfx>=R || nfy>=C || visited[nfx][nfy] || map[nfx][nfy] == '#') continue;
                    visited[nfx][nfy] = true;
                    fire.add(new Fire(nfx, nfy, firetime+1));
                }
            }
            while(!jihoon.isEmpty() && jihoon.peek().time < turn){
                Jihoon curjihoon = jihoon.poll();
                int jx = curjihoon.x;
                int jy = curjihoon.y;
                int jtime = curjihoon.time;
                for(int k=0; k<4; k++){
                    int njx = jx + dx[k];
                    int njy = jy + dy[k];
                    if(njx<0 || njy<0 || njx>=R || njy>=C) return jtime+1;
                    if(visited[njx][njy] || map[njx][njy] == '#') continue;
                    visited[njx][njy] = true;
                    jihoon.add(new Jihoon(njx, njy, jtime+1));
                }
            }
            turn++;
        }
        return -1;
    }
}