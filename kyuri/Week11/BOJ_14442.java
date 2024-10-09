import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static char[][] map;
    static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};

    static class Info {
        int x;
        int y;
        int cnt;
        int destroyed;

        public Info(int x, int y, int cnt, int destroyed) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.destroyed = destroyed;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Info> q = new LinkedList<>();
        q.add(new Info(0, 0, 1, 0));

        int[][][] visit = new int[N][M][K+1];
        while (!q.isEmpty()) {
            Info cur = q.poll();

            if (cur.x == N - 1 && cur.y == M-1) {
                return cur.cnt;
            }

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d], ny = cur.y + dy[d];
                if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if (map[nx][ny] == '0') {
                        if (visit[nx][ny][cur.destroyed] == 0) {
                            visit[nx][ny][cur.destroyed] = 1;
                            q.add(new Info(nx, ny, cur.cnt + 1, cur.destroyed));
                        }
                    } else if (map[nx][ny] == '1') {
                        if (cur.destroyed < K && visit[nx][ny][cur.destroyed + 1] == 0) {
                            visit[nx][ny][cur.destroyed + 1] = 1;
                            q.add(new Info(nx, ny, cur.cnt + 1, cur.destroyed + 1));
                        }
                    }
                }
            }
        }

        return -1;
    }

}
