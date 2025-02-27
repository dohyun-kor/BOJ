import java.io.*;
import java.util.*;

public class Main{
    
    static int N;
    static long menu[];
    static long result;
    static final int MOD = 1_000_000_007;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        menu = new long[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            menu[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(menu);
        result = 0;
        for(int i=0; i<N; i++){
            calculate(i);
        }
        System.out.println(result);
    }
    
    static void calculate(int index){
        if(index>0){
            long maxValue = menu[index];
            long plus = powerNumber(2, index) % MOD;
            long add = ((plus-1) * maxValue);
            result += add;
        }
        if(index<N-1){
            long minValue = menu[index];
            long minus = powerNumber(2, N-index-1) % MOD;
            long pop = ((minus-1) * minValue);
            result -= pop;
        }
        result %= MOD;
    }
    
    static long powerNumber(long num, int power){
        if(power == 1){
            return num;
        }
        
        long a;
        long b;
        
        if(power % 2 == 0){
            a = powerNumber(num, power/2) % MOD;
            b = a * a;
        }else{
            a = powerNumber(num, power/2) % MOD;
            b = ((a * a) % MOD) * 2;
        }
        return (b % MOD);
    }
}