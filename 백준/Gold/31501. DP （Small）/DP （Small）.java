import java.io.*;
import java.util.*;

public class Main
{
    static int N,Q,numList[],dp[],order[];
    static int numList2[],dp2[];
    static ArrayList<Integer> list;
    static ArrayList<Integer> list2;

    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	    
	    st = new StringTokenizer(br.readLine());
	    N = Integer.parseInt(st.nextToken());
	    Q = Integer.parseInt(st.nextToken());
	    
	    dp = new int[N];
	    numList = new int[N];
	    order = new int[Q];
	    
	    st = new StringTokenizer(br.readLine());
	    for(int i=0; i<N; i++){
	        numList[i] = Integer.parseInt(st.nextToken());
	    }
	    for(int i=0; i<Q; i++){
            order[i] = Integer.parseInt(br.readLine());
	    }
	    
	    list = new ArrayList<>();
	    list.add(numList[0]);
	    dp[0] = 1;
	    
	    for(int i=1; i<N; i++){
	        int num = numList[i];
	        
	        if(list.get(list.size()-1) < num){
	            list.add(num);
	            dp[i] = list.size();
	        }
	        else if(list.get(list.size()-1) > num){
	            binary(num,i);
	        }
	        else{
                dp[i] = list.size();
	        }
	    }
	    
	    dp2 = new int[N];
	    numList2 = new int[N];
	    for(int i=0; i<N; i++){
	        numList2[i] = numList[N-1-i];
	    }
	    list2 = new ArrayList<>();
	    list2.add(numList2[0]);
	    dp2[0] = 1;
	    
	    for(int i=1; i<N; i++){
	        int num = numList2[i];
	        
	        if(list2.get(list2.size()-1) > num){
	            list2.add(num);
	            dp2[i] = list2.size();
	        }
	        else if(list2.get(list2.size()-1) < num){
	            binary2(num, i);
	        }
	        else{
	            dp2[i] = list2.size();
	        }
	    }
	    
	    StringBuilder sb = new StringBuilder();
        for(int number : order){
            number--;
            sb.append(dp[number] + dp2[N-1-number] -1).append("\n");
        }
        System.out.println(sb);
	    
	}
	
	static void binary(int target, int index){
	    int start = 0;
	    int end = list.size()-1;
	    int answer_i = 0;
	    
	    while(start<=end){
	        int mid = (start+end) / 2;
	        if(list.get(mid) > target){
	            answer_i = mid;
	            end = mid-1;
	        }
	        else if(list.get(mid) < target){
	            start = mid+1;
	        }
	        else{
	            answer_i = mid;
	            break;
	        }
	    }
	    list.set(answer_i, target);
	    dp[index] = answer_i + 1;
	}
	
	static void binary2(int target, int index){
	    int start = 0;
	    int end = list2.size()-1;
	    int answer_i = 0;
	    
	    while(start<=end){
	        int mid = (start+end) / 2;
	        if(list2.get(mid) > target){
	            start = mid+1;
	        }
	        else if(list2.get(mid) < target){
	            answer_i = mid;
	            end = mid-1;
	        }
	        else{
	            answer_i = mid;
	            break;
	        }
	    }
	    list2.set(answer_i, target);
	    dp2[index] = answer_i + 1;
	}

}