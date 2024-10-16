import java.io.*;
import java.util.*;

public class Main
{
    static int N, info[][], dp[][];
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		info = new int[N][2];
		dp = new int[N][N];
		for(int i=0; i<N; i++){
		    Arrays.fill(dp[i], Integer.MAX_VALUE);
		}
		for(int i=0; i<N; i++){
		    st = new StringTokenizer(br.readLine());
		    int r = Integer.parseInt(st.nextToken());
		    int c = Integer.parseInt(st.nextToken());
		    info[i][0] = r;
		    info[i][1] = c;
		}
		
		for(int i=0; i<N; i++){
		    for(int j=0; j<N-i; j++){
		        int a = j;
		        int b = j+i;
		        if(a==b){
		            dp[a][b] = 0;
		            continue;
		        }
		        for(int k=a; k<b; k++){
		            dp[a][b] = Math.min(dp[a][b], dp[a][k] + dp[k+1][b] + info[a][0] * info[k][1] * info[b][1]);
		        }
		    }
		}
		
		System.out.println(dp[0][N-1]);
	}
}