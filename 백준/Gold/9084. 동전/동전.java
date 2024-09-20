import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
       int T = Integer.parseInt(br.readLine());
       for(int tc=1; tc<T+1; tc++){
           int N = Integer.parseInt(br.readLine());
           st = new StringTokenizer(br.readLine());
           int coin[] = new int[N];
           for(int i=0; i<N; i++){
               coin[i] = Integer.parseInt(st.nextToken());
           }
           int M = Integer.parseInt(br.readLine());
           int dp[] = new int[M+1];
           
           
           
            for(int money : coin){
                for(int i=1; i<M+1; i++){
                   if(i-money == 0) dp[i] = dp[i] +1;
                   if(i-money < 0) continue;
                   dp[i] = dp[i] + dp[i-money];
               }
           }
           
           System.out.println(dp[M]);
       }
    }
}