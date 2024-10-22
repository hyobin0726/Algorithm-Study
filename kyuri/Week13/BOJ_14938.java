import java.io.*;
import java.util.*;

class Node {
	int idx, dist;

	public Node(int idx, int dist) {
		super();
		this.idx = idx;
		this.dist = dist;
	}
}

class Elem implements Comparable<Elem> {
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
	static int N, M, R, ans;
	static int[] items;
	static ArrayList<Node>[] graph;
	static int[] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		items = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}
		
		graph = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			graph[a].add(new Node(b, l));
			graph[b].add(new Node(a, l));
		}
		
		int sum;
		for (int i = 1; i <= N; i++) {
			sum = 0;
			dijkstra(i);
			for (int j = 1; j <= N; j++) {
				if (dist[j] <= M) sum += items[j];
			}
			ans = Math.max(sum, ans);
		}
		System.out.println(ans);
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
