import java.io.*;
import java.util.*;

public class Main{
    
    static int N,M;
    static int parent[];
    
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        parent = new int[N+1];
        for(int i=0; i<N+1; i++){
            parent[i] = i;
        }
        
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int point = Integer.parseInt(st.nextToken());
                if(point==1){
                    union(i+1,j+1);
                }
            }
        }
        
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        for(int i=0; i<M-1; i++){
            int p = Integer.parseInt(st.nextToken());
            if(find(a) != find(p)){
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
    
 // x의 부모를 찾는 연산
    public static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
 
        return parent[x] = find(parent[x]);
    }
 
    // y의 부모를 x의 부모로 치환하는 연산 (x > y 일 경우, 반대)
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
 
        if (x != y) {
            if (x < y) {
                parent[y] = x;
            } else {
                parent[x] = y;
            }
        }
    }
}