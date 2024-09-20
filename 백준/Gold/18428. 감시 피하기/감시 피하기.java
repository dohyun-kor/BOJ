import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	public static boolean finding(ArrayList<int[]> teacher, ArrayList<int[]> point, char map2[][]) {
		int dx[] = { 0, 1, 0, -1 };
		int dy[] = { 1, 0, -1, 0 };
		int N = map2.length;
		char map[][] = new char[map2.length][];
		int number = 0;
		int num = 0;
		for (int i = 0; i < map2.length; i++) {
			map[i] = map2[i].clone();
		}

		boolean flag = true;
		while (num != 3) {
			int pointt[] = point.get(num);
			num++;
			int ni = pointt[0];
			int nj = pointt[1];
			map[ni][nj] = 'O';
		}

		while (number != teacher.size()) {
			int t_point[] = teacher.get(number);
			number++;
			int ti = t_point[0];
			int tj = t_point[1];
			for (int k = 0; k < 4; k++) {
				for (int l = 1; l < 100; l++) {
					int nti = ti + dx[k] * l;
					int ntj = tj + dy[k] * l;
					if (0 <= nti && 0 <= ntj && nti < N && ntj < N) {
						if (map[nti][ntj] == 'S') {
							flag = false;
							break;
						}
						if (map[nti][ntj] == 'O') {
							break;
						}
					} else
						break;
				}
			}
		}

		return flag;

	}

	public static String result(ArrayList<int[]> teacher, ArrayList<int[]> point, char map[][]) {
		String result = "NO";
		int i, j;
		int N = map.length;
		for (i = 0; i < N; i++) {
			for (j = 0; j < N; j++) {
				if (map[i][j] == 'X') {
					point.add(new int[] { i, j });
					for (int i2 = 0; i2 < N; i2++) {
						for (int j2 = 0; j2 < N; j2++) {
							if (map[i2][j2] == 'X') {
								if (i2 != i || j2 != j) {
									point.add(new int[] { i2, j2 });
									for (int i3 = 0; i3 < N; i3++) {
										for (int j3 = 0; j3 < N; j3++) {
											if (map[i3][j3] == 'X') {
												if (i3 != i || j3 != j) {
													if (i3 != i2 || j3 != j2) {
														point.add(new int[] { i3, j3 });
														if (finding(teacher, point, map)) {
															result = "YES";
															return result;

														} else {
															point.remove(2);
														}
													}
												}
											}
										}
									}
									point.remove(1);
								}
							}
						}
					}
					point.remove(0);
				}
			}
		}

		return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<int[]> teacher = new ArrayList<>();
		ArrayList<int[]> point = new ArrayList<>();
		int N = Integer.parseInt(br.readLine());
		int i, j;
		char map[][] = new char[N][N];
		for (i = 0; i < N; i++) {
			String[] s = br.readLine().split(" ");
			for (j = 0; j < N; j++) {
				map[i][j] = s[j].charAt(0);
			}
		}

		for (i = 0; i < N; i++) {
			for (j = 0; j < N; j++) {
				if (map[i][j] == 'T') {
					teacher.add(new int[] { i, j });
				}

			}
		}

		String answer = result(teacher, point, map);
		ArrayList<int[]> asdf = new ArrayList<>();
		System.out.println(answer);
	}

}