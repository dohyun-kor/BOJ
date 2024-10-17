import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int map[][];
	static boolean visited[][];
	static int result = 26;
	static int lasti;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		map = new int[10][10];

		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					lasti = i;
				}
			}
		}

		visited = new boolean[10][10];

		deco(5, 5, 5, 5, 5, visited, 0, 0);

		if (result == 26) {
			result = -1;
		}

		System.out.println(result);

	}

	static boolean checking(int i, int j, int num) {
		for (int ni = 0; ni < num; ni++) {
			for (int nj = 0; nj < num; nj++) {
				if (10 <= i + ni || 10 <= j + nj || map[i + ni][j + nj] == 0 || visited[i + ni][j + nj] == true)
					return false;
			}
		}
		return true;
	}

	static void move(int i, int j, int num, boolean v) {
		for (int ni = 0; ni < num; ni++) {
			for (int nj = 0; nj < num; nj++) {
				visited[i + ni][j + nj] = v;
			}
		}
	}

	static void deco(int p1, int p2, int p3, int p4, int p5, boolean visited[][], int cnt, int starti) {
		if (cnt >= result) {
			return;
		}

		for (int i = starti; i <= lasti; i++) {
			for (int j = 0; j < 10; j++) {
				if (map[i][j] == 0 || visited[i][j] == true) {
					continue;
				}

				if (p1 > 0) {
					visited[i][j] = true;
					deco(p1 - 1, p2, p3, p4, p5, visited, cnt + 1, i);
					visited[i][j] = false;
				}
				if (p2 > 0) {
					if (!checking(i, j, 2))
						return;

					move(i, j, 2, true);
					deco(p1, p2 - 1, p3, p4, p5, visited, cnt + 1, i);
					move(i, j, 2, false);

				}

				if (p3 > 0) {
					if (!checking(i, j, 3))
						return;

					move(i, j, 3, true);
					deco(p1, p2, p3 - 1, p4, p5, visited, cnt + 1, i);
					move(i, j, 3, false);

				}

				if (p4 > 0) {
					if (!checking(i, j, 4))
						return;

					move(i, j, 4, true);
					deco(p1, p2, p3, p4 - 1, p5, visited, cnt + 1, i);
					move(i, j, 4, false);

				}

				if (p5 > 0) {
					if (!checking(i, j, 5))
						return;

					move(i, j, 5, true);
					deco(p1, p2, p3, p4, p5 - 1, visited, cnt + 1, i);
					move(i, j, 5, false);

				}
			}
		}

		for (int i = 0; i <= lasti; i++) {
			for (int j = 0; j < 10; j++) {
				if (map[i][j] == 1 && visited[i][j] == false) {
					return;
				}

			}
		}
		result = Math.min(result, cnt);
		return;

	}

}