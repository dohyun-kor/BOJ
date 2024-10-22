import java.io.*;
import java.util.*;

public class Main
{
	static int N,K;
	static long answer;
	static ArrayList<info> infoList;
	static ArrayList<back> backList;

	static class info implements Comparable<info> {
		int weight, cost;

		info(int weight, int cost) {
			this.weight = weight;
			this.cost = cost;
		}

		@Override
		public int compareTo(info n) {
			return n.cost - this.cost;
		}
	}

	static class back {
		int max_weight;

		back(int max_weight) {
			this.max_weight = max_weight;
		}
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		infoList = new ArrayList<>();
		backList = new ArrayList<>();

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			infoList.add(new info(weight, cost));
		}

		for(int i=0; i<K; i++) {
			int max_weight = Integer.parseInt(br.readLine());
			backList.add(new back(max_weight));
		}
		
		Collections.sort(infoList, (i1, i2) -> {
		    return i1.weight - i2.weight;
		});

		Collections.sort(backList, (i1, i2) -> {
			return i1.max_weight - i2.max_weight;
		});

        
		answer = 0;

		greedy();

        System.out.println(answer);
	}

	static void greedy() {
		PriorityQueue<info> pq = new PriorityQueue<>();
		int j = 0;
        
        for(back curback : backList){
            while(j<N){
                if(infoList.get(j).weight <= curback.max_weight){
                    pq.add(infoList.get(j));
                    j++;
                }
                else{
                    break;
                }
            }
            if(!pq.isEmpty()){
                answer += pq.poll().cost;
            }
        }
	}
}