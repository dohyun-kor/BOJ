import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int M = Integer.parseInt(br.readLine());

		int cur = (1<<21);
        
        int num = 0;

        StringBuilder sb = new StringBuilder();

		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
// 			System.out.println(order);
            if(!order.equals("all") && !order.equals("empty")){
			    num = Integer.parseInt(st.nextToken());
            }

			if(order.equals("add")) {
				cur = cur | (1<<num);
			}
			else if(order.equals("check")) {
				if((cur & (1<<num)) == (1<<num)) {
					sb.append(1).append("\n");
				}
				else {
					sb.append(0).append("\n");
				}
			}
			else if(order.equals("remove")) {
				if((cur & (1<<num)) == (1<<num)) {
					cur -= (1<<num);
				}
			}
			else if(order.equals("toggle")) {
				if((cur & (1<<num)) == (1<<num)) {
					cur -= (1<<num);
				}
				else {
					cur = cur | (1<<num);
				}
			}
			else if(order.equals("empty")) {
				cur = (1<<21);
			}
			else { // all
				cur = ((1<<21)-1);
			}
		}
        
        System.out.println(sb);

	}
}