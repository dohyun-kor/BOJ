import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int result[][] = new int[N + 1][N + 1];
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (i == j)
					continue;
				result[i][j] = 200000000;
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			if (result[start][end] == 200000000)
				result[start][end] = cost;
			else
				result[start][end] = Math.min(result[start][end], cost);
		}
		int count[][] = new int[N + 1][N + 1];
		ArrayList<Integer> spot[][] = new ArrayList[N + 1][N + 1];
		for (int i = 0; i < N + 1; i++) {
			for (int j = 0; j < N + 1; j++) {
				spot[i][j] = new ArrayList<>();
			}
		}
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (0 < result[i][j] && result[i][j] < 200000000) {
					count[i][j] = 2;
					if (!spot[i][j].contains(i))
						spot[i][j].add(i);
					if (!spot[i][j].contains(j))
						spot[i][j].add(j);
				}
			}
		}

		for (int k = 1; k < N + 1; k++) {
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					if (result[i][j] > result[i][k] + result[k][j]) {
						count[i][j] = count[i][k] + count[k][j] - 1;
						result[i][j] = result[i][k] + result[k][j];
						spot[i][j].clear();
						for (int x = 0; x < spot[i][k].size(); x++) {
							if (spot[i][j].contains(spot[i][k].get(x)))
								continue;
							spot[i][j].add(spot[i][k].get(x));
						}
						for (int x = 0; x < spot[k][j].size(); x++) {
							if (spot[i][j].contains(spot[k][j].get(x)))
								continue;
							spot[i][j].add(spot[k][j].get(x));
						}
					}
				}
			}
		}
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (result[i][j] < 200000000)
					System.out.print(result[i][j] + " ");
				else
					System.out.print("0 ");
			}
			System.out.println();
		}
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				System.out.print(count[i][j]);
				if (spot[i][j].size() != 0) {
					for (int k = 0; k < spot[i][j].size(); k++) {
						System.out.print(" " + spot[i][j].get(k));
					}
				}
				System.out.println();
			}
		}

	}

}