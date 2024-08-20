import java.io.*;
import java.util.*;

public class Solution {
    static int N, board[][], ans, sX, sY;
    static boolean visited[];
    static int[] dx = {1, 1, -1, -1}, dy = {1, -1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            board = new int[N][N];
            ans = -1;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N - 2; i++) {
                for (int j = 1; j < N - 1; j++) {
                    visited = new boolean[101];
                    visited[board[i][j]] = true;
                    sX = i;
                    sY = j;
                    go(i, j, -1, -1, 0, 0);
                }
            }

            System.out.println("#" + tc + " " + ans);
        }
    }

    private static void go(int x, int y, int prevX, int prevY, int cnt, int sd) {
        for (int d = sd; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if(nx == prevX && ny == prevY) continue;
            if(nx == sX && ny == sY) {
                ans = Math.max(ans, cnt + 1);
                return;
            }

            if(visited[board[nx][ny]]) continue;
            visited[board[nx][ny]] = true;
            go(nx, ny, x, y, cnt + 1, d);
            visited[board[nx][ny]] = false;
        }

    }
}
