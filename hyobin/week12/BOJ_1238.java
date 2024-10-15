import java.io.*;
import java.util.*;

public class Main {
	static int n, m, x;
	static int[][] mindistance;

	static class Node {
		int end, cost;

		public Node(int end, int cost) {
			this.end = end;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());

		ArrayList<ArrayList<Node>> adlist = new ArrayList<>();
		for (int i = 0; i < n + 1; i++) {
			adlist.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adlist.get(u).add(new Node(v, w));
		}
		mindistance = new int[n + 1][n + 1];
		for (int a = 1; a <= n; a++) {

			boolean[] visited = new boolean[n + 1];

			for (int i = 1; i < n + 1; i++) {
				mindistance[a][i] = Integer.MAX_VALUE;
			}
			mindistance[a][a] = 0;
			// 다익스트라
			for (int i = 0; i < n; i++) {
				int nowDisttance = Integer.MAX_VALUE;
				int idx = 0;
				for (int j = 1; j < n + 1; j++) {
					if (!visited[j] && mindistance[a][j] < nowDisttance) {
						nowDisttance = mindistance[a][j];
						idx = j;
					}
				}
				visited[idx] = true;

				for (int j = 0; j < adlist.get(idx).size(); j++) {
					Node adNode = adlist.get(idx).get(j);

					if (mindistance[a][adNode.end] > mindistance[a][idx] + adNode.cost) {
						mindistance[a][adNode.end] = mindistance[a][idx] + adNode.cost;
					}
				}
			}

		}
		int max = Integer.MIN_VALUE;
		// 최종 출력
		for (int i = 1; i <= n; i++) {
			if(mindistance[x][i] + mindistance[i][x]>max) {
				max = mindistance[x][i] + mindistance[i][x];
			}
		}
		System.out.println(max);
	}
}
