import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int floyd[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		floyd = new int[N + 1][N + 1];
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (i == j)
					continue;
				floyd[i][j] = 1000000;
			}
		}

		for (int n = 0; n < M; n++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			floyd[start][end] = cost;
		}

		for (int k = 1; k < N + 1; k++) {
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					floyd[i][j] = Math.min(floyd[i][j], floyd[i][k] + floyd[k][j]);
				}
			}
		}
		int K = Integer.parseInt(br.readLine());
		int friends[] = new int[K];

		int result = 987654321;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			friends[i] = Integer.parseInt(st.nextToken());
		}

		int sum[] = new int[N + 1];

		for (int go = 1; go < N + 1; go++) {
			for (int i = 0; i < K; i++) {
				int a = friends[i];
				sum[go] = Math.max(sum[go], floyd[a][go] + floyd[go][a]);
			}
			if (result > sum[go]) {
				result = sum[go];
			}
		}
		for (int i = 1; i < N + 1; i++) {
			if (sum[i] == result) {
				System.out.print(i + " ");
			}
		}
	}

}