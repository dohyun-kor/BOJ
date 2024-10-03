import java.io.*;
import java.util.*;

public class Main
{
	static int N, floyd[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		floyd = new int[N+1][N+1];
		int sum = 0;
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<N+1; j++) {
				floyd[i][j] = Integer.parseInt(st.nextToken());
				if(i < j) sum += floyd[i][j];
			}
		}
		boolean visited[][] = new boolean[N+1][N+1];
		int result = 0;
		for(int k=1; k<N+1; k++) {
			for(int i=1; i<N+1; i++) {
				for(int j=1; j<N+1; j++) {
					if(floyd[i][j] > floyd[i][k] + floyd[k][j]) {
						System.out.println(-1);
						return;
					}
					else if(floyd[i][j] == floyd[i][k] + floyd[k][j]) {
                        if(visited[i][j] || visited[j][i]) continue;
                        if(i == k || k == j) continue;
                        visited[i][j] = true;
                        visited[j][i] = true;
						result += floyd[i][k] + floyd[k][j];
					}
				}
			}
		}
		System.out.println(sum - result);

	}
}