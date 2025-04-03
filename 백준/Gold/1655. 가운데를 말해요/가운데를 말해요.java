import java.io.*;
import java.util.*;

public class Main{
    
    static int N;
    static PriorityQueue<numb> pq;
    static PriorityQueue<reversenumb> reversePq;
    static StringBuilder sb;
    
    static class numb implements Comparable<numb>{
        int n;
        
        public numb(int n){
            this.n = n;
        }
        
        @Override
        public int compareTo(numb o){
            return this.n-o.n;
        }
    }
    
    static class reversenumb implements Comparable<reversenumb>{
        int n;
        
        public reversenumb(int n){
            this.n = n;
        }
        
        @Override
        public int compareTo(reversenumb o){
            return o.n-this.n;
        }
    }
    
    public static void main(String args[]) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        
        pq = new PriorityQueue<>();
        reversePq = new PriorityQueue<>();
        
        for(int i=0; i<N; i++){
            int number = Integer.parseInt(br.readLine());
            pqadd(number);
        }
        System.out.println(sb);
        
    }
    
    static void pqadd(int number){
        if(pq.size() == reversePq.size()){
            pq.add(new numb(number));
            if(!reversePq.isEmpty()){
                if(reversePq.peek().n > pq.peek().n){
                    int a = reversePq.poll().n;
                    int b = pq.poll().n;
                    pq.add(new numb(a));
                    reversePq.add(new reversenumb(b));
                }
            }
            sb.append(pq.peek().n).append("\n");
        }else{
            reversePq.add(new reversenumb(number));
            if(!pq.isEmpty()){
                if(reversePq.peek().n > pq.peek().n){
                    int a = reversePq.poll().n;
                    int b = pq.poll().n;
                    pq.add(new numb(a));
                    reversePq.add(new reversenumb(b));
                }
            }
            sb.append(reversePq.peek().n).append("\n");
        }
    }
}