import java.io.*;
import java.util.*;

/* BFS 이용 */
public class Main {
	static int R, C;
	static char[][] board;
	static int[][] visit;
	static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
	static int Dx, Dy, Sx, Sy; // 굴, 고슴도치 위치
	static Queue<int[]> q;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		board = new char[R][C];
		visit = new int[R][C];
		q = new LinkedList<>();
    // q에 시작 위치 넣기
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				board[i][j] = str.charAt(j);
				if (board[i][j] == 'D') {
					Dx = i; Dy = j;
				} else if (board[i][j] == 'S') {
					q.add(new int[] {i, j});
					Sx = i; Sy = j;
				}
			}
		}

    // q에 모든 물 위치 넣기
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (board[i][j] == '*') q.add(new int[] {i, j});
			}
		}
		
		bfs();
		System.out.println(visit[Dx][Dy] != 0 ? visit[Dx][Dy] : "KAKTUS");
	}
	
	public static void bfs() {
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + dx[d], ny = cur[1] + dy[d];
				if (0 <= nx && nx < R && 0 <= ny && ny < C) {
					if ((board[nx][ny] == '.' || board[nx][ny] == 'D') && board[cur[0]][cur[1]] == 'S') {
						board[nx][ny] = 'S';
						visit[nx][ny] = visit[cur[0]][cur[1]] + 1;
						q.add(new int[] {nx, ny});
					} else if ((board[nx][ny] == '.' || board[nx][ny] == 'S') && board[cur[0]][cur[1]] == '*') {
						board[nx][ny] = '*';
						q.add(new int[] {nx, ny});
					}
				}
			}
		}
	}

}
