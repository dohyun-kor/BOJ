import java.io.*;
import java.util.*;

public class Main
{
    static int G,P,numList[],p[];
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        numList = new int[P];
        p = new int[G+1];
        for(int i=0; i<P; i++){
            numList[i] = Integer.parseInt(br.readLine());
        }
        
        for(int i=0; i<G+1; i++){
            p[i] = i;
        }
        int cnt = 0;
        for(int num : numList){
            // System.out.println(Arrays.toString(p));
            if(find(num) == 0) break;
            cnt++;
            p[find(num)]--;
            // find(p[num]);
        }
        System.out.println(cnt);
        
        
	}
	
	static int find(int x){
	    if(p[x]==x) return x;
	    return p[x] = find(p[x]);
	}
	
}