import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int max[][] = new int[N + 1][N + 1];
        int INF = 1_000_000 * N + 1;

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (i == j) continue;
                max[i][j] = INF;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            max[start][end] = cost;
        }

        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (max[i][k] >= INF || max[k][j] >= INF) continue;
                    // i에서 k를 거쳐 j로 가는 경로 중 허들의 최댓값을 갱신
                    max[i][j] = Math.min(max[i][j], Math.max(max[i][k], max[k][j]));
                }
            }
        }

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if (max[start][end] >= INF) {
                sb.append(-1).append("\n");
            } else {
                sb.append(max[start][end]).append("\n");
            }
        }
        System.out.println(sb);
    }
}