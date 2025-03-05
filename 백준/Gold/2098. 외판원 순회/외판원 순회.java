import java.io.*;
import java.util.*;

public class Main
{
    static int N,dist[][],dp[][],INF;
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
        dist = new int[N+1][N+1];
        dp = new int[N+1][(1<<N+1)-2];
        for(int i=0; i<N+1; i++){
            Arrays.fill(dp[i], -1);
        }
        INF = N*1_000_000;
        
        
		for(int i=1; i<N+1; i++){
		    st = new StringTokenizer(br.readLine());
		    for(int j=1; j<N+1; j++){
		        int cost = Integer.parseInt(st.nextToken());
		        dist[i][j] = cost;
		    }
		}
		System.out.println(tsp(1,2));
	}
	
	static int tsp(int pos, int visited){
	    if(visited == (1<<N+1)-2){
	        if(dist[pos][1] == 0) return INF;
	        return dist[pos][1];
	    }
	    
	    if(dp[pos][visited] != -1) return dp[pos][visited];
	    dp[pos][visited] = INF;
	    
	    for(int i=1; i<N+1; i++){
	        int next = visited | (1<<i);
	        if(dist[pos][i] == 0 || (visited & (1<<i)) != 0) continue;
	        dp[pos][visited] = Math.min(dp[pos][visited], tsp(i, next) + dist[pos][i]);
	    }
	    return dp[pos][visited];
	}
}