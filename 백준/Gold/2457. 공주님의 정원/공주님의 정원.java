import java.io.*;
import java.util.*;

public class Main
{
    static int N;
    static ArrayList<flower> flowerList;
    
    
    static class flower{
        int start, end;
        flower(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    
	public static void main(String[] args) throws IOException{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
	    N = Integer.parseInt(br.readLine());
	    flowerList = new ArrayList<flower>();
	    int memo = 0;
	    
	    for(int i=0; i<N; i++){
	        st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int start = a*100 + b;
            int end = c*100 + d;
            memo = Math.max(memo, end);
            flowerList.add(new flower(start, end));
	    }
	    if(memo < 1131){
	        System.out.println(0);
	        return;
	    }
	    Collections.sort(flowerList, (i1, i2) -> {
	        if(i1.start < i2.start) return -1;
	        else if(i1.start == i2.start) return i1.end - i2.end;
	        else return 1;
	    });
	    
	    System.out.println(find());
	}
	
	
	static int find(){
	    int must = 301;
	    int startp = 0;
	    int max_v = 0;
	    int cnt = 0;
	    while(true){
	       // System.out.println(startp);
	        if(flowerList.get(startp).start > must) break;
	        for(int i=startp; i<flowerList.size(); i++){
	            if(flowerList.get(i).start > must){
	                startp = i;
	                must = max_v;
	               // System.out.println(must);
	                cnt++;
	                if(must > 1131) return cnt;
	                break;
	            }
	            if(flowerList.get(i).end > max_v){
	                max_v = flowerList.get(i).end;
	                if(max_v > 1131) return cnt+1;
	            }
	        }
	    }
	    return 0;
	}
}