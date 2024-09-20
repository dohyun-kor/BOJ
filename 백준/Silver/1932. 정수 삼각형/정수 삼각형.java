import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        int map[][] = new int[N][N];
        int dp[][] = new int[N][N];
        
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<i+1; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0][0] = map[0][0];
        for(int i=1; i<N; i++){
            for(int j=0; j<i+1; j++){
                if(j==0){
                    dp[i][j] = dp[i-1][j] + map[i][j];
                    continue;
                }
                if(j==i){
                    dp[i][j] = dp[i-1][j-1] + map[i][j];
                    continue;
                }
                dp[i][j] = Math.max(dp[i-1][j] + map[i][j] , dp[i-1][j-1] + map[i][j]); 
            }
        }
        int max_v =0;
        for(int j=0; j<N; j++){
            max_v = Math.max(max_v, dp[N-1][j]);
        }
        System.out.println(max_v);
    }
}