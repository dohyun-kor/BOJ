import java.io.*;
import java.util.*;

public class Main
{
    static int A,B,C;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

        System.out.println(divide(A%C,B)%C);
	}
	
	static long divide(int A, int B){
	    if(B == 0 || B == 1){
	        return A%C;
	    }
	    
	    long a = divide(A,B/2)%C;
	    
	    if(B%2 == 1){
	        return ((a*a)%C*A)%C;
	    }
	    else{
	        return (a*a)%C;
	    }
	}
	
}