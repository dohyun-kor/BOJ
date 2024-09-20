import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        
        int dp[] = new int[N+2];
        int info[][] = new int[N+1][2];
        for(int i=1; i<N+1; i++){
            st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            info[i][0] = day;
            info[i][1] = cost;
        }
        
        for(int i=1; i<N+1; i++){
            dp[i] = Math.max(dp[i-1], dp[i]);
            int day = info[i][0];
            int cost = info[i][1];
            if(i+day >= N+2) continue;
            dp[i+day] = Math.max(dp[i+day], dp[i]+cost);
        }
        
        int result = Integer.MIN_VALUE;
        for(int i=0; i<N+2; i++){
            result = Math.max(result, dp[i]);
        }
        
        System.out.println(result);
    }
}