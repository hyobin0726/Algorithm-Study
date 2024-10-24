import java.util.*;
import java.io.*;

public class Main {
    static int N, L, R;
    static int[][] map, visited;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static int people, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        while (true) {
            visited = new int[N][N];
            int big = 0;
            boolean chk = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] == 0) {
                        // dfs
                        big++;
                        people = map[i][j];
                        cnt = 1;
                        bfs(i, j, big);
                        if (cnt != 1) chk = true;
                    }
                }
            }
            if (!chk) break;
            ans++;
        }

        System.out.println(ans);
    }

    public static void bfs(int x, int y, int big) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = big;
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = cur[0] + dx[k], ny = cur[1] + dy[k];
                if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                    int diff = Math.abs(map[cur[0]][cur[1]] - map[nx][ny]);
                    if (visited[nx][ny] == 0 && L <= diff && diff <= R) {
                        q.add(new int[]{nx, ny});
                        visited[nx][ny] = big;
                        people += map[nx][ny];
                        cnt++;
                    }
                }
            }
        }

        if (cnt != 1) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] == big) {
                        map[i][j] = people / cnt;
                    }
                }
            }
        }

    }
}
