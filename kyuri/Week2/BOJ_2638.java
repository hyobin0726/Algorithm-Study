/* BFS, 공기 중심으로 생각 */
import java.io.*;
import java.util.*;

public class Main {
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];
        int cheese = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) { cheese++; }
            }
        }

        int time = 0;
        while (true) {
            if (cheese == 0) { break; }
            int[][] visited = new int[N][M];
            dfs(board, visited, N, M);
            time++;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (visited[i][j] >= 2) {
                        board[i][j] = 0;
                        cheese--;
                    }
                }
            }
        }
        System.out.println(time);
    }

    private static void dfs(int[][] board, int[][] visited, int N, int M) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {0, 0});
        while (!q.isEmpty()) {
            int[] x = q.poll();
            visited[x[0]][x[1]] = 1;
            for (int k = 0; k < 4; k++) {
                int nx = x[0] + dx[k];
                int ny = x[1] + dy[k];
                if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if (board[nx][ny] == 0 && visited[nx][ny] == 0) {
                        visited[nx][ny] = 1;
                        q.add(new int[] {nx, ny});
                    } else if (board[nx][ny] == 1) {
                        visited[nx][ny] += 1;
                    }
                }
            }
        }
    }
}
