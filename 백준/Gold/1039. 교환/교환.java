import java.io.*;
import java.util.*;

public class Main
{
    static int N,K,answer;
    static HashMap<String, Integer> map;    


    static class mynum{
        String num;
        int count;
        
        mynum(String num, int count){
            this.num = num;
            this.count = count;
        }
    }
    
	public static void main(String[] args) throws IOException{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
	    st = new StringTokenizer(br.readLine());
	    N = Integer.parseInt(st.nextToken());
	    K = Integer.parseInt(st.nextToken());
	    map = new HashMap<String,Integer>();
	    
	    answer = -1;
	    comb();
	    System.out.println(answer);
	}
	
	static void comb(){
        Queue<mynum> q = new ArrayDeque<>();
        String first = ""+N;
        q.add(new mynum(first, 0));
        
        while(!q.isEmpty()){
            mynum curnum = q.poll();
            if(curnum.count == K){
                answer = Math.max(answer, Integer.parseInt(curnum.num));
                continue;
            }
            String key = curnum.num + "countis" + curnum.count;
            if(map.containsKey(key)) continue;
            map.put(key, 1);
            char nums[] = curnum.num.toCharArray();
            for(int i=0; i<nums.length-1; i++){
                for(int j=i+1; j<nums.length; j++){
                    char newnums[] = curnum.num.toCharArray();
                    char swapnum1 = nums[i];
                    char swapnum2 = nums[j];
                    newnums[i] = swapnum2;
                    newnums[j] = swapnum1;
                    if(newnums[0] == '0') continue;
                    String nnums = new String(newnums);
                    q.add(new mynum(nnums, curnum.count + 1));
                }
            }
        }
	    
	}
}