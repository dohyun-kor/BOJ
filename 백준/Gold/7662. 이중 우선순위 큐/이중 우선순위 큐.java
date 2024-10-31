import java.io.*;
import java.util.*;

public class Main {

	static int K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<T+1; tc++) {
			K = Integer.parseInt(br.readLine());
			TreeMap<Integer, Integer> map = new TreeMap<>();
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				String order = st.nextToken();
				int num = Integer.parseInt(st.nextToken());
				if(order.equals("I")) {
					if(map.containsKey(num)) {
						map.put(num, map.get(num) + 1);
					}
					else {
						map.put(num, 1);
					}
				}
				else {
					if(map.isEmpty()) continue;
					int key = 0;
					if(num == 1) {
						key = map.lastKey();
					}
					else {
						key = map.firstKey();
					}
					if(map.get(key) == 1) {
						map.remove(key);
					}
					else {
						map.put(key, map.get(key) - 1);
					}
				}
			}
			if(map.isEmpty()){
			    System.out.println("EMPTY");
			}
			else{
			    System.out.println(map.lastKey() + " " + map.firstKey());
			}
		}
	}
}