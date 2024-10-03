import java.io.*;
import java.util.*;

public class Main {

	static int n, m, r;
	static int floyd[][];
	static int item[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		floyd = new int[n + 1][n + 1];
		item = new int[n + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < n + 1; i++) {
			item[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if (i == j)
					continue;
				floyd[i][j] = 10000;
			}
		}

		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			floyd[start][end] = cost;
			floyd[end][start] = cost;
		}

		for (int k = 1; k < n + 1; k++) {
			for (int i = 1; i < n + 1; i++) {
				for (int j = 1; j < n + 1; j++) {
					floyd[i][j] = Math.min(floyd[i][j], floyd[i][k] + floyd[k][j]);
				}
			}
		}
		int result = 0;
		for (int i = 1; i < n + 1; i++) {
			int sum = 0;
			for (int j = 1; j < n + 1; j++) {
				if (floyd[i][j] <= m)
					sum += item[j];
			}
			result = Math.max(result, sum);
		}

		System.out.println(result);
	}
}