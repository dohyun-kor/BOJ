import java.io.*;
import java.util.*;

public class Main
{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        if(n==1){
            System.out.println(1);
            return;
        }
        if(n==2){
            System.out.println(3);
            return;
        }
        int dp[] = new int[n+1];
        dp[1] = 1;
        dp[2] = 3;
        for(int i=3; i<n+1; i++){
            dp[i] = (dp[i-1] + 2*dp[i-2])%10007;
        }
		
		System.out.println(dp[n]);
	}
}