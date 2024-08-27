/* DFS */
import java.io.*;
import java.util.*;
 
class Solution {
    static int N, K;
    static int[][] board;
    static int[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int ans;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            board = new int[N][N];
            int high = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    high = Math.max(high, board[i][j]);
                }
            }
 
            visited = new int[N][N];
            ans = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == high) {
                        visited[i][j] = 1;
                        dfs(i, j, 1, 1);
                        visited[i][j] = 0;
                    }
                }
            }
 
            System.out.println("#" + tc + " " + ans);
        }
    }
 
    public static void dfs(int x, int y, int cnt, int cut) {
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d], ny = y + dy[d];
            if (0 <= nx && nx < N && 0 <= ny && ny < N && visited[nx][ny] == 0) {
                if (board[nx][ny] < board[x][y]) {
                    visited[nx][ny] = 1;
                    dfs(nx, ny, cnt + 1, cut);
                    visited[nx][ny] = 0;
                }
                else if (cut == 1) {
                    for (int k = 1; k <= K; k++) {
                        if (board[nx][ny] - k < board[x][y]) {
                            board[nx][ny] -= k;
                            dfs(nx, ny, cnt + 1, 0);
                            board[nx][ny] += k;
                        }
                    }
                }
            }
        }
 
        ans = Math.max(ans, cnt);
    }
}
