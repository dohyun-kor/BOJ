import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb=new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;


		st=new StringTokenizer(br.readLine());
		
		int N=Integer.parseInt(st.nextToken());
		int K=Integer.parseInt(st.nextToken());
		
		// 1~100까지의 전기용품이 등장한 index를 저장할 queue.
		Queue<Integer> [] queue=new LinkedList[101];
		
		for(int i=1;i<=100;i++) 
			queue[i]=new LinkedList<>();
		
		// 현재 작동하고 있는 전기용품의 번호를 나타낼 해시셋
		HashSet<Integer> isMoving=new HashSet<>();
		
		
		st=new StringTokenizer(br.readLine());
		
		int arr[]=new int[K]; 
		for(int i=0;i<K;i++) { 
			// 3번째 순서에 전기용품 7이 들어왔다면 queue[7].add(3) 을 한다.
			arr[i]=Integer.parseInt(st.nextToken());
			queue[arr[i]].add(i);
		}
		
		
		int i=0;
		for(i=0;i<K;i++) { //처음으로 멀티탭 구멍을 다 채울 때 까지 i를 증가.
			isMoving.add(arr[i]);
			queue[arr[i]].poll();
			if(isMoving.size()==N) {
				i++;
				break;
			}
			
		}
		
		int count=0;
		
		//이전에 멀티탭 구멍을 처음으로 다 채운 숫자를 알았으니 그 다음부터 탐색한다.
		//그렇기에 for 문에서 i를 끌어와서 쓴다.
		for(;i<K;i++) {
			int num=arr[i];
			queue[num].poll();
			int index= -1;
			int max=0;
			if(isMoving.contains(num)) // 현재 전기용품이 이미 멀티탭에 꽂혀있다면 continue
				continue;
			for(int val:isMoving) {
				if(queue[val].isEmpty()) { 
                //만약 앞으로 다시는 다시 쓰지 않는 전기용품이 있다면 index는 그 용품이 된다.
					index=val;
					break;
				}
				if(queue[val].peek()>max) { // 앞으로 다시 등장하는 데에 가장 오랜 시간이 걸리는 것을 찾음.
					max=queue[val].peek();
					index=val;
				}
			}
			
			isMoving.remove(index); // 현재 멀티탭에서 제거함
			isMoving.add(num); // 새로 추가하는 것을 멀티탭에 추가함
			count++; //카운트 증가
			
		}
		
		
		System.out.println(count);
		
		
		

	}
}