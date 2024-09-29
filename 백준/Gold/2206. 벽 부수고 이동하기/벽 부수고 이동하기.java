import java.io.*;
import java.util.*;

public class Main
{
	static int N,M,map[][],count[][],result;
	static int dx[] = {0, 1, 0, -1};
	static int dy[] = {1, 0, -1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = Character.getNumericValue(s.charAt(j));
			}
		}
		count = new int[N][M];
		count[0][0] = 1;
		if(N==1 && M==1) {
			System.out.println(1);
			return;
		}
		result = -1;
		bfs();
		System.out.println(result);
	}

	static void bfs() {
		boolean visited[][][] = new boolean[N][M][2];
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0,0,0});
		visited[0][0][0] = true;
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			int curx = cur[0];
			int cury = cur[1];
			int used = cur[2];
			if(curx == N-1 && cury == M-1) {
				result = count[curx][cury];
				return;
			}
			for(int k=0; k<4; k++) {
				int ni = curx + dx[k];
				int nj = cury + dy[k];
				if(ni<0 || N<=ni || nj<0 || M<=nj) continue;
				if(used == 1) {
					if(map[ni][nj] == 1 || visited[ni][nj][1]) continue;
					visited[ni][nj][1] = true;
					count[ni][nj] = count[curx][cury] + 1;
					q.add(new int[] {ni,nj,1});
				}
				else {
					if(visited[ni][nj][0]) continue;
					if(map[ni][nj] == 1) {
						if(visited[ni][nj][1]) continue;
						visited[ni][nj][1] = true;
						count[ni][nj] = count[curx][cury] + 1;
						q.add(new int[] {ni,nj,1});
					}
					else {
						visited[ni][nj][0] = true;
						count[ni][nj] = count[curx][cury] + 1;
						q.add(new int[] {ni,nj,0});
					}
				}
			}
		}
	}
}