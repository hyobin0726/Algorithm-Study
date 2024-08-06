/* BFS */
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int[][] board = new int[N][M];
		int gram_x = 0, gram_y = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 2) {
					gram_x = i;
					gram_y = j;
				}
			}
		}
		
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		int[][] route = new int[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(route[i], Integer.MAX_VALUE);
		}
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0, 0});
		route[0][0] = 0;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0], y = cur[1];
			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if (0 <= nx && nx < N && 0 <= ny && ny < M) {
					if (board[nx][ny] != 1) {
						if (route[x][y] + 1 < route[nx][ny]) {
							route[nx][ny] = route[x][y] + 1;
							q.add(new int[] {nx, ny});
						}
					}
				}
			}
		}
		
		int gram = route[gram_x][gram_y];
		if (gram != Integer.MAX_VALUE) {
			gram += (N - 1 - gram_x) + (M - 1 - gram_y);
		}
		int ans = Math.min(route[N-1][M-1], gram);
		if (ans <= T) {
			System.out.println(ans);
		} else {
			System.out.println("Fail");
		}
	}

}
