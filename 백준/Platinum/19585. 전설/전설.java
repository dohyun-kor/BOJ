import java.io.*;
import java.util.*;

public class Main {
    
    static int C,N,Q;
    static int max = 1000*4100;
    static int trie[][] = new int[max][26];
    static int chk[] = new int[max];
    static HashMap<String, Integer> name;
    static int ROOT = 1;
    static int unused = 2;
    
    public static void main(String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        for(int i=0; i<max; i++){
            Arrays.fill(trie[i], -1);
        }
        name = new HashMap<>();
        
        for(int i=0; i<C; i++){
            String curcolor = br.readLine();
            insert(curcolor);
        }
        for(int i=0; i<N; i++){
            String curname = br.readLine();
            name.put(curname, 1);
        }
        
        Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<Q; i++){
            String findword = br.readLine();
            String answer = "No";
            
            if(search(findword)) answer = "Yes";
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
    
    static void insert(String colors){
        int cur = ROOT;
        
        for(int i=0; i<colors.length(); i++){
            int c = cToi(colors.charAt(i));
            if(trie[cur][c] == -1){
                trie[cur][c] = unused++;
            }
            cur = trie[cur][c];
        }
        chk[cur] = 1;
    }
    
    static boolean search(String colors){
        int cur = ROOT;
        
        for(int i=0; i<colors.length(); i++){
            int c = cToi(colors.charAt(i));
            if(trie[cur][c] == -1) return false;
            cur = trie[cur][c];
            if(chk[cur]==1){
                String other = colors.substring(i+1, colors.length());
                if(name.containsKey(other)) return true;
            }
        }
        return false;
    }
    
    static int cToi(Character cc){
        return cc-'a';
    }
}