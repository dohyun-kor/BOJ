import java.io.*;
import java.util.*;

public class Main{
    
    static int N, M;
    static int numbers[];
    static SegmentTree segTree;
    
    static class SegmentTree{
        ArrayList<Integer> numb[];
        
        SegmentTree(int n){
            int h = (int)Math.ceil(Math.log(n)/Math.log(2));
            numb = new ArrayList[1<<(1+h)];
            for(int i=0; i<1<<(1+h); i++){
                numb[i] = new ArrayList<>();
            }
        }
        
        public void init(int arr[], int node, int start, int end) {
            if (start == end) {
                numb[node].add(arr[start]);
                return;
            } else {
                init(arr, node * 2, start, (start + end) / 2);
                init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
                merge(numb[node*2], numb[node*2+1], node);
            }
        }
        
        void merge(ArrayList<Integer> left , ArrayList<Integer> right , int rnum){
            int i = 0;
            int j = 0;
            while(i < left.size() && j < right.size()){
                if(left.get(i) <= right.get(j)){
                    numb[rnum].add(left.get(i++));
                }else{
                    numb[rnum].add(right.get(j++));
                }
            }
            while(i < left.size()) numb[rnum].add(left.get(i++));
            while(j < right.size()) numb[rnum].add(right.get(j++));
        }

        
        long search(int node, int start, int end, int left, int right, int num){
            if(end<left || right<start) return 0;
            else if(left<=start && end<=right) {
                return binary(start, end, num, node);
            }
            else{
                return search(node*2, start, (start+end)/2, left, right, num) +
                        search(node*2 + 1, (start+end)/2 + 1 , end, left, right ,num);
            }
        }
        
        int binary(int start, int end, int num, int node){
            int s = 0;
            int e = numb[node].size();
            while(s<e){
                int mid = (s+e)/2;
                if(numb[node].get(mid) <= num){
                    s = mid+1;
                }else{
                    e = mid;
                }
            }
            return numb[node].size()-s;
        }
        
        
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        numbers = new int[N+1];
        
        st = new StringTokenizer(br.readLine());
        
        for(int i=1; i<N+1; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        
        segTree = new SegmentTree(N);
        segTree.init(numbers,1,1,N);
        
        M = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        
        for(int c=0; c<M; c++){
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            
            sb.append(segTree.search(1,1,N,i,j,k)).append("\n");
        }
        System.out.println(sb);
    }
}