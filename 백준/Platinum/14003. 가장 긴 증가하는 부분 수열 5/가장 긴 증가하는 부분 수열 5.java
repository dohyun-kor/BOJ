import java.io.*;
import java.util.*;

public class Main
{
    static int N;
    static int numList[];
    static ArrayList<Integer> list;
    static ArrayList<Integer> find;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		list = new ArrayList<>();
		find = new ArrayList<>();
		numList = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++){
		    numList[i] = Integer.parseInt(st.nextToken());
		}
		
		list.add(numList[0]);
		find.add(0);
		
		for(int i=1; i<N; i++){
		    int num = numList[i];
		    if(list.get(list.size()-1) < num){
		        list.add(num);
                find.add(list.size()-1);
		    }
		    else if(list.get(list.size()-1) > num){
		        binary(num);
		    }
		    else{
		        find.add(list.size()-1);
		    }
		}
		int idx = list.size()-1;
		System.out.println(list.size());
        int target = list.size()-1;
        Stack<Integer> stack = new Stack<>();
        for(int i=find.size()-1; i>=0; i--){
            if(find.get(i) == target){
                stack.add(numList[i]);
                target--;
            }
        }

		StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop()).append(" ");
        }
		System.out.println(sb);
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
	            find.add(mid);
	            return;
	        }
	    }
	    
	    list.set(answer_i, target);
        find.add(answer_i);
	}
}