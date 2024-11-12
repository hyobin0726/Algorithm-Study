import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            String num = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = num.charAt(j)-'0';
            }
        }

        int[][] mincost = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mincost[i][j] = Integer.MAX_VALUE;
            }
        }
        mincost[0][0] = 0;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;
        q.add(new int[]{0, 0});
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int tx = temp[0];
            int ty = temp[1];

            for (int i = 0; i < 4; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (!visited[nx][ny] || mincost[tx][ty] + map[nx][ny] < mincost[nx][ny]) {
                        mincost[nx][ny] = mincost[tx][ty] + map[nx][ny];
                        visited[nx][ny] = true;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }

        System.out.println(mincost[n-1][m-1]);

    }
}
