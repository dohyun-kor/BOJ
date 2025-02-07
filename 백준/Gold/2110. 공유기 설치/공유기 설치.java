import java.io.*;
import java.util.*;

public class Main{
    
    static int N,C;
    static int xList[];
    
    public static void main (String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        xList = new int[N];
        
        for(int i=0; i<N; i++){
            int x = Integer.parseInt(br.readLine());
            xList[i] = x;
        }
        
        Arrays.sort(xList);
        
        int start = 0;
        int end = xList[N-1];
        int answer = 0;
        while(start<=end){
            int mid = (start+end)/2;
            if(check(mid)){
                answer = mid;
                start = mid+1;
            }else{
                end = mid-1;
            }
        }
        
        System.out.println(answer);
    }
    
    static boolean check(int distance){
        int count = 1;
        int nowpoint = xList[0];
        for(int i=1; i<N; i++){
            if(xList[i] - nowpoint >= distance){
                nowpoint = xList[i];
                count++;
            }
        }
        
        if(count >= C){
            return true;
        }
        return false;
    }
}