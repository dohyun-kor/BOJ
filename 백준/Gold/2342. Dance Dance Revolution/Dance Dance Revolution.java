import java.io.*;
import java.util.*;

public class Main
{
	static int info[], dp[][][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		info = new int[100001];

		int idx = 0;

		while(true) {
			int num = Integer.parseInt(st.nextToken());
			if(num == 0) break;
			info[idx++] = num;
		}
		if(idx == 0){
		    System.out.println(0);
		    return;
		}
		idx++;
		dp = new int[idx][5][5];
		for(int i=0; i<5; i++){
		    for(int j=0; j<5; j++){
		        if(i==0 && j==0) continue;
		        dp[0][i][j] = 1_000_000_000;
		    }
		}
		
		for(int i=1; i<idx; i++) {
			for(int j=0; j<5; j++) {
				for(int k=0; k<5; k++) {
					dp[i][j][k] = 1_000_000_000;
				}
			}
		}
		int index = 0;

		for(int depth=1; depth<idx; depth++) {
			int num = info[index++];
			for(int i=0; i<5; i++) {
				for(int j=0; j<5; j++) {
					if(i==num || j==num) {
						dp[depth][i][j] = Math.min(dp[depth][i][j], dp[depth-1][i][j]+1);
					} else {
						dp[depth][i][num] = Math.min(dp[depth][i][num], dp[depth-1][i][j] + cal(j, num));
						dp[depth][num][j] = Math.min(dp[depth][num][j], dp[depth-1][i][j] + cal(i, num));
					}
				}
			}
		}
		int answer = Integer.MAX_VALUE;
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				answer = Math.min(answer, dp[idx-1][i][j]);
			}
		}
		System.out.println(answer);
	}

	static int cal(int a, int b) {
		if(a == 0) {
			return 2;
		}
		if(Math.abs(a-b) == 2) {
			return 4;
		}
		else {
			return 3;
		}
	}

}