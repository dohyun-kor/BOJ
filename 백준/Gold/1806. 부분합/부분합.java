import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		int numbers[] = new int[N];
		st = new StringTokenizer(br.readLine());
		long check = 0;
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
			if(numbers[i] >= S) {
				System.out.println(1);
				return;
			}
			check += numbers[i];
		}
		if(check < S){
		    System.out.println(0);
		    return;
		}
		
		int start = 0;
		int sum = numbers[0];
		int end = 0;
		int answer = N;
		while(true) {
		    if(sum < S){
                end++;
                if(end >= N){
                    break;
                }
                sum += numbers[end];
                if(sum >= S){
                    answer = Math.min(answer, (end-start+1));
                }
		    }else{
    		    sum -= numbers[start];
    		    start++;
                if(sum >= S){
                    answer = Math.min(answer, (end-start+1));
                }
		    }
		}
		System.out.println(answer);


	}
}