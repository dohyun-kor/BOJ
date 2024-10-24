import java.io.*;
import java.util.*;

public class Main
{
	static int N;
	static long minvalue;
	static long numList[];
	static long answer[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		numList = new long[N];
		minvalue = Long.MAX_VALUE;
		answer = new long[3];
		for(int i=0; i<3; i++) {
			answer[i] = -1;
		}

		st = new StringTokenizer(br.readLine());

		for(int i=0; i<N; i++) {
			numList[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(numList);

		for(int i=0; i<N-1; i++) {
			long target = numList[i];
			binary(target, i+1);
			if(answer[0] + answer[1] + answer[2] == 0) {
				Arrays.sort(answer);
				System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
			}
		}
		Arrays.sort(answer);
		System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
	}



	static void binary(long target, int starti) {
		for(int i=starti; i<N; i++) {
			long nums = numList[i];
			int start = i;
			int end = N-1;
			long min_v = Long.MAX_VALUE;
			long min_num = target;
			while(start<=end) {
				int mid = (start+end) / 2;
				long value = numList[mid] + nums;
				// System.out.println(target + "  target and  " + value + "    " + min_v);
				if(mid == i) {
					start = mid+1;
					continue;
				}
				if(value+target > 0) {
					end = mid-1;
					if(Math.abs(value + target) < min_v) {
						min_v = Math.abs(numList[mid] + target + nums);
						min_num = numList[mid];
					}
				}
				else if(value+target < 0) {
					start = mid+1;
					if(Math.abs(value + target) < min_v) {
						min_v = Math.abs(numList[mid] + target + nums);
						min_num = numList[mid];
					}
				}
				else {
					min_num = numList[mid];
					answer[0] = target;
					answer[1] = nums;
					answer[2] = min_num;
					return;
				}
			}

			if(min_v < minvalue) {
				minvalue = min_v;
				answer[0] = target;
				answer[1] = nums;
				answer[2] = min_num;
			}
		}
	}


}