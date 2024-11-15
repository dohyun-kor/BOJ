import java.io.*;
import java.util.*;

public class Main {

	static int N, map[][], answer, answer2;
	static int visited[][];
	static int dx[] = {-1, 1, 1, -1};
	static int dy[] = {1, 1, -1, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		answer = 0;
		answer2 = 0;
		dfs(0,0,0);
		dfs2(0,1,0);
		System.out.println(answer+answer2);
	}

	static void dfs(int x, int y, int count) {
		if(y>=N) {
			x++;
			if(x>=N) {
				answer = Math.max(answer, count);
				return;
			}
			if(x%2 == 0) {
				y = 0;
			} else {
				y = 1;
			}
		}

		if(visited[x][y] == 0 && map[x][y] == 1) {
			makevisit(x,y,1);
			dfs(x,y+2,count+1);
			makevisit(x,y,-1);
		}

		dfs(x,y+2,count);
	}

	static void dfs2(int x, int y, int count) {
		if(y>=N) {
			x++;
			if(x>=N) {
				answer2 = Math.max(answer2, count);
				return;
			}
			if(x%2 == 0) {
				y = 1;
			} else {
				y = 0;
			}
		}


		if(visited[x][y] == 0 && map[x][y] == 1) {
			makevisit(x,y,1);
			dfs2(x,y+2,count+1);
			makevisit(x,y,-1);
		}

		dfs2(x,y+2,count);
	}

	static void makevisit(int a, int b, int c) {
		for(int k=0; k<4; k++) {
			for(int l=0; l<N; l++) {
				int ni = a + dx[k] * l;
				int nj = b + dy[k] * l;
				if(ni<0 || N<=ni || nj<0 || N<=nj) continue;
				visited[ni][nj] += c;
			}
		}
	}
}