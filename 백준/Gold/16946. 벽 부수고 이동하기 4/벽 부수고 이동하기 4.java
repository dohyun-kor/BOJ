import java.io.*;
import java.util.*;

public class Main {

    static int dx[] = {0, 1, 0, -1};
    static int dy[] = {1, 0, -1, 0};
    static int N, M, map[][], result[][];
    static boolean visited[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        result = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = (s.charAt(j) - '0');
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) result[i][j] += 1;
                if (map[i][j] != 0 || visited[i][j]) continue;
                bfs(i, j);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(result[i][j] % 10);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void bfs(int starti, int startj) {
        Queue<int[]> q = new LinkedList<>();
        Set<String> uniqueCells = new HashSet<>();  // 중복 제거를 위한 Set
        q.add(new int[]{starti, startj});
        visited[starti][startj] = true;
        int cnt = 0;

        while (!q.isEmpty()) {
            int cur[] = q.poll();
            int curx = cur[0];
            int cury = cur[1];
            cnt++;

            for (int k = 0; k < 4; k++) {
                int ni = curx + dx[k];
                int nj = cury + dy[k];
                if (ni < 0 || N <= ni || nj < 0 || M <= nj || visited[ni][nj]) continue;

                if (map[ni][nj] == 1) {
                    String cellKey = ni + "," + nj;
                    uniqueCells.add(cellKey);  // 중복된 값은 알아서 처리
                    continue;
                }
                visited[ni][nj] = true;
                q.add(new int[]{ni, nj});
            }
        }
        for (String cell : uniqueCells) {
            String[] parts = cell.split(",");
            int curx = Integer.parseInt(parts[0]);
            int cury = Integer.parseInt(parts[1]);
            result[curx][cury] += cnt;
        }
    }
}
