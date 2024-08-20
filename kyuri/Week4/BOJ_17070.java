/* DFS (DP로 다시 풀기) */
import java.io.*;
import java.util.*;

public class Main {
	static int[][][] dir = {{}, {{}, {0,1}, {1,1}, {}}, {{}, {0,1}, {1,1}, {1,0}}, {{}, {}, {1,1}, {1,0}}};
	static int N;
	static int[][] visited;
	static int[][] board;
	static int cnt = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new int[N][N];
		dfs(1, 0, 1);
		System.out.println(cnt);
	}

	public static void dfs(int d, int x, int y) {
		visited[x][y] = d;
		if (x == N-1 && y == N-1) {
			cnt++;
		}
		for (int idx = 0; idx < 4; idx++) {
			if (dir[d][idx].length == 0) {
				continue;
			}

			int nx = x + dir[d][idx][0], ny = y + dir[d][idx][1];
			if (0 <= nx && nx < N && 0 <= ny && ny < N && board[nx][ny] != 1) {
				if (idx == 2) {
					if (board[nx-1][ny] == 1 || board[nx][ny-1] == 1) {
						continue;
					}
				}
				if(visited[nx][ny] == 0) {
					dfs(idx, nx, ny);
					visited[nx][ny] = 0;
				}
			}
		}

	}
}
