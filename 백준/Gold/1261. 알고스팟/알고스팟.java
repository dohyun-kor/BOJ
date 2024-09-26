import java.io.*;
import java.util.*;

public class Main {
	static class node implements Comparable<node> {
		int posx, posy, cost;

		node(int posx, int posy, int cost) {
			this.posx = posx;
			this.posy = posy;
			this.cost = cost;
		}

		@Override
		public int compareTo(node n) {
			return this.cost - n.cost;
		}
	}

	static int dx[] = { 0, 1, 0, -1 };
	static int dy[] = { 1, 0, -1, 0 };
	static ArrayList<node> nodeList[][];
	static int map[][];
	static int djk[][];
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = Character.getNumericValue(s.charAt(j));
			}
		}

		nodeList = new ArrayList[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				nodeList[i][j] = new ArrayList<>();
			}
		}

		djk = new int[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(djk[i], Integer.MAX_VALUE);
		}

		bfs();
		dijkstra();
		System.out.println(djk[N - 1][M - 1]);
	}

	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { 0, 0 });

		while (!q.isEmpty()) {
			int cur[] = q.poll();
			int curx = cur[0];
			int cury = cur[1];
			if (curx == N - 1 && cury == M - 1)
				continue;
			for (int k = 0; k < 4; k++) {
				int nx = curx + dx[k];
				int ny = cury + dy[k];
				if (nx < 0 || N <= nx || ny < 0 || M <= ny)
					continue;
				boolean contains = false;
				for (node arr : nodeList[curx][cury]) {
					if (Arrays.equals(new int[] {arr.posx, arr.posy}, new int[] { nx, ny })) {
						contains = true;
						break;
					}
				}
				if (contains)
					continue;
				q.add(new int[] { nx, ny });
				nodeList[curx][cury].add(new node(nx, ny, map[nx][ny]));
			}
		}
	}

	static void dijkstra() {
		boolean visited[][] = new boolean[N][M];
		PriorityQueue<node> pq = new PriorityQueue<>();
		pq.add(new node(0, 0, 0));
		djk[0][0] = 0;

		while (!pq.isEmpty()) {
			node curnode = pq.poll();
			if (visited[curnode.posx][curnode.posy])
				continue;
			visited[curnode.posx][curnode.posy] = true;

			for (node next : nodeList[curnode.posx][curnode.posy]) {
				if (djk[next.posx][next.posy] > curnode.cost + next.cost) {
					djk[next.posx][next.posy] = curnode.cost + next.cost;
					pq.add(new node(next.posx, next.posy, djk[next.posx][next.posy]));
				}
			}
		}
	}

}