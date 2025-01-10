import java.io.*;
import java.util.*;

public class Main{
    
    static ArrayList<String> numbers;
    
    static class Node{
        HashMap<Character, Node> child;
        
        Node(){
            this.child = new HashMap<>();
        }
    }
    
    static class Trie{
        Node root;
        
        Trie(){
            this.root = new Node();
        }
        
        public void insert(String word){
            Node curnode = this.root;
            for(int i=0; i<word.length(); i++){
                Character a = word.charAt(i);
                if(curnode.child.containsKey(a)){
                    curnode = curnode.child.get(a);
                }else{
                    curnode.child.put(a, new Node());
                    curnode = curnode.child.get(a);
                }
            }
        }
        public boolean search(String word){
            Node curnode = this.root;
            for(int i=0; i<word.length(); i++){
                Character a = word.charAt(i);
                if(!curnode.child.containsKey(a)) return false;
                curnode = curnode.child.get(a);
            }
            return true;
        }
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        
        for(int tc=1; tc<T+1; tc++){
            int n = Integer.parseInt(br.readLine());
            numbers = new ArrayList<>();
            for(int i=0; i<n; i++){
                String s = br.readLine();
                numbers.add(s);
            }
            
            Collections.sort(numbers, (s1, s2) ->
                Integer.compare(s2.length(), s1.length())
            );
            
            Trie trie = new Trie();
            String answer = "YES";
            for(int i=0; i<n; i++){
                if(trie.search(numbers.get(i))){
                    answer = "NO";
                    break;
                }
                trie.insert(numbers.get(i));
            }
            System.out.println(answer);
        }
    }
}