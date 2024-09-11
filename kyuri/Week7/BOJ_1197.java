import java.io.*;
import java.util.*;

/* PRIM 알고리즘을 이용한 최소 신장 트리 - BFS와 유사 */
class Edge implements Comparable<Edge> {
	int v, cost;
	
	public Edge(int v, int cost) {
		super();
		this.v = v;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.cost, o.cost);
	}
}

public class Main {
	static List<Edge>[] graph;
	static boolean[] visited;
	static int ans = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[a].add(new Edge(b, c));
			graph[b].add(new Edge(a, c));
		}
		
		visited = new boolean[N+1];
		prim();
		System.out.println(ans);
	}
	
	public static void prim() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(1, 0));
		
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			if (visited[edge.v]) continue;
			visited[edge.v] = true;
			ans += edge.cost;
			for (Edge e : graph[edge.v]) {
				if (!visited[e.v]) {
					pq.offer(e);
				}
			}
		}
	}

}
