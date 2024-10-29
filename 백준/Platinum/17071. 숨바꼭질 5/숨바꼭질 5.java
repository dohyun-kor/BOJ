import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static Queue<subin> subinq = new ArrayDeque<>();
    static Queue<bro> broq = new ArrayDeque<>();
    static int answer = Integer.MAX_VALUE;
    static int[][] visitedSubin; // 짝수, 홀수 초별 subin의 방문 시간 기록

    static class subin {
        int x, time;
        subin(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }

    static class bro {
        int x, time;
        bro(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visitedSubin = new int[500001][2]; // [위치][짝수초/홀수초 방문 시간]
        for (int i = 0; i <= 500000; i++) {
            Arrays.fill(visitedSubin[i], Integer.MAX_VALUE);
        }

        subinq.add(new subin(N, 0));
        broq.add(new bro(K, 0));
        visitedSubin[N][0] = 0;

        find();
        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        System.out.println(answer);
    }

    static void find() {
        int idx = 0;
        while (!broq.isEmpty()) {
            idx++;
            bro curbro = broq.poll();
            if (curbro.x > 500000) continue;

            int broParity = curbro.time % 2;

            // bro 위치에 subin이 도착해 있고 짝/홀 초가 일치하면 answer 갱신
            if (visitedSubin[curbro.x][broParity] <= curbro.time) {
                answer = Math.min(answer, curbro.time);
            }

            // subin 위치를 갱신하며 이동
            while (!subinq.isEmpty() && subinq.peek().time <= curbro.time) {
                subin cursubin = subinq.poll();
                if (cursubin.time >= answer) return;

                int subinParity = cursubin.time % 2;

                // subin의 이동
                int[] nextPos = { cursubin.x - 1, cursubin.x * 2, cursubin.x + 1 };
                for (int next : nextPos) {
                    if (next >= 0 && next <= 500000 && visitedSubin[next][(subinParity + 1) % 2] > cursubin.time + 1) {
                        visitedSubin[next][(subinParity + 1) % 2] = cursubin.time + 1;
                        subinq.add(new subin(next, cursubin.time + 1));
                    }
                }
            }

            // bro가 다음 위치로 이동
            if (curbro.x + idx <= 500000) {
                broq.add(new bro(curbro.x + idx, curbro.time + 1));
            }
        }
    }
}
