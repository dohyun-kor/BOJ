import java.io.*;
import java.util.*;

public class Main
{
	static int N,result;
	static int visited[][];
	static int dx[] = {1,1,-1,-1,0,1,0,-1};
	static int dy[] = {1,-1,-1,1,1,0,-1,0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		result = 0;
		visited = new int[N][N];
		dfs(0,0);
		System.out.println(result);
	}

	static void dfs(int depth, int start) {
		if(depth == N) {
			result++;
			return;
		}
		
		if(start > depth) return;
		
		for(int i=start; i<N; i++) {
			for(int j=0; j<N; j++) {
		        if(i>depth) return;
				if(visited[i][j] > 0) continue;
				check(i,j,1);
				dfs(depth+1, i+1);
				check(i,j,-1);
			}
		}
	}

	static void check(int x, int y, int status) {
		for(int l=1; l<N; l++) {
			for(int k=0; k<8; k++) {
				int ni = x + dx[k]*l;
				int nj = y + dy[k]*l;
				if(ni<0 || N<=ni || nj<0 || N<=nj) continue;
				visited[ni][nj] += status;
			}
		}
	}
}