import java.io.*;
import java.util.*;

public class Main {

    static int w, b, allPoint, allCount, longLength;
    static String dummy, longString;
    static int dx[] = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};
    static int dy[] = new int[] {0, 1, 1, 1, 0, -1, -1, -1};
    static Trie trie;
    static Character dice[][];
    static HashMap<String, Integer> checkword;
    static PriorityQueue<String> pq;

    static class Node {
        HashMap<Character, Node> child;
        boolean endWord;

        Node() {
            this.child = new HashMap<>();
            this.endWord = false;
        }
    }

    static class Trie {
        Node root;

        Trie() {
            this.root = new Node();
        }

        public void insert(String words) {
            Node curnode = this.root;

            for (int i = 0; i < words.length(); i++) {
                Character a = words.charAt(i);

                if (!curnode.child.containsKey(a)) {
                    curnode.child.put(a, new Node());
                }
                curnode = curnode.child.get(a);
            }
            curnode.endWord = true;
        }

        public int search(String words) {
            Node curnode = this.root;

            for (int i = 0; i < words.length(); i++) {
                Character a = words.charAt(i);

                if (!curnode.child.containsKey(a)) return 1;
                curnode = curnode.child.get(a);
            }
            if (!curnode.endWord) return 2;
            return 3;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        w = Integer.parseInt(br.readLine());
        trie = new Trie();
        for (int i = 0; i < w; i++) {
            String s = br.readLine();
            trie.insert(s);
        }
        dummy = br.readLine();

        b = Integer.parseInt(br.readLine());
        for (int c = 0; c < b; c++) {
            dice = new Character[4][4];
            allPoint = 0;
            allCount = 0;
            longLength = 0;
            longString = "";
            for (int i = 0; i < 4; i++) {
                String ss = br.readLine();
                for (int j = 0; j < 4; j++) {
                    dice[i][j] = ss.charAt(j);
                }
            }
            checkword = new HashMap<>();
            pq = new PriorityQueue<>();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    boolean[][] visited = new boolean[4][4];
                    dfs(i, j, "" + dice[i][j], visited);
                }
            }
            longString = pq.poll();
            sb.append(allPoint).append(" ").append(longString).append(" ").append(allCount);
            sb.append("\n");
            if (c != b - 1) dummy = br.readLine();
        }
        System.out.println(sb);
    }

    static void dfs(int x, int y, String word, boolean[][] visited) {
        if (trie.search(word) == 1) return; // Not a valid prefix

        if (trie.search(word) == 3 && !checkword.containsKey(word)) {
            checkword.put(word, 1);
            addPoint(word);
            allCount++;

            if (longLength < word.length()) {
                longLength = word.length();
                pq.clear();
                pq.add(word);
            } else if (longLength == word.length()) {
                pq.add(word);
            }
        }

        visited[x][y] = true;

        for (int k = 0; k < 8; k++) {
            int ni = x + dx[k];
            int nj = y + dy[k];

            if (ni < 0 || nj < 0 || ni >= 4 || nj >= 4 || visited[ni][nj]) continue;

            dfs(ni, nj, word + dice[ni][nj], visited);
        }

        visited[x][y] = false; // Backtrack
    }

    static void addPoint(String nv) {
        if (nv.length() == 3 || nv.length() == 4) {
            allPoint += 1;
        } else if (nv.length() == 5) {
            allPoint += 2;
        } else if (nv.length() == 6) {
            allPoint += 3;
        } else if (nv.length() == 7) {
            allPoint += 5;
        } else if (nv.length() == 8) {
            allPoint += 11;
        }
    }
}