import java.io.*;
import java.util.*;

public class Main{
    
    static char coins[][];
    static int N;
    static int answer;
    
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        coins = new char[N][N];
        answer = N*N;
        
        for(int i=0; i<N; i++){
            String s = br.readLine();
            for(int j=0; j<N; j++){
                coins[i][j] = s.charAt(j);
            }
        }
        
        for(int i=0; i<(1<<N); i++){
            for(int k=0; k<N; k++){
                int h = (1<<k);
                if((h & i) != 0){
                    flip(k);
                }
            }
            cal();
            for(int kk=0; kk<N; kk++){
                int hh = (1<<kk);
                if((hh & i) != 0){
                    flip(kk);
                }
            }
        }
        
        System.out.println(answer);
        
    }
    
    static void flip(int k){
        for(int j=0; j<N; j++){
            if(coins[j][k] == 'H'){
                coins[j][k] = 'T';
            }else{
                coins[j][k] = 'H';
            }
        }
    }
    
    static void cal(){
        int total = 0;
        
        for(int ci=0; ci<N; ci++){
            int count = 0;
            int rcount = 0;
            for(int cj=0; cj<N; cj++){
                if(coins[ci][cj] == 'T') count++;
                else rcount++;
            }
            if(count > rcount) total += rcount;
            else total += count;
        }
        
        answer = Math.min(answer, total);
    }
}