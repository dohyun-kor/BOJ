import java.io.*;
import java.util.*;


public class Main
{
    static int N,M,indegree[];
    static ArrayList<ArrayList<Integer>> lst;
    static int result[][];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        lst = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<N+1; i++){
            lst.add(new ArrayList<Integer>());
        }
        indegree = new int[N+1];
        
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            for(int j=0; j<count; j++){
                lst.get(b).add(a);
                indegree[a]++;
            }
        }
        result = new int[N+1][N+1];
        topo();
        for(int i=1; i<N+1; i++){
            if(result[N][i] == 0) continue;
            System.out.println(i + " " + result[N][i]);
        }
	}
	
	static void topo(){
	    Queue<Integer> q = new LinkedList<>();
	    for(int i=1; i<N+1; i++){
	        if(indegree[i] == 0){
	            result[i][i]++;
	            q.add(i);
	        }
	    }
	    
	    while(!q.isEmpty()){
	        int cur = q.poll();
	        for(int i=0; i<lst.get(cur).size(); i++){
	            int next = lst.get(cur).get(i);
	            indegree[next]--;
	            for(int j=1; j<N+1; j++){
	                if(result[cur][j] == 0) continue;
	                result[next][j] += result[cur][j];
	            }
	            if(indegree[next] == 0){
	                q.add(next);
	            }
	        }
	    }
	    
	}
}