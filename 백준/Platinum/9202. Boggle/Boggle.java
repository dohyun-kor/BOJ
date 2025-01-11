import java.io.*;
import java.util.*;

public class Main {

    static int w, b, allPoint, allCount, longLength;
    static String dummy, longString;
    static int dx[] = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};
    static int dy[] = new int[] {0, 1, 1, 1, 0, -1, -1, -1};
    static Trie trie;
    static Character dice[][];
    static HashSet<String> checkword;
    static PriorityQueue<String> pq;

    static class Trie {
        Node root;

        Trie() {
            this.root = new Node();
        }

        public void insert(String words) {
            Node currentNode = this.root;
            for (int i = 0; i < words.length(); i++) {
                char c = words.charAt(i);
                currentNode.child.putIfAbsent(c, new Node());
                currentNode = currentNode.child.get(c);
            }
            currentNode.endWord = true;
        }

        public int search(String words) {
            Node currentNode = this.root;
            for (int i = 0; i < words.length(); i++) {
                char c = words.charAt(i);
                if (!currentNode.child.containsKey(c)) return 1; // 단어 경로 없음
                currentNode = currentNode.child.get(c);
            }
            return currentNode.endWord ? 3 : 2; // 3: 완전 단어, 2: 경로만 존재
        }
    }

    static class Node {
        Map<Character, Node> child = new HashMap<>();
        boolean endWord = false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        w = Integer.parseInt(br.readLine());
        trie = new Trie();

        for (int i = 0; i < w; i++) {
            trie.insert(br.readLine());
        }
        dummy = br.readLine(); // 빈 줄

        b = Integer.parseInt(br.readLine());
        for (int c = 0; c < b; c++) {
            dice = new Character[4][4];
            allPoint = 0;
            allCount = 0;
            longLength = 0;
            longString = "";
            
            for (int i = 0; i < 4; i++) {
                String line = br.readLine();
                for (int j = 0; j < 4; j++) {
                    dice[i][j] = line.charAt(j);
                }
            }

            checkword = new HashSet<>();
            pq = new PriorityQueue<>();

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    bfs(i, j);
                }
            }

            longString = pq.isEmpty() ? "" : pq.poll();
            System.out.println(allPoint + " " + longString + " " + allCount);

            if (c != b - 1) dummy = br.readLine(); // 빈 줄 처리
        }
    }

    static void bfs(int x, int y) {
        Queue<Info> q = new LinkedList<>();
        boolean[][] visited = new boolean[4][4];
        visited[x][y] = true;

        String startWord = "" + dice[x][y];
        q.add(new Info(x, y, startWord, visited));

        if (trie.search(startWord) == 3 && checkword.add(startWord)) {
            addPoint(startWord);
            allCount++;
            updateLongestWord(startWord);
        }

        while (!q.isEmpty()) {
            Info cur = q.poll();

            for (int k = 0; k < 8; k++) {
                int ni = cur.x + dx[k];
                int nj = cur.y + dy[k];

                if (ni < 0 || nj < 0 || ni >= 4 || nj >= 4 || cur.visited[ni][nj]) continue;

                String nextWord = cur.word + dice[ni][nj];
                if (trie.search(nextWord) == 1) continue; // 유효하지 않은 경로

                boolean[][] newVisited = copyVisited(cur.visited);
                newVisited[ni][nj] = true;
                q.add(new Info(ni, nj, nextWord, newVisited));

                if (trie.search(nextWord) == 3 && checkword.add(nextWord)) {
                    addPoint(nextWord);
                    allCount++;
                    updateLongestWord(nextWord);
                }
            }
        }
    }

    static void updateLongestWord(String word) {
        if (word.length() > longLength) {
            longLength = word.length();
            pq.clear();
            pq.add(word);
        } else if (word.length() == longLength) {
            pq.add(word);
        }
    }

    static boolean[][] copyVisited(boolean[][] visited) {
        boolean[][] newVisited = new boolean[4][4];
        for (int i = 0; i < 4; i++) {
            System.arraycopy(visited[i], 0, newVisited[i], 0, 4);
        }
        return newVisited;
    }

    static void addPoint(String word) {
        switch (word.length()) {
            case 3:
            case 4:
                allPoint += 1;
                break;
            case 5:
                allPoint += 2;
                break;
            case 6:
                allPoint += 3;
                break;
            case 7:
                allPoint += 5;
                break;
            case 8:
                allPoint += 11;
                break;
        }
    }

    static class Info {
        int x, y;
        String word;
        boolean[][] visited;

        Info(int x, int y, String word, boolean[][] visited) {
            this.x = x;
            this.y = y;
            this.word = word;
            this.visited = visited;
        }
    }
}