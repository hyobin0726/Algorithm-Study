import java.io.*;
import java.util.*;

/* 유니온 파인드 이용 */
public class Main {
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		parent = new int[N];
		for (int i = 0; i < N; i++) parent[i] = i;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				if (Integer.parseInt(st.nextToken()) == 1) {
					union(i, j);
				}
			}
		}
		
		boolean chk = true;
		st = new StringTokenizer(br.readLine());
		int start = find(Integer.parseInt(st.nextToken()) - 1);
		for (int i = 1; i < M; i++) {
			int city = Integer.parseInt(st.nextToken()) - 1;
			if (start != find(city)) chk = false;
		}
		
		System.out.println(chk ? "YES" : "NO");
	}
	
	public static void union(int x, int y) {
		x = find(x); y = find(y);
		if (x == y) return;
		else if (x < y) parent[y] = x;
		else parent[x] = y;
	}
	
	public static int find(int x) {
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	
}
