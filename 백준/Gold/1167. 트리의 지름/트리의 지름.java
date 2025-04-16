import java.io.*;
import java.util.*;

public class Main{
    
    static int V;
    static ArrayList<node> nodeList[];
    static int maxV, maxD;
    
    static class node{
        int next, cost;
        
        public node(int next, int cost){
            this.next = next;
            this.cost = cost;
        }
    }
    
    static class info{
        int pos, value;
        
        public info(int pos, int value){
            this.pos = pos;
            this.value = value;
        }
    }
    
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        V = Integer.parseInt(br.readLine());
        nodeList = new ArrayList[V+1];
        for(int i=0; i<V+1; i++){
            nodeList[i] = new ArrayList<>();
        }
        
        for(int i=0; i<V; i++){
            st = new StringTokenizer(br.readLine());
            int cur = Integer.parseInt(st.nextToken());
            while(true){
                int next = Integer.parseInt(st.nextToken());
                if(next == -1) break;
                int cost = Integer.parseInt(st.nextToken());
                nodeList[cur].add(new node(next, cost));
            }
        }
        maxV = 0;
        maxD = 0;
        dfs(1);
        
        maxD = 0;
        dfs(maxV);
        
        System.out.println(maxD);
    }
    
    static void dfs(int k){
        boolean visited[] = new boolean[V+1];
        Stack<info> stack = new Stack<>();
        stack.add(new info(k, 0));
        
        while(!stack.isEmpty()){
            info curinfo = stack.pop();
            int curpos = curinfo.pos;
            int curvalue = curinfo.value;
            visited[curpos] = true;
            for(node n : nodeList[curpos]){
                if(visited[n.next]) continue;
                stack.add(new info(n.next, curvalue+n.cost));
                if(curvalue+n.cost > maxD){
                    maxD = curvalue+n.cost;
                    maxV = n.next;
                }
            }
        }
    }
}