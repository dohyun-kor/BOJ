import java.io.*;
import java.util.*;

public class Main
{
    static int N,M,info[][],lst[][],p[];
    static ArrayList<node> nodeList;
    static double result;
    
    static class node{
        int x,y;
        double cost;
        node(int x, int y, double cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
    
	public static void main(String[] args)throws IOException{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
	    st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        info = new int[N+1][2];
        nodeList = new ArrayList<>();

        
        for(int i=1; i<N+1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<2; j++){
                info[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        lst = new int[M][2];
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            lst[i][0] = Integer.parseInt(st.nextToken());
            lst[i][1] = Integer.parseInt(st.nextToken());
        }
        
        for(int i=1; i<N+1; i++){
            for(int j=i+1; j<N+1; j++){
                nodeList.add(new node(i,j,calculate(i,j)));
                nodeList.add(new node(j,i,calculate(j,i)));
            }
        }
        p = new int[N+1];
        for(int i=0; i<N+1; i++){
            p[i] = i;
        }
        Collections.sort(nodeList, (i1 , i2) ->{
            return Double.compare(i1.cost, i2.cost);
        });
        for(int i=0; i<M; i++){
            if(find(lst[i][0]) != find(lst[i][1]))
            unionf(lst[i][0], lst[i][1]);
        }
        result = 0.0;
        for(node n : nodeList){
            if(find(n.x) != find(n.y)){
                unionf(n.x, n.y);
                result += n.cost;
            }
        }
        
        result = Math.round(result*100)/100.0;
        System.out.println(String.format("%.2f", result));
	}
	
	static int find(int x){
	    if(p[x]==x) return p[x];
	    return p[x] = find(p[x]);
	}
	
	static void unionf(int x, int y){
	    x = find(x);
	    y = find(y);
	    if(x>y) p[x] = y;
	    else p[y] = x;
	}
	
	
	static double calculate(int i, int j){
	    double xcost = Math.pow(info[i][0]-info[j][0],2);
	    double ycost = Math.pow(info[i][1]-info[j][1],2);
	    return Math.sqrt(xcost+ycost);
	}
}