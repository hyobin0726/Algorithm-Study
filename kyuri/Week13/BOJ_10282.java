import java.io.*;
import java.util.*;

class Node {
	int idx, dist;
	public Node(int idx, int dist) {
		this.idx = idx;
		this.dist = dist;
	}
}

class Elem implements Comparable<Elem>{
	int dist, idx;
	public Elem(int dist, int idx) {
		this.dist = dist;
		this.idx = idx;
	}

	@Override
	public int compareTo(Elem o) {
		return this.dist - o.dist;
	}
	
}

public class Main {
	static int N, D, C;
	static ArrayList<Node>[] graph;
	static int[] dist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
	
			graph = new ArrayList[N+1];
			for (int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				graph[b].add(new Node(a, c));
			}
			
			dijkstra(C);
			int cnt = 0, time = 0;
			for (int i = 1; i <= N; i++) {
				if (dist[i] != (int) 1e9) {
					cnt++;
					time = Math.max(time, dist[i]);
				}
			}
			System.out.println(cnt + " " + time);
		}

	}
	
	public static void dijkstra(int start) {
		dist = new int[N+1];
		for (int i = 1; i <= N; i++) {
			dist[i] = (int) 1e9;
		}
		dist[start] = 0;
		
		PriorityQueue<Elem> pq = new PriorityQueue<>();
		pq.add(new Elem(0, start));
		while (!pq.isEmpty()) {
			int minD = pq.peek().dist;
			int minIdx = pq.peek().idx;
			pq.poll();
			
			if (minD != dist[minIdx]) continue;
			
			int len = graph[minIdx].size();
			for (int i = 0; i < len; i++) {
				int tarIdx = graph[minIdx].get(i).idx;
				int tarD = graph[minIdx].get(i).dist;
				int newD = dist[minIdx] + tarD;
				
				if (dist[tarIdx] > newD) {
					dist[tarIdx] = newD;
					pq.add(new Elem(newD, tarIdx));
				}
			}
		}
	}

}
