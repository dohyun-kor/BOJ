import java.io.*;
import java.util.*;

public class Main
{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int INF = N * 100 + 1;
		
		int floyd[][] = new int[N+1][N+1];
		for(int i=0; i<N+1; i++){
		    Arrays.fill(floyd[i], INF);
		}
		for(int i=0; i<N+1; i++){
		    floyd[i][i] = 0;
		}
		for(int i=0; i<M; i++){
		    st = new StringTokenizer(br.readLine());
		    int start = Integer.parseInt(st.nextToken());
		    int end = Integer.parseInt(st.nextToken());
		    int cost = Integer.parseInt(st.nextToken());
		    floyd[start][end] = cost;
		}
		
		for(int k=1; k<N+1; k++){
		    for(int i=1; i<N+1; i++){
		        for(int j=1; j<N+1; j++){
		            floyd[i][j] = Math.min(floyd[i][j], floyd[i][k] + floyd[k][j]);
		        }
		    }
		}
		int result = 0;
		for(int i=1; i<N+1; i++){
		    if(floyd[i][X] >= INF || floyd[X][i] >= INF) continue;
		    result = Math.max(result , floyd[i][X] + floyd[X][i]);
		}
		
		System.out.println(result);
	}
}