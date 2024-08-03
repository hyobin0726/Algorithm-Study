/* BFS (이미 최적 경로라면 q에 add하지 않기) */
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dx = {1, 0}, dy = {0, 1};
        int[][] result = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(result[i], Integer.MAX_VALUE);
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {0, 0});
        result[0][0] = 0;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int x = p[0], y = p[1];
            if (x == N-1 && y == M-1) break;
            for (int i = 1; i <= board[x][y]; i++) {
                for (int k = 0; k < 2; k++) {
                    int nx = x + (dx[k] * i);
                    int ny = y + (dy[k] * i);
                    if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                        if (result[nx][ny] > result[x][y] + 1) {
                            result[nx][ny] = result[x][y] + 1;
                            q.add(new int[] {nx, ny});
                        }
                    }
                }
            }
        }

        System.out.println(result[N-1][M-1]);
    }
}
