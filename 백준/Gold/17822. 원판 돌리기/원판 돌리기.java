import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s[] = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		int T = Integer.parseInt(s[2]);
		int i, j, k, l;
		ArrayList<Integer> list[] = new ArrayList[N + 1];
		int turn[][] = new int[T][3];
		for (i = 0; i < list.length; i++) {
			list[i] = new ArrayList<>();
		}
		for (i = 0; i < N; i++) {
			String ss[] = br.readLine().split(" ");
			for (j = 0; j < M; j++) {
				list[i + 1].add(Integer.parseInt(ss[j]));
			}
		}

		for (i = 0; i < T; i++) {
			String sss[] = br.readLine().split(" ");
			for (j = 0; j < 3; j++) {
				turn[i][j] = Integer.parseInt(sss[j]);
			}
		}

		for (int ii = 0; ii < T; ii++) {
			int x = turn[ii][0];
			int dir = turn[ii][1];
			int time = turn[ii][2];
			for (l = 0; l < time; l++) {
				for (k = 1; k < N + 1; k++) {
					if (k % x == 0) {
						if (dir == 1) {
							int temp = list[k].get(0);
							list[k].remove(0);
							list[k].add(temp);

						} else {
							int temp = list[k].get(M - 1);
							list[k].remove(M - 1);
							list[k].add(0, temp);
						}

					}

				}

			} // time
			boolean flag = false;
			Stack<int[]> remove_list = new Stack<>();
			for (i = 1; i < N + 1; i++) {
				for (j = 0; j < M; j++) {
					int temp = list[i].get(j);
					if (temp == 0)
						continue;
					if (j - 1 >= 0) {
						if (temp == list[i].get(j - 1)) {
							remove_list.push(new int[] { i, j });
							remove_list.push(new int[] { i, j - 1 });
							flag = true;
						}
					}
					if (j - 1 < 0) {
						if (temp == list[i].get(M - 1)) {
							remove_list.push(new int[] { i, j });
							remove_list.push(new int[] { i, M - 1 });
							flag = true;
						}
					}
					if (j + 1 < M) {
						if (temp == list[i].get(j + 1)) {
							remove_list.push(new int[] { i, j });
							remove_list.push(new int[] { i, j + 1 });
							flag = true;
						}
					}
					if (j + 1 >= M) {
						if (temp == list[i].get(0)) {
							remove_list.push(new int[] { i, j });
							remove_list.push(new int[] { i, 0 });
							flag = true;

						}
					}
					if (i - 1 >= 1) {
						if (temp == list[i - 1].get(j)) {
							remove_list.push(new int[] { i, j });
							remove_list.push(new int[] { i - 1, j });
							flag = true;

						}
					}

					if (i + 1 <= N) {
						if (temp == list[i + 1].get(j)) {
							remove_list.push(new int[] { i, j });
							remove_list.push(new int[] { i + 1, j });
							flag = true;

						}
					}

				}

			}
			while (!remove_list.isEmpty()) {
				int item[] = remove_list.pop();
				int ni = item[0];
				int nj = item[1];
				list[ni].set(nj, 0);
			}

			if (flag == false) {
				float sum_v = 0;
				float cnt = 0;
				for (i = 1; i < N + 1; i++) {
					for (j = 0; j < M; j++) {
						if (list[i].get(j) != 0) {
							cnt += 1;
						}
						sum_v += list[i].get(j);
					}
				}
				float ave = (sum_v / cnt);
				for (i = 1; i < N + 1; i++) {
					for (j = 0; j < M; j++) {
						int new_value = list[i].get(j);
						if (new_value == 0)
							continue;
						if (new_value < ave) {
							list[i].set(j, new_value + 1);
						} else if (new_value > ave) {
							list[i].set(j, new_value - 1);
						}
					}
				}
			}
		}

		int result = 0;
		for (i = 1; i < N + 1; i++) {
			for (j = 0; j < M; j++) {
				result += list[i].get(j);
			}
		}
		System.out.println(result);
	}

}