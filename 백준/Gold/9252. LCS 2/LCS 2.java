import java.io.*;
import java.util.*;

public class Main
{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s1 = br.readLine();
        String s2 = br.readLine();
        
        int N = s1.length();
        int M = s2.length();
        
        int dp[][] = new int[N+1][M+1];
        
        
        for(int i=1; i<N+1; i++){
            for(int j=1; j<M+1; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        
        System.out.println(dp[N][M]);
	    Stack<Character> stack = new Stack<>();
	    while(true){
	        if(N==0 && M==0) break;
	        if(dp[N][M] == 0) break;
	        if(dp[N][M] == dp[N-1][M]){
	            N--;
	        }
	        else if(dp[N][M] == dp[N][M-1]){
	            M--;
	        }
	        else{
	            N--;
	            M--;
	            stack.add(s1.charAt(N));
	        }
	    }
	    while(!stack.isEmpty()){
	        System.out.print(stack.pop());
	    }
	    
	}
}