import java.io.*;
import java.util.*;

public class Main {

	static int N, M, depth[], parent[][];
	static ArrayList<Integer> list[];
	static int MAXV = 17;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		list = new ArrayList[N+1];
		depth = new int[N+1];
		parent = new int[N+1][MAXV];


		for(int i=0; i<N+1; i++) {
			list[i] = new ArrayList<>();
		}
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list[p].add(c);
			list[c].add(p);
		}

		dfs();
		sparseTable();

		M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(lca(a,b)).append("\n");
		}
		System.out.println(sb);

	}

	static void dfs() {
		Stack<int[]> stack = new Stack<>();
		stack.add(new int[] {1,1});
		parent[1][0] = 1;
		while(!stack.isEmpty()) {
			int cur[] = stack.pop();
			int point = cur[0];
			int curdepth = cur[1];
			depth[point] = curdepth;
			for(int next : list[point]) {
			    if(parent[next][0] != 0) continue;
				stack.add(new int[] {next, curdepth+1});
				parent[next][0] = point;
			}
		}
	}

	static void sparseTable() {
		for(int i=1; i<MAXV; i++) {
			for(int node=1; node<N+1; node++) {
				parent[node][i] = parent[parent[node][i-1]][i-1];
			}
		}
	}

	static int lca(int a, int b) {
		if(depth[a] < depth[b]) {
			int tempt = a;
			a = b;
			b = tempt;
		}

		for(int i=MAXV-1; i>=0; i--) {
			if(depth[a] - depth[b] >= (1<<i)) {
				a = parent[a][i];
			}
		}
		if(a==b) return a;

		for(int i=MAXV-1; i>=0; i--) {
			if(parent[a][i] != parent[b][i]) {
				a = parent[a][i];
				b = parent[b][i];
			}
		}
		return parent[a][0];
	}
}