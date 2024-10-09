import java.io.*;
import java.util.*;

public class Main
{
	static int N,M,d[];
    static StringBuilder sb;
    static ArrayList<ArrayList<Integer>> lst;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
	    
	    st = new StringTokenizer(br.readLine());
	    N = Integer.parseInt(st.nextToken());
	    M = Integer.parseInt(st.nextToken());
	    lst = new ArrayList<ArrayList<Integer>>();
	    for(int i=0; i<N+1; i++){
	        lst.add(new ArrayList<Integer>());
	    }
	    d = new int[N+1];
	    for(int i=0; i<M; i++){
	        st = new StringTokenizer(br.readLine());
	        int one = Integer.parseInt(st.nextToken());
	        int two = Integer.parseInt(st.nextToken());
	        lst.get(one).add(two);
	        d[two]++;
	    }
        topo();
	    System.out.println(sb);
	    
	}
	
	static void topo(){
        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<N+1; i++){
            if(d[i] == 0){
                q.add(i);
            }
        }
        while(!q.isEmpty()){
            int cur = q.poll();
            sb.append(cur + " ");
            for(int i=0; i<lst.get(cur).size(); i++){
                d[lst.get(cur).get(i)]--;
                if(d[lst.get(cur).get(i)] == 0){
                    q.add(lst.get(cur).get(i));
                }
            }
        }
	}

}