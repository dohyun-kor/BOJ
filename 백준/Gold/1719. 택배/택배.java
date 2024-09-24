import java.io.*;
import java.util.*;

public class Main
{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int floyd[][] = new int[n+1][n+1];
		int startpoint[][] = new int[n+1][n+1];
		int INF = n*1001;
		for(int i=1; i<n+1; i++){
		    for(int j=1; j<n+1; j++){
		        if(i==j) continue;
		        floyd[i][j] = INF;
		    }
		}
		
		for(int x=0; x<m; x++){
		    st = new StringTokenizer(br.readLine());
		    int start = Integer.parseInt(st.nextToken());
		    int end = Integer.parseInt(st.nextToken());
		    int cost = Integer.parseInt(st.nextToken());
		    floyd[start][end] = cost;
		    floyd[end][start] = cost;
		    startpoint[start][end] = end;
		    startpoint[end][start] = start;
		}
		
		for(int k=1; k<n+1; k++){
		    for(int i=1; i<n+1; i++){
		        for(int j=1; j<n+1; j++){
		            if(floyd[i][j] > floyd[i][k] + floyd[k][j]){
		                floyd[i][j] = floyd[i][k] + floyd[k][j];
		                startpoint[i][j] = startpoint[i][k];
		            }
		        }
		    }
		}
		
        for(int i=1; i<n+1; i++){
            for(int j=1; j<n+1; j++){
                if(startpoint[i][j] == 0){
                    sb.append("- ");
                }
                else{
                    sb.append(startpoint[i][j] + " ");
                }
            }
            sb.append("\n");
        }
		System.out.println(sb);
	}
}