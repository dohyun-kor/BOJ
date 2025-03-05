import java.io.*;
import java.util.*;

public class Main {

    static int N,M,H;
    static ArrayList<Integer> blocks[];
    static int dp[][];

    public static void main(String [] args) throws Exception {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        dp = new int[N][1001];

        blocks = new ArrayList[N];
        for(int i=0; i<N; i++) {
            blocks[i] = new ArrayList<>();
        }

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());

            while(st.hasMoreTokens()) {
                blocks[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        dp[0][0] = 1;
        for(int a : blocks[0]) {
            dp[0][a] = 1;
        }

        for(int i=1; i<N; i++) {
            for(int j=0; j<1001; j++) {
                if(dp[i-1][j] == 0) continue;
                dp[i][j] += dp[i-1][j];
                for(int b : blocks[i]) {
                    if(j+b > 1000) continue;
                    dp[i][j+b] += dp[i-1][j];
                    dp[i][j+b] %= 10_007;
                }
            }
        }
        System.out.println(dp[N-1][H] % 10_007);
    }
}