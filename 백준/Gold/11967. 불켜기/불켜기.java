import java.io.*;
import java.util.*;

public class Main
{
	static int N,M;
	static ArrayList<int[]> map[][];
	static int dx[] = {0,1,0,-1};
	static int dy[] = {1,0,-1,0};

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new ArrayList[N+1][N+1];
		for(int i=0; i<N+1; i++) {
			for(int j=0; j<N+1; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[x][y].add(new int[] {a,b});
		}
		System.out.println(bfs());
	}

	static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		int visited[][] = new int[N+1][N+1];
		boolean light[][] = new boolean[N+1][N+1];
		q.add(new int[] {1,1,1});
		visited[1][1] = 1;
		light[1][1] = true;
		int result = 0;
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			int curx = cur[0];
			int cury = cur[1];
			int light_n = cur[2];
			for(int[] c : map[curx][cury]) {
				if(!light[c[0]][c[1]]) {
					light[c[0]][c[1]] = true;
					visited[c[0]][c[1]] = light_n;
					light_n++;
				}
			}
			for(int k=0; k<4; k++) {
				int ni = curx + dx[k];
				int nj = cury + dy[k];
				if(ni<1 || N+1<=ni || nj<1 || N+1<=nj || visited[ni][nj]>=light_n || light[ni][nj] == false) continue;
				visited[ni][nj] = light_n;
				q.add(new int[] {ni,nj,light_n});
			}
		}
		for(int i=0; i<N+1; i++) {
			for(int j=0; j<N+1; j++) {
				if(light[i][j]) result++;
			}
		}
		return result;
	}
}