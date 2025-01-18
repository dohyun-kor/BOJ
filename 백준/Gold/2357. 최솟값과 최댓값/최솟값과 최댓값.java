import java.io.*;
import java.util.*;

public class Main {
    
    static int N,M;
    static int numbers[];
    static SegmentTree segTree;
    
    static class SegmentTree{
        int treeMax[];
        int treeMin[];
        
        SegmentTree(int n){
            int h = (int)Math.ceil(Math.log(n)/Math.log(2));
            treeMax = new int[1<<(h+1)];
            treeMin = new int[1<<(h+1)];
        }
        
        public void init(int arr[] ,int node, int start, int end){
            if(start == end) {
                treeMin[node] = arr[start];
                treeMax[node] = arr[start];
            }else{
                init(arr, node*2, start, (start+end)/2);
                init(arr, node*2+1, (start+end)/2+1, end);
                treeMin[node] = Math.min(treeMin[node*2], treeMin[node*2+1]);
                treeMax[node] = Math.max(treeMax[node*2], treeMax[node*2+1]);
            }
        }
        
        public int[] search(int node, int start, int end, int left, int right){
            int value[] = new int[2];
            if(end<left || right<start) {
                value[0] = 1_000_000_001;
                value[1] = -1;
                return value;
            }else if(left<=start && end<=right){
                value[0] = treeMin[node];
                value[1] = treeMax[node];
            }else{
                int v1[] = search(node*2, start, (start+end)/2, left, right);
                int v2[] = search(node*2+1, (start+end)/2+1, end, left, right);
                value[0] = Math.min(v1[0] , v2[0]);
                value[1] = Math.max(v1[1] , v2[1]);
            }
            return value;
        }
    }
    
    public static void main(String args[]) throws Exception{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        numbers = new int[N+1];
        
        M = Integer.parseInt(st.nextToken());
        for(int i=1; i<N+1; i++){
            numbers[i] = Integer.parseInt(br.readLine());
        }
        segTree = new SegmentTree(N+1);
        segTree.init(numbers,1,1,N);
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int answer[] = segTree.search(1,1,N,a,b);
            sb.append(answer[0]).append(" ").append(answer[1]).append("\n");
        }
        System.out.println(sb);
    }
}