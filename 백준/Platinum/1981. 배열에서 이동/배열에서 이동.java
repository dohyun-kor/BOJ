import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int map[][];
    static int dp[][][];
    static int dx[] = {0,1,0,-1};
    static int dy[] = {1,0,-1,0};

    static class info {
        int x,y,maxV,minV;

        public info(int x, int y, int maxV, int minV) {
            this.x = x;
            this.y = y;
            this.maxV = maxV;
            this.minV = minV;
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N][201];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                Arrays.fill(dp[i][j], 201);
            }
        }


        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();
        int answer = 202;
        for(int i=0; i<201; i++){
            if(dp[N-1][N-1][i] == 201) continue;
            answer = Math.min(answer, i - dp[N-1][N-1][i]);
        }
        System.out.println(answer);
    }

    static void bfs() {
        Queue<info> q = new ArrayDeque<>();
        q.add(new info(0,0,map[0][0], map[0][0]));

        while(!q.isEmpty()) {
            info curInfo = q.poll();
            int curx = curInfo.x;
            int cury = curInfo.y;
            int maxV = curInfo.maxV;
            int minV = curInfo.minV;
            for(int k=0; k<4; k++) {
                int nx = curx + dx[k];
                int ny = cury + dy[k];
                if(nx<0 || N<=nx || ny<0 || N<=ny) continue;
                int nvalue = map[nx][ny];
                int maxValue = Math.max(maxV, nvalue);
                int minValue = Math.min(minV, nvalue);
                int diff = maxValue - minValue;
                if(dp[nx][ny][maxValue] != 201 && maxValue - dp[nx][ny][maxValue] <= diff) continue;
                dp[nx][ny][maxValue] = minValue;
                q.add(new info(nx,ny,maxValue, minValue));
            }
        }
    }
}