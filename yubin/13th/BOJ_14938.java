import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14938 {
	/**
	 * 서강그라운드
	 */
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int n,m,r;
	static int[] t;
	static int[][] dist;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//n,m,r
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());  //지역의 개수
		m = Integer.parseInt(st.nextToken());  //예은의 수색 범위
		r = Integer.parseInt(st.nextToken());  //길의 개수
		
		t = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			t[i] = Integer.parseInt(st.nextToken());
		}
		
		dist = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(i != j) dist[i][j] = 1000000000;
			}
		}
		
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) -1;
			int end = Integer.parseInt(st.nextToken()) -1;
			int value = Integer.parseInt(st.nextToken());
			
			if(m >= value) {
				dist[start][end] = value;
				dist[end][start] = value;
			}
		}
		
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(dist[i][k] + dist[k][j] <= m) {
						dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
					}
				}
			}
		}
		
		int sum = 0;
		for (int i = 0; i < n; i++) {
			int tmpSum = 0;
			for (int j = 0; j < n; j++) {
				if(dist[i][j] < 1000000000) {
					tmpSum += t[j];
				}
			}
			sum = Math.max(sum, tmpSum);
		}
		
		System.out.println(sum);
	}
}
