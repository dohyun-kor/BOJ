import java.io.*;
import java.util.*;

public class Main{
    
    static int N;
    static int dp[][];
    static int coin[][];
    static int answer, result;
    
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        for(int tc=0; tc<3; tc++){
            N = Integer.parseInt(br.readLine());
            dp = new int[N][100001];
            coin = new int[N][2];
            st = new StringTokenizer(br.readLine());
            int sum = 0;
            answer = 0;
            int m = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            for(int i=0; i<=c; i++){
                int idx = m*i;
                dp[0][idx] = 1;
            }
            sum += m*c;
            for(int i=1; i<N; i++){
                st = new StringTokenizer(br.readLine());
                int money = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());
                sum += money*count;
                coin[i][0] = money;
                coin[i][1] = count;
            }
            result = 0;
            if(sum % 2 == 1){
                System.out.println("0");
                continue;
            }else{
                result = sum/2;
            }
            if(dp[0][result] == 1){
                System.out.println("1");
                continue;
            }
            find();
            System.out.println(answer);
        }
    }
    
    static void find(){
        for(int i=1; i<N; i++){
            int money = coin[i][0];
            int count = coin[i][1];
            for(int j=0; j<=result; j++){
                if(dp[i-1][j] == 0) continue;
                for(int c=0; c<=count; c++){
                    int plus = money*c;
                    if(j + plus == result){
                        answer = 1;
                        return;
                    }
                    dp[i][j + plus] = 1;
                }
            }
        }
    }
}