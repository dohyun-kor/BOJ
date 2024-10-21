import java.io.*;
import java.util.*;

public class Main
{
	static int w,h;
	static char map[][];
	static Queue<fire> fireList;
	static Queue<me> my;
	static int starti, startj;
	static boolean visited[][];
	static boolean visited2[][];
	static int dx[] = {0,1,0,-1};
	static int dy[] = {1,0,-1,0};

	static class fire {
		int x,y,time;
		fire(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

	static class me {
		int x,y,time;
		me(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<T+1; tc++) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			map = new char[h][w];
			fireList = new ArrayDeque<>();
			my = new ArrayDeque<>();
			starti = 0;
			startj = 0;
			visited = new boolean[h][w];
			visited2 = new boolean[h][w];

			for(int i=0; i<h; i++) {
				String s = br.readLine();
				for(int j=0; j<w; j++) {
					map[i][j] = s.charAt(j);
					if(map[i][j] == '*') {
						fireList.add(new fire(i, j, 0));
						visited[i][j] = true;
					}
					if(map[i][j] == '@') {
						starti = i;
						startj = j;
					}
				}
			}

			int answer = escape();
			if(answer == 0) {
				System.out.println("IMPOSSIBLE");
			} else {
				System.out.println(answer);
			}

		}

	}

	static int escape() {
		my.add(new me(starti, startj, 0));
		int curtime = 0;
		while(!my.isEmpty()) {
			if(!fireList.isEmpty()) {
				while(fireList.peek().time <= curtime) {
					fire f = fireList.poll();
					int cx = f.x;
					int cy = f.y;
					int t = f.time;
					for(int k=0; k<4; k++) {
						int ni = cx + dx[k];
						int nj = cy + dy[k];
						if(ni<0 || h<=ni || nj<0 || w<=nj || visited[ni][nj] || map[ni][nj] == '#') continue;
						visited[ni][nj] = true;
						fireList.add(new fire(ni,nj,t+1));
					}
					if(fireList.isEmpty()) break;
				}
			}

			while(my.peek().time <= curtime) {
				me curme = my.poll();
				int mex = curme.x;
				int mey = curme.y;
				int met = curme.time;
				for(int k=0; k<4; k++) {
					int ni = mex + dx[k];
					int nj = mey + dy[k];
					if(ni<0 || h<=ni || nj<0 || w<=nj) return met+1;
					if(visited[ni][nj] || visited2[ni][nj] || map[ni][nj] == '#') continue;
					visited2[ni][nj] = true;
					my.add(new me(ni, nj, met+1));
				}
				if(my.isEmpty()) return 0;
			}
			curtime++;
		}
		return 0;
	}
}