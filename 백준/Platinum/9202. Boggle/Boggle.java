import java.io.*;
import java.util.*;

public class Main {

    static int w, b, allPoint, allCount, longLength;
    static String dummy, longString;
    static final int dx[] = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int dy[] = {0, 1, 1, 1, 0, -1, -1, -1};
    static Trie trie;
    static Character[][] dice;
    static HashSet<String> foundWords;

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

        public void insert(String word) {
            Node curNode = root;
            for (char ch : word.toCharArray()) {
                curNode = curNode.child.computeIfAbsent(ch, k -> new Node());
            }
            curNode.endWord = true;
        }

        public Node getNode(String prefix) {
            Node curNode = root;
            for (char ch : prefix.toCharArray()) {
                curNode = curNode.child.get(ch);
                if (curNode == null) return null;
            }
            return curNode;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        w = Integer.parseInt(br.readLine());
        trie = new Trie();
        for (int i = 0; i < w; i++) {
            trie.insert(br.readLine());
        }
        dummy = br.readLine();

        b = Integer.parseInt(br.readLine());
        for (int c = 0; c < b; c++) {
            dice = new Character[4][4];
            allPoint = 0;
            allCount = 0;
            longLength = 0;
            longString = "";
            foundWords = new HashSet<>();

            for (int i = 0; i < 4; i++) {
                String row = br.readLine();
                for (int j = 0; j < 4; j++) {
                    dice[i][j] = row.charAt(j);
                }
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    dfs(i, j, "" + dice[i][j], new boolean[4][4], trie.root.child.get(dice[i][j]));
                }
            }

            sb.append(allPoint).append(" ").append(longString).append(" ").append(allCount).append("\n");
            if (c != b - 1) dummy = br.readLine();
        }

        System.out.print(sb);
    }

    static void dfs(int x, int y, String word, boolean[][] visited, Node curNode) {
        if (curNode == null) return; // No valid prefix

        if (curNode.endWord && foundWords.add(word)) {
            addPoint(word);
            allCount++;
            if (word.length() > longLength || (word.length() == longLength && word.compareTo(longString) < 0)) {
                longLength = word.length();
                longString = word;
            }
        }

        visited[x][y] = true;

        for (int dir = 0; dir < 8; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx >= 0 && ny >= 0 && nx < 4 && ny < 4 && !visited[nx][ny]) {
                dfs(nx, ny, word + dice[nx][ny], visited, curNode.child.get(dice[nx][ny]));
            }
        }

        visited[x][y] = false; // Backtrack
    }

    static void addPoint(String word) {
        switch (word.length()) {
            case 3: case 4: allPoint += 1; break;
            case 5: allPoint += 2; break;
            case 6: allPoint += 3; break;
            case 7: allPoint += 5; break;
            case 8: allPoint += 11; break;
        }
    }
}
