import java.io.*;
import java.util.*;

public class Main
{
    static int N, numList[];
    static ArrayList<Integer> list;
    
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		numList = new int[N];
		list = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++){
		    numList[i] = Integer.parseInt(st.nextToken());
		}
		
		list.add(numList[0]);
		
		for(int i=1; i<N; i++){
		    int num = numList[i];
		    
		    if(num > list.get(list.size()-1)){
		        list.add(num);
		        continue;
		    }
		    else if(num < list.get(list.size()-1)){
		        binary(num);
		    }
		}
		
		System.out.println(list.size());
		
	}
	
	static void binary(int target){
	    int start = 0;
	    int end = list.size()-1;
	    int answer_i = 0;
	    
	    while(start <= end){
	        int mid = (start+end)/2;
	        if(list.get(mid) > target){
	            answer_i = mid;
	            end = mid-1;
	        }
	        else if(list.get(mid) < target){
	            start = mid+1;
	        }
	        else{
	            return;
	        }
	    }
	    
	    list.set(answer_i, target);
	}
	
}