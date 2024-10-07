import java.io.*;
import java.util.*;

public class Main
{
	static int N,M,W;

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<T+1; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			int floyd[][] = new int[N+1][N+1];
			int INF = 10000 * M;
			for(int i=0; i<N+1; i++) {
				Arrays.fill(floyd[i],INF);
				floyd[i][i] = 0;
			}

			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				if(floyd[start][end] > cost) {
					floyd[start][end] = cost;
				}
				if(floyd[end][start] > cost){
				    floyd[end][start] = cost;
				}
			}
			
			for(int i=0; i<W; i++){
			    st = new StringTokenizer(br.readLine());
			    int start = Integer.parseInt(st.nextToken());
			    int end = Integer.parseInt(st.nextToken());
			    int cost = Integer.parseInt(st.nextToken());
			    if(floyd[start][end] > -cost){
			        floyd[start][end] = -cost;
			    }
			}
			for(int k=1; k<N+1; k++){
			    for(int i=1; i<N+1; i++){
			        for(int j=1; j<N+1; j++){
			            if(floyd[i][j] > floyd[i][k] + floyd[k][j]){
			                floyd[i][j] = floyd[i][k] + floyd[k][j];
			            }
			        }
			    }
			}
			String result = "NO";
			for(int i=1; i<N+1; i++){
			    if(floyd[i][i] < 0){
			        result = "YES";
			    }
			}
			System.out.println(result);
		}
	}
}