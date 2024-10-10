import java.io.*;
import java.util.*;

public class Main
{
	static int N,p[];
	static ArrayList<node> nodeList;
	static ArrayList<planet> planetList;
	static long answer;

	static class node {
		int x,y;
		long cost;
		node(int x, int y, long cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}

	static class planet {
		long x,y,z;
		int idx;
		planet(long x, long y, long z, int idx) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.idx = idx;
		}
	}

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		nodeList = new ArrayList<>();
		planetList = new ArrayList<>();


		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			planetList.add(new planet(a,b,c,i));
		}

		Collections.sort(planetList, (i1, i2) -> {
			return Long.compare(i1.x, i2.x);
		});
        make(1);
        
        Collections.sort(planetList, (i1, i2) -> {
            return Long.compare(i1.y, i2.y);
        });
        make(2);
        
        Collections.sort(planetList, (i1, i2) -> {
            return Long.compare(i1.z, i2.z); 
        });
        make(3);


		Collections.sort(nodeList, (i1, i2) -> {
			return Long.compare(i1.cost, i2.cost);
		});

		p = new int[N+1];
		for(int i=0; i<N+1; i++) {
			p[i] = i;
		}

		answer = 0;

		for(node n : nodeList) {
			if(find(n.x) == find(n.y)) continue;
			union(n.x, n.y);
			answer += n.cost;
		}

		System.out.println(answer);
	}

	static int find(int x) {
		if(p[x] == x) return x;
		return p[x] = find(p[x]);
	}
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x<y) p[y] = x;
		else p[x] = y;
	}


	static void make(int num) {
		for(int i=0; i<planetList.size(); i++) {
			planet one = planetList.get(i);
			if(i+1 < planetList.size()) {
				planet two = planetList.get(i+1);
				nodeList.add(new node(one.idx, two.idx, calculate(i,i+1,num)));
			}
			if(i-1 >=0) {
				planet three = planetList.get(i-1);
				nodeList.add(new node(one.idx, three.idx, calculate(i,i-1,num)));
			}
		}
	}
	
	static long calculate(int i, int j, int num){
	    planet p1 = planetList.get(i);
	    planet p2 = planetList.get(j);
	    
	    if(num==1){
	        return Math.abs(p1.x-p2.x);
	    }
	    else if(num==2){
	        return Math.abs(p1.y-p2.y);
	    }
	    else{
	        return Math.abs(p1.z-p2.z);
	    }
	}
	
}