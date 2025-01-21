import java.io.*;
import java.util.*;

public class Main {
    
    static class Trie{
        Node root;
        
        Trie(){
            this.root = new Node();
        }
        
        public void insert(String words){
            Node curnode = this.root;
            for(int i=0; i<words.length(); i++){
                Character a = words.charAt(i);
                if(!curnode.child.containsKey(a)) curnode.child.put(a, new Node());
                curnode = curnode.child.get(a);
            }
            curnode.endWord = true;
        }
        
        public int search(String words){
            Node curnode = this.root;
            curnode = curnode.child.get(words.charAt(0));
            int count = 1;
            for(int i=1; i<words.length(); i++){
                Character a = words.charAt(i);
                if(!curnode.child.containsKey(a)) return 0;
                if(curnode.child.size() > 1 || curnode.endWord) count++;
                curnode = curnode.child.get(a);
            }
            return count;
        }
        
    }
    
    static class Node{
        HashMap<Character, Node> child;
        boolean endWord;
        
        Node(){
            this.child = new HashMap<>();
            this.endWord = false;
        }
    }
    
    
    static int N;
    static Trie trie;
    static String w[];
    
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        while((input = br.readLine()) != null){
            N = Integer.parseInt(input);
            trie = new Trie();
            w = new String[N];
            for(int i=0; i<N; i++){
                String s = br.readLine();
                trie.insert(s);
                w[i] = s;
            }
            int answer = 0;
            for(int i=0; i<N; i++){
                answer += trie.search(w[i]);
                // System.out.println("value is " + trie.search(w[i]));
            }
            System.out.printf("%.2f",(float)answer/(float)N);
            System.out.println();
        }
        br.close();
    }
}