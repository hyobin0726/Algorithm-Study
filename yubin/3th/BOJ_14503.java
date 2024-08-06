import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class BOJ_14503 {
	static int N, M;
	static int r, c;
	static int d;
	static int[][] arr;
	static int result = 0;
	static int[] dx = {-1,0,1,0};
	//북서남동
	static int[] dy = {0,-1,0,1};
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		switch (d) {
		case 1:
			d = 3;
			break;
		case 3:
			d = 1;
			break;
		default:
			break;
		}
		
		loopa:
		while(arr[r][c] != 1) {
			if(arr[r][c] == 0) {
				arr[r][c] = 2;
				result++;
			}
			
			d++;
			
			for (int i = 0; i < 4; i++) {
				if(d == 4) {
					d = 0;
				}
				int nextX = r + dx[d];
				int nextY = c + dy[d];
				
				if(arr[nextX][nextY] == 0) {
					r = nextX;
					c = nextY;
					continue loopa;
				}
				if(i != 3) {
					d++;
				}
			}

			
			if(arr[r - dx[d]][c - dy[d]] == 1) {
				System.out.println(result);
				return;
			}else {
				r = r - dx[d];
				c = c - dy[d];
			}
			
		}
		
	}

}
