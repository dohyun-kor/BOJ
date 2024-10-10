import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static class home {
		int s, e, v;

		home(int s, int e, int v) {
			this.s = s;
			this.e = e;
			this.v = v;
		}
	}

	static int p[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<home> homeList = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			homeList.add(new home(s, e, v));
		}

		p = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			p[i] = i;
		}

		Collections.sort(homeList, (i1, i2) -> {
			return (i1.v - i2.v);
		});
		long sum = 0;
		int max_v = 0;
		for (home h1 : homeList) {
			if (find(h1.s) != find(h1.e)) {
				unionf(h1.s, h1.e);
				sum += h1.v;
				max_v = h1.v;
			}
		}
		System.out.println(sum - max_v);

	}

	static int find(int x) {
		if (p[x] == x)
			return p[x];
		return p[x] = find(p[x]);
	}

	static void unionf(int x, int y) {
		x = find(x);
		y = find(y);
		if (x < y)
			p[y] = x;
		else
			p[x] = y;
	}

}