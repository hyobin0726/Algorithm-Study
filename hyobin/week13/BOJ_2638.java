import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static List<int[]> point;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {
					map[i][j] = -1;
					visited[i][j] = true;
				}
			}
		}
		cnt = 0;
		boolean ischeck = true;
		while (ischeck) {
			cnt++;
			ischeck = false;
			point = new LinkedList<>();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == 0 && !visited[i][j]) {
						for (int d = 0; d < 4; d++) {
							int nx = i + dx[d];
							int ny = j + dy[d];
							if (0 <= nx && 0 <= ny && nx < n && ny < m && map[nx][ny] == -1) {

								bfs(i, j);
								break;

							}
						}
					}
				}
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == 1 && !visited[i][j]) {
						int tcnt = 0;
						for (int d = 0; d < 4; d++) {
							int nx = i + dx[d];
							int ny = j + dy[d];
							if (0 <= nx && 0 <= ny && nx < n && ny < m && map[nx][ny] == -1) {
								tcnt++;
								ischeck = true;
							}
						}
						if (tcnt >= 2) {
							point.add(new int[] { i, j });
						}
					}
				}
			}
			fuse(point);

		}
		System.out.println(cnt - 1);

	}

	static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { x, y });
		map[x][y] = -1;
		visited[x][y] = true;

		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int tx = temp[0];
			int ty = temp[1];

			for (int i = 0; i < 4; i++) {
				int nx = tx + dx[i];
				int ny = ty + dy[i];
				if (0 <= nx && 0 <= ny && nx < n && ny < m && map[nx][ny] == 0) {
					visited[nx][ny] = true;
					map[nx][ny] = -1;
					q.add(new int[] { nx, ny });
				}
			}

		}
	}

	static void fuse(List<int[]> point) {
		for (int i = 0; i < point.size(); i++) {
			int x = point.get(i)[0];
			int y = point.get(i)[1];
			map[x][y] = -1;
		}
	}

}
