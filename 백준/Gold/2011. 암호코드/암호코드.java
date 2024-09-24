import java.io.*;
import java.util.*;

public class Main
{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();

		// 유효하지 않은 입력 처리
		if(s.charAt(0) == '0' || s.length() == 0) {
			System.out.println(0);
			return;
		}
		if(s.length() == 1){
		    System.out.println(1);
		    return;
		}

		int dp[] = new int[s.length() + 1];
		dp[0] = 1;
		dp[1] = 1;

		for (int i = 2; i <= s.length(); i++) {
			int oneDigit = s.charAt(i - 1) - '0';
			int twoDigits = (s.charAt(i - 2) - '0') * 10 + oneDigit;

			// 현재 자리가 '0'이 아니면, 이전 자릿수에서 값을 이어받음
			if (oneDigit >= 1) {
				dp[i] += dp[i - 1];
			}

			// 두 자리 숫자가 10~26 사이인 경우에만 값을 더함
			if (twoDigits >= 10 && twoDigits <= 26) {
				dp[i] += dp[i - 2];
			}

			// 숫자가 너무 크거나 잘못된 경우 처리
			dp[i] %= 1_000_000;
		}

		System.out.println(dp[s.length()]);
	}
}
