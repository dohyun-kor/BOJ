import java.io.*;
import java.util.*;

public class Main
{
	static int N, map[][], answer;
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};

	static class block {
		int x,y,dir;
		boolean added;
		block(int x, int y, int dir, boolean added) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.added = added;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int dir[] = new int[5];
		answer = Integer.MIN_VALUE;
		perm(0, dir);
		System.out.println(answer);

	}

	static void perm(int depth, int dir[]) {
		if(depth == 5) {
			answer = Math.max(answer, simulate(dir));
			return;
		}

		for(int i=0; i<4; i++) {
			dir[depth] = i;
			perm(depth+1, dir);
		}
	}

	static int simulate(int dirr[]) {
		int copymap[][] = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				copymap[i][j] = map[i][j];
			}
		}

		for(int curdir : dirr) {
			Queue<block> q = new LinkedList<>();
			boolean visited[][] = new boolean[N][N];
			if(curdir == 0 || curdir == 3) {
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(copymap[i][j] == 0) continue;
						q.add(new block(i,j,curdir,false));
						int value = copymap[i][j];
						while(!q.isEmpty()) {
							block curblock = q.poll();
							int curx = curblock.x;
							int cury = curblock.y;
							int dir = curblock.dir;
							boolean addis = curblock.added;

							int ni = curx + dx[dir];
							int nj = cury + dy[dir];

							if(ni<0 || N<=ni || nj<0 || N<=nj) continue;
							if(copymap[ni][nj] == 0) {
								q.add(new block(ni, nj, curdir, addis));
								copymap[ni][nj] = value;
								copymap[curx][cury] = 0;
								continue;
							}
							if(copymap[ni][nj] == value) {
								if(!visited[ni][nj] && !addis) {
									value *= 2;
									copymap[ni][nj] = value;
									copymap[curx][cury] = 0;
	    							visited[curx][cury] = false;
								    visited[ni][nj] = true;
									q.add(new block(ni, nj, curdir, true));
									continue;
								}
							}
							visited[curx][cury] = addis;
						}
					}
				}
			}
			else {
				for(int i=N-1; i>=0; i--) {
					for(int j=N-1; j>=0; j--) {
						if(copymap[i][j] == 0) continue;
						q.add(new block(i,j,curdir,false));
						int value = copymap[i][j];
						while(!q.isEmpty()) {
							block curblock = q.poll();
							int curx = curblock.x;
							int cury = curblock.y;
							int dir = curblock.dir;
							boolean addis = curblock.added;

							int ni = curx + dx[dir];
							int nj = cury + dy[dir];

							if(ni<0 || N<=ni || nj<0 || N<=nj) continue;
							if(copymap[ni][nj] == 0) {
								q.add(new block(ni, nj, curdir, addis));
								copymap[ni][nj] = value;
								copymap[curx][cury] = 0;
								continue;
							}
							if(copymap[ni][nj] == value) {
								if(!visited[ni][nj] && !addis) {
									value *= 2;
									copymap[ni][nj] = value;
									copymap[curx][cury] = 0;
	    							visited[curx][cury] = false;
								    visited[ni][nj] = true;
									q.add(new block(ni, nj, curdir, true));
									continue;
								}
							}
							visited[curx][cury] = addis;
						}
					}
				}
			}
		}
		int max_v = 0;
		for(int i=0; i<N; i++){
		    for(int j=0; j<N; j++){
		        if(max_v < copymap[i][j]){
		            max_v = copymap[i][j];
		        }
		    }
		}
// 		for(int i=0; i<N; i++){
// 		    System.out.println(Arrays.toString(copymap[i]));
// 		}
// 		System.out.println("---------------------");
// 		System.out.println(max_v);
		return max_v;
	}

}