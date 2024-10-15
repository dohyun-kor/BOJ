import java.io.*;
import java.util.*;

public class Main
{
    static int N, result;
    static ArrayList<point> pointList;
    
    static class point{
        int s,e;
        point(int s, int e){
            this.s = s;
            this.e = e;
        }
    }
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		result = 0;
		pointList = new ArrayList<point>();
		for(int i=0; i<N; i++){
		    st = new StringTokenizer(br.readLine());
		    int start = Integer.parseInt(st.nextToken());
		    int end = Integer.parseInt(st.nextToken());
            pointList.add(new point(start,end));
		}
		
		Collections.sort(pointList, (i1,i2) -> {
		   if(i1.s != i2.s) return i1.s - i2.s;
		   else return i1.e - i2.e;
		});
		int max_end = -1_000_000_001;
		
		for(int i=0; i<pointList.size(); i++){
		    point p = pointList.get(i);
		    if(p.e > max_end){
		        if(p.s > max_end){
		            result += p.e-p.s;
		        }
		        else{
		            result += p.e-max_end;
		        }
		        max_end = p.e;
		    }
		  //  System.out.println(result + " and max_end is " + max_end);
		}
		System.out.println(result);
	}
}