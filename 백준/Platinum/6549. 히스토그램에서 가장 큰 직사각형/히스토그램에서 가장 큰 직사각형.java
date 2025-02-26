import java.io.*;
import java.util.*;

public class Main{
    
    static int N;
    static long answer;
    static long square[];
    static SegmentTree segtree;
    
    static class SegmentTree{
        int tree[];
        
        SegmentTree(int n){
            int height = (int)Math.ceil(Math.log(n)/Math.log(2));
            tree = new int[1<<(height+1)];
        }
        
        int init(long arr[], int start, int end, int index){
            if(start==end) return tree[index] = start;
            else{
                int left = init(arr, start, (start+end)/2, index*2);
                int right = init(arr, (start+end)/2 + 1, end, index*2 + 1);
                if(square[left] <= square[right]){
                    return tree[index] = left;
                }else{
                    return tree[index] = right;
                }
            }
        }
        
        int find(int start, int end, int left, int right, int index){
            if(right < start || end < left) return 0;
            else if(left<=start && end<=right) return tree[index];
            else{
                int leftValue = find(start, (start+end)/2, left, right ,index*2);
                int rightValue = find((start+end)/2 + 1 , end, left, right, index*2 + 1);
                if(square[leftValue] <= square[rightValue]){
                    return leftValue;
                }else{
                    return rightValue;
                }
            }
        }
    }
    
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        while(true){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if(N==0) return;
            square = new long[N+1];
            square[0] = Long.MAX_VALUE;
            for(int i=1; i<N+1; i++){
                square[i] = Long.parseLong(st.nextToken());
            }
            segtree = new SegmentTree(N);
            segtree.init(square, 1, N, 1);
            answer = 0;
            getArea(1,N);
            System.out.println(answer);
        }
    }
    static void getArea(int s, int e){
        int minIndex = segtree.find(1,N,s,e,1);
        long area = (e-s+1) * square[minIndex];
        answer = Math.max(answer, area);
        if(minIndex-1>=s){
            getArea(s, minIndex-1);
        }
        if(minIndex+1<=e){
            getArea(minIndex+1 , e);
        }
    }
}