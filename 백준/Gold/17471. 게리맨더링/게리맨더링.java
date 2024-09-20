import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

	static int map[][];
	static int popul[];
	static int N;
	static boolean visited[];
	static int min_v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		popul = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) {
			popul[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int j = Integer.parseInt(st.nextToken());
			for (int k = 0; k < j; k++) {
				map[i + 1][k] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[N + 1];
		ArrayList<Integer> check = new ArrayList<>();
		min_v = 99999;
		for (int i = 1; i < N + 1; i++) {
			check.add(i);
			visited[i] = true;
			bfs(i, check, visited);
			visited[i] = false;
			check.clear();
		}

		if (min_v == 99999) {
			min_v = -1;
		}

		System.out.println(min_v);

	}

	static void bfs(int i, ArrayList<Integer> check, boolean visited[]) {
		if (check.size() == N ) {
			return;
		}
		ArrayList<Integer> others = new ArrayList<Integer>();
		for (int ii = 1; ii < N + 1; ii++) {
			if (!check.contains(ii)) {
				others.add(ii);
			}
		}

		if (checking(check) && checking(others)) {
			min_v = Math.min(min_v, plus(check));
		}

		for (int j = i; j < N + 1; j++) {
			if (visited[j] == false) {
				check.add(j);
				visited[j] = true;
				bfs(j, check, visited);
				visited[j] = false;
				check.remove(Integer.valueOf(j));
			}
		}

	}

	static boolean checking(ArrayList<Integer> check) {
		boolean cvisit[] = new boolean[N + 1];
		int other[] = new int[N - check.size()];
		int index = 0;
		for (int i = 1; i < N + 1; i++) {
			if (!check.contains(i)) {
				other[index++] = i;
			}
		}
		ArrayList<Integer> arr = new ArrayList<>();
		Queue<Integer> q = new LinkedList<>();
		cvisit[other[0]] = true;
		q.add(other[0]);
		while (!q.isEmpty()) {
			int cur = q.poll();
			arr.add(cur);
			for (int i = 0; i < N + 1; i++) {
				if (map[cur][i] == 0 || cvisit[map[cur][i]] == true || check.contains(map[cur][i]))
					continue;
				cvisit[map[cur][i]] = true;
				q.add(map[cur][i]);
			}
		}

		if (arr.size() == other.length) {
			return true;
		} else {
			return false;
		}
	}

	static int plus(ArrayList<Integer> check) {
		int result1 = 0;
		int result2 = 0;
		for (int i = 1; i < N + 1; i++) {
			if (check.contains(i)) {
				result1 += popul[i];
			} else {
				result2 += popul[i];
			}
		}

		return Math.abs(result1 - result2);
	}

}