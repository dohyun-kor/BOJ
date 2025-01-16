import java.io.*;
import java.util.*;

public class Main {
    
    static int N,M,K;
    static long numbers[];
    static SegmentTree segTree;
    
    static class SegmentTree{
        long tree[];
        
        public SegmentTree(int n){
            int h = (int)Math.ceil(Math.log(n)/Math.log(2));
            tree = new long[1<<(h+1)];
        }
        
        long init(long arr[] , int node, int start , int end){
            if(start==end){
                return tree[node] = arr[start];
            }else{
                return tree[node] = init(arr, node*2, start, (start+end)/2) + init(arr, node*2+1, (start+end)/2 + 1 , end);
            }
        }
        
        long sum(int node, int start, int end, int left, int right){
            if(end < left || right < start) return 0;
            else if(left<=start && end<=right) return tree[node];
            else{
                return sum(node*2, start, (start+end)/2, left, right) + sum(node*2 + 1, (start+end)/2 + 1, end, left, right);
            }
        }
        
        void update(int node, int start, int end, int index , long diff){
            if(index < start || end < index) return;
            else{
                tree[node] += diff;
                if(start != end){
                    update(node*2, start, (start+end)/2, index, diff);
                    update(node*2 + 1 , (start+end)/2 + 1 , end, index, diff);
                }
            }
        }
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        numbers = new long[N+1];
        
        for(int i=1; i<N+1; i++){
            numbers[i] = Long.parseLong(br.readLine());
        }
        
        segTree = new SegmentTree(N);
        
        segTree.init(numbers,1,1,N);
        
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<M+K; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            if(a == 1){
                Long c = Long.parseLong(st.nextToken());
                segTree.update(1,1,N,b,c-numbers[b]);
                numbers[b] = c;
            }else{
                int c = Integer.parseInt(st.nextToken());
                sb.append(segTree.sum(1,1,N,b,c)).append("\n");
            }
        }
        
        System.out.println(sb);
        
    }
}