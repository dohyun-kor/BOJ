import java.io.*;
import java.util.*;

public class Main
{
    static int N,M,map[][],graph[][],cnt,answer;
    static boolean visited[][];
    static int dx[] = {0,1,0,-1};
    static int dy[] = {1,0,-1,0};
    static ArrayList<node> nodeList[];
    
    static class node implements Comparable<node>{
        int pos,cost;
        node(int pos, int cost){
            this.pos = pos;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(node n){
            return this.cost - n.cost;
        }
    }
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		graph = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++){
		    st = new StringTokenizer(br.readLine());
		    for(int j=0; j<M; j++){
		        map[i][j] = Integer.parseInt(st.nextToken());
		    }
		}
		cnt = 1;
		for(int i=0; i<N; i++){
		    for(int j=0; j<M; j++){
		        if(visited[i][j] || map[i][j] == 0) continue;
		        bfs(i,j);
		        cnt++;
		    }
		}
		nodeList = new ArrayList[cnt];
		for(int i=0; i<cnt; i++){
		    nodeList[i] = new ArrayList<>();
		}
		
        
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(graph[i][j] == 0) continue;
                dfs(i,j);
            }
        }
        answer = 0;
        prim();
        System.out.println(answer);
        
	}
	
	static void bfs(int x, int y){
	    Queue<int[]> q = new LinkedList<>();
	    q.add(new int[] {x,y});
	    visited[x][y] = true;
	    graph[x][y] = cnt;
	    while(!q.isEmpty()){
	        int cur[] = q.poll();
	        int curx = cur[0];
	        int cury = cur[1];
	        for(int k=0; k<4; k++){
	            int ni = curx + dx[k];
	            int nj = cury + dy[k];
	            if(ni<0 || N<=ni || nj<0 || M<=nj || visited[ni][nj] || map[ni][nj] == 0) continue;
	            visited[ni][nj] = true;
	            graph[ni][nj] = cnt;
	            q.add(new int[] {ni, nj});
	        }
	    }
	}
	
	static void dfs(int x, int y){
	    int me = graph[x][y];
	    for(int k=0; k<4; k++){
	        Stack<int[]> s = new Stack<>();
	        s.add(new int[]{x,y});
	        int cost = 0;
            while(!s.isEmpty()){
                int cur[] = s.pop();
                int ni = cur[0] + dx[k];
                int nj = cur[1] + dy[k];
                if(ni<0 || N<=ni || nj<0 || M<=nj || graph[ni][nj] == me) continue;
                if(graph[ni][nj] > 0){
                    if(cost<2) break;
                    nodeList[me].add(new node(graph[ni][nj], cost));
                    break;
                }
                cost++;
                s.add(new int[]{ni, nj});
            }
	    }
	}
	
	static void prim(){
	    boolean visited2[] = new boolean[cnt];
	    PriorityQueue<node> pq = new PriorityQueue<>();
	    pq.add(new node(1,0));
	    while(!pq.isEmpty()){
	        node curnode = pq.poll();
	        if(visited2[curnode.pos]) continue;
	        visited2[curnode.pos] = true;
	        answer += curnode.cost;
	        for(node nextnode : nodeList[curnode.pos]){
	            pq.add(nextnode);
	        }
	    }
	    for(int i=1; i<cnt; i++){
	        if(visited2[i] == false){
	            answer = -1;
	            return;
	        }
	    }
	}
}