import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 100_000_000;
	static int N,M;
	static double[][] dist;
	static ArrayList<graph> list[];

	static class graph {
		int e;
		double v;
		graph(int e, double v) {
			this.e = e;
			this.v = v;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
	    M = Integer.parseInt(st.nextToken());
		dist = new double[N + 1][N + 1];

		for (int i = 1; i < N+1; i++) {
			Arrays.fill(dist[i], INF);
			dist[i][i] = 0;
		}


		list = new ArrayList[N+1];
		for(int i=0; i<N+1; i++) {
			list[i] = new ArrayList<>();
		}


		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			double w = Double.parseDouble(st.nextToken());

			dist[u][v] = Math.min(dist[u][v], w);
			dist[v][u] = Math.min(dist[v][u], w);

			list[u].add(new graph(v, w));
			list[v].add(new graph(u, w));

		}

		for (int k = 1; k <N+1; k++) {
			for (int i = 1; i <N+1; i++) {
				for (int j = 1; j <N+1; j++) {
					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}
        double fanswer = Double.MAX_VALUE;
		for(int n=1; n<N+1; n++) {
		    double answer = 0;
			for(int i=1; i<N+1; i++) {
				for(graph g : list[i]) {
					double curtime = 0;
					double timei = dist[n][i];
					double timeg = dist[n][g.e];
					if(timei < timeg) {
						if(timeg - timei > g.v) {
							curtime = timei+g.v;
						}
						else {
							curtime = timeg + ((g.v - (timeg-timei))/2);
						}
					}
					else if(timei > timeg) {
						if(timei - timeg > g.v) {
							curtime = timeg+g.v;
						}
						else {
							curtime = timei + ((g.v - (timei-timeg))/2);
						}
					}
					else {
                        curtime = timei + (g.v/2);
					}
					answer = Math.max(answer , curtime);
				}
			}
			fanswer = Math.min(fanswer, answer);
		}
        
    System.out.println(fanswer);
	}
}
