import java.io.*;
import java.util.*;

/* 위상 정렬 - BFS로 구현 */
public class Main {
	static ArrayList<Integer>[] graph;
	static int[] in_degree;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		in_degree = new int[N+1];
		graph = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

    // 진입 차수 저장하기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			in_degree[b]++;
		}

    // 진입 차수가 0인 정점들 q에 삽입
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if (in_degree[i] == 0) q.add(i);
		}
		
		while (!q.isEmpty()) {
			int v = q.poll();
			sb.append(v).append(" ");
			for (int next : graph[v]) {
				in_degree[next]--;
				if (in_degree[next] == 0) q.add(next);
			}
		}
		
		System.out.println(sb.toString());
	}
	
}
