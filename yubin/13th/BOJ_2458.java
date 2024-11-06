import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



public class BOJ_2458 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int N,M;
	static int[][] dist;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//N,M
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dist = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(i==j) {
					continue;
				}else {
					dist[i][j] = 1000000000;
				}
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			
			dist[start][end] = 1;
		}
		
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
				
		int sum = 0;
		for (int i = 0; i < N; i++) {
			boolean check = false;
			for (int j = 0; j < N; j++) {
				if(dist[i][j] >= 1000000000) {
					if(dist[j][i] < 1000000000) {
					}else {
						break;
					}
				}
				
				if(j == N-1) check = true;
			}
			if(check) sum++;
		}
		
		
		System.out.println(sum);
	}
}
