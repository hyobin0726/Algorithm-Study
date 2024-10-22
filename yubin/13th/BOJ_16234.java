import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ_16234 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int N,M;
	static int[][] dist;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		dist = new int[N][N];
		
		
		//초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(i == j) {
					dist[i][j] = 0;
					continue;
				}
				dist[i][j] = 10000000;
			}
		}
		
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken())-1;
			int end = Integer.parseInt(st.nextToken())-1;
			int value = Integer.parseInt(st.nextToken());
			
			dist[start][end] = Math.min(dist[start][end], value);
		}
		
		for (int k = 0; k < N; k++) {	
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(dist[i][j] >=10000000) {
					sb.append("0").append(" ");
				}else {
					sb.append(dist[i][j]).append(" ");
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
