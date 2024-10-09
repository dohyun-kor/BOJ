import java.io.*;
import java.util.*;

public class Main
{
	static int N,map[][],si,sj;
	static int dx[] = {-1,0,0,1};
	static int dy[] = {0,-1,1,0};
	static int power;
	static int feed;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					si = i;
					sj = j;
				}
			}
		}
		int result = 0;
		power = 2;
		feed = 0;
		while(true) {
			int add = bfs();
			if(add == 0) break;
			result += add;
		}
		System.out.println(result);
	}

	static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		boolean visited[][] = new boolean[N][N];
		q.add(new int[] {si, sj, 0});
		visited[si][sj] = true;
		int min_time = 9999;
		int min_i = N;
		int min_j = N;
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			int curx = cur[0];
			int cury = cur[1];
			int time = cur[2];
			if(time > min_time) {
				break;
			}
			if(map[curx][cury] > 0 && map[curx][cury] < 9) {
				if(map[curx][cury] < power && min_time == 9999) {
					min_time = time;
				}
				if(map[curx][cury] < power && min_time == time) {
					if(min_i > curx) {
						min_i = curx;
						min_j = cury;
					}
					if(min_i == curx) {
						if(min_j > cury) {
							min_i = curx;
							min_j = cury;
						}
					}
				}
			}
			for(int k=0; k<4; k++) {
				int ni = curx + dx[k];
				int nj = cury + dy[k];
				if(ni<0 || N<=ni || nj<0 || N<=nj || visited[ni][nj] || map[ni][nj] > power) continue;
				visited[ni][nj] = true;
				q.add(new int[] {ni,nj,time+1});
			}
		}
		if(min_time != 9999) {
			map[min_i][min_j] = 9;
			map[si][sj] = 0;
			feed++;
			if(feed == power) {
				power++;
				feed = 0;
			}
			si = min_i;
			sj = min_j;
		    return min_time;
		}
		return 0;
	}
}