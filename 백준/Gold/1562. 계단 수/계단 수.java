import java.io.*;
import java.util.*;

public class Main
{
    
    static int N;
    static long dp[][][];
    static int MOD = 1_000_000_000;
    
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        
        dp = new long[N+1][10][1<<10];
        
        for(int k=1; k<10; k++){
            dp[1][k][1<<k] = 1;
        }
	    
	    for(int n=2; n<N+1; n++){
	        for(int k=0; k<10; k++){
	            for(int visit = 0; visit < (1<<10); visit++){
	                int newvisit = visit | (1<<k);
	                if(k==0){
	                    dp[n][k][newvisit] += dp[n-1][k+1][visit];
	                    dp[n][k][newvisit] %= MOD;
	                }
	                else if(k==9){
	                    dp[n][k][newvisit] += dp[n-1][k-1][visit];
	                    dp[n][k][newvisit] %= MOD;
	                }
	                else{
	                    dp[n][k][newvisit] += dp[n-1][k-1][visit];
                        dp[n][k][newvisit] %= MOD;
	                    dp[n][k][newvisit] += dp[n-1][k+1][visit];
	                    dp[n][k][newvisit] %= MOD;
	                }
	            }
	        }
	    }
	    long answer = 0;
	    for(int i=0; i<10; i++){
	        answer += dp[N][i][(1<<10)-1];
	        answer %= MOD;
	    }
	    System.out.println(answer);
	}
}