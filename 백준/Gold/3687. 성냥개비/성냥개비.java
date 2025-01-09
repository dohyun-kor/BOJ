import java.io.*;
import java.util.*;

public class Main{
    
    static int n;
    static String maxValue, minValue;
    static int count[] = new int[]{6,2,5,5,4,5,6,3,7,6};
    
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        
        for(int tc=1; tc<T+1; tc++){
            n = Integer.parseInt(br.readLine());
            
            maxValue = "";
            minValue = "";
            
            findMax();
            findMin();
            
            System.out.println(minValue + " " + maxValue);
        }
    }
    
    static void findMax(){
        int ncopy = n;
        if(ncopy % 2 == 1){
            maxValue += "7";
            ncopy -= 2;
        }
        while(ncopy > 1){
            maxValue += "1";
            ncopy -= 2;
        }
    }
    
    static void findMin(){
        int ncopy = n;
        int mincount = 0;
        if(ncopy % 7 ==0){
            mincount = ncopy/7;
            for(int i=0; i<mincount; i++){
                minValue += "8";
            }
            return;
        }else{
            mincount = (ncopy/7)+1;
        }
        
        int digit = 0;
        int num[] = new int[mincount];
        int rest = ncopy;
        dfs(mincount,digit,num,rest);
    }
    
    static void dfs(int mincount, int digit, int num[], int rest){
        if(digit==mincount){
            if(rest>0) return;
            for(int i=0; i<mincount; i++){
                minValue += num[i];
            }
            return;
        }
        
        for(int i=0; i<10; i++){
            if(digit==0 && i==0) continue;
            num[digit] = i;
            if(rest-count[i] >= 0 && rest-count[i] <= 7*(mincount-digit-1)){
                dfs(mincount, digit+1, num, rest-count[i]);
                if(minValue.length() == mincount) return;
            }
        }
        
        
    }
}