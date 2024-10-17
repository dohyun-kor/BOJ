import java.io.*;
import java.util.*;

public class Main
{
    static int D;
	static long dp[][];
	static long DIV = 1_000_000_007;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		D = Integer.parseInt(br.readLine());
		
        dp = new long[][]{{0,1,0,0,0,0,0,1},
                            {1,0,1,0,0,0,0,1},
                            {0,1,0,1,0,0,1,1},
                            {0,0,1,0,1,0,1,0},
                            {0,0,0,1,0,1,0,0},
                            {0,0,0,0,1,0,1,0},
                            {0,0,1,1,0,1,0,1},
                            {1,1,1,0,0,0,1,0}};
                            
        long result[][] = divide(dp,D-1);
        long answer = ((result[0][1]%DIV) + (result[0][7]%DIV))%DIV;
        System.out.println(answer);
	}
	
	static long[][] divide(long A[][], int depth){
	    if(depth == 0 || depth == 1){
	        return dp;
	    }
	    
	    long b[][] = divide(A, depth/2);
	    
	    if(depth % 2 == 0){
	        return multiple(b,b);
	    }
	    else{
	        return multiple(multiple(b,dp),b);
	    }
	}
	
	static long[][] multiple(long a[][], long b[][]){
	    long c[][] = new long[8][8];
	    for(int i=0; i<8; i++){
	        for(int j=0; j<8; j++){
	            long result = 0;
	            for(int k=0; k<8; k++){
	                result += (a[i][k]*b[k][j])%DIV;
	                result %= DIV;
	            }
	            c[i][j] = result%DIV;
	        }
	    }
	    return c;
	}
}