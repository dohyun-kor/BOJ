import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	
	static List<int[]> house = new ArrayList<>();
	static List<int[]> gage = new ArrayList<>();
	static boolean[] selected;
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int migi = Integer.parseInt(st.nextToken());
				if (migi == 1) {
					house.add(new int[] {i, j});
				}
				else if (migi == 2) {
					gage.add(new int[] {i, j});
				}
			}
		}
		
		selected = new boolean[gage.size()];
		
		combination(0, 0);

		System.out.println(result);
	}
	
	static void combination(int start, int dep) {
		if (dep == M) {
			distance();
			return;
		}
		
		for (int i = start; i < gage.size(); i++) {
			selected[i] = true;
			combination(i + 1, dep + 1);
			selected[i] = false;
		}
	}
	
	static void distance() {
		int total = 0;
		
		for (int[] h : house) {
			int dis = Integer.MAX_VALUE;
			for (int i = 0; i < gage.size(); i++) {
				if (selected[i]) {
					int[] store = gage.get(i);
					int distance = Math.abs(h[0] - store[0]) + Math.abs(h[1] - store[1]);
					dis = Math.min(dis, distance);
				}
			}
			total += dis;
		}
		
		result = Math.min(result, total);
	}
}