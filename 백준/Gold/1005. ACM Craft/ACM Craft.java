import java.io.*;
import java.util.*;


public class Main
{
    static int N,K,D[],indegree[],dist[],W;
    static ArrayList<ArrayList<Integer>> lst;
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<T+1; tc++){
		    st = new StringTokenizer(br.readLine());
		    
		    N = Integer.parseInt(st.nextToken());
		    K = Integer.parseInt(st.nextToken());
		    lst = new ArrayList<ArrayList<Integer>>();
		    for(int i=0; i<N+1; i++){
		        lst.add(new ArrayList<Integer>());
		    }
		    D = new int[N+1];
		    indegree = new int[N+1];
		    dist = new int[N+1];
		    st = new StringTokenizer(br.readLine());
		    for(int i=1; i<N+1; i++){
		        D[i] = Integer.parseInt(st.nextToken());
		    }
		    for(int i=0; i<K; i++){
		        st = new StringTokenizer(br.readLine());
		        int x = Integer.parseInt(st.nextToken());
		        int y = Integer.parseInt(st.nextToken());
		        lst.get(x).add(y);
		        indegree[y]++;
		    }
		    W = Integer.parseInt(br.readLine());
		    topo();
		    System.out.println(dist[W]);
		}
	}
	
	static void topo(){
	    Queue<Integer> q = new LinkedList<>();
	    for(int i=1; i<N+1; i++){
	        if(indegree[i] == 0){
	            q.add(i);
	            dist[i] = D[i];
	        }
	    }
	    while(!q.isEmpty()){
	        int cur = q.poll();
	        for(int i=0; i<lst.get(cur).size(); i++){
	            indegree[lst.get(cur).get(i)]--;
                dist[lst.get(cur).get(i)] = Math.max(dist[cur] + D[lst.get(cur).get(i)], dist[lst.get(cur).get(i)]);
	            if(indegree[lst.get(cur).get(i)] == 0){
	                q.add(lst.get(cur).get(i));
	            }
	        }
	    }
	}
}