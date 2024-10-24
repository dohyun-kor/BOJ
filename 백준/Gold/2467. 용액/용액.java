import java.io.*;
import java.util.*;

public class Main
{
    static int N, minvalue;
    static int numList[];
    static int finalone, finaltwo;
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	    
	    N = Integer.parseInt(br.readLine());
	    
	    numList = new int[N];
	    minvalue = Integer.MAX_VALUE;
	    
	    st = new StringTokenizer(br.readLine());
	    finalone = 0;
	    finaltwo = 0;
	    for(int i=0; i<N; i++){
	        numList[i] = Integer.parseInt(st.nextToken());
	    }
	    
	    for(int i=0; i<N; i++){
	        int target = numList[i];
            binary(target, i);
            if(finalone + finaltwo == 0){
                System.out.println(finalone + " " + finaltwo);
                return;
            }
	    }
	    
	    System.out.println(finalone + " " + finaltwo);
	}
	

	
	static void binary(int target, int starti){
	    int start = starti;
	    int end = N-1;
	    int min_v = Integer.MAX_VALUE;
	    int min_num = target;
	    while(start<=end){
	        int mid = (start+end) / 2;
	        int value = numList[mid] + target;
	        if(mid == starti){
	            start = mid+1;
	            continue;
	        }
	        if(value > 0){
	            end = mid-1;
	            if(Math.abs(numList[mid] + target) < min_v){
	                min_v = Math.abs(numList[mid] + target);
	                min_num = numList[mid];
	            }
	        }
	        else if(value < 0){
	            start = mid+1;
	            if(Math.abs(numList[mid] + target) < min_v){
	                min_v = Math.abs(numList[mid] + target);
	                min_num = numList[mid];
	            }
	        }
	        else{
	            min_num = numList[mid];
    	        finalone = Math.min(target, min_num);
	            finaltwo = Math.max(target, min_num);
	            return;
	        }
	    }
	    
	    if(min_v < minvalue){
	        minvalue = min_v;
	        finalone = Math.min(target, min_num);
	        finaltwo = Math.max(target, min_num);
	    }
	}


}