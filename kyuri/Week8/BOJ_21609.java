import java.io.*;
import java.util.*;

/* 단순 구현 (bfs) */
public class Main {
    static int N, M, ans = 0;
    static int[][] board, visit;
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

    static class Block {
        int x, y, color, sum, rSum;

        public Block(int x, int y, int color, int sum, int rSum) {
            this.x = x;
            this.y = y;
            this.color = color;
            this.sum = sum;
            this.rSum = rSum;
        }

        public boolean compareBlock(Block other) {
            if (this.sum != other.sum)
                return this.sum < other.sum;
            if (this.rSum != other.rSum)
                return this.rSum < other.rSum;
            if (this.x != other.x)
                return this.x < other.x;
            return this.y < other.y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            Block maxB = findMax();
            if (maxB == null) break;
            ans += maxB.sum * maxB.sum;
            remove(maxB);
            down();
            rotate90();
            down();
        }

        System.out.println(ans);
    }

    public static Block findMax() {
        visit = new int[N][N];
        Block maxBlock = new Block(0, 0, -2, Integer.MIN_VALUE, Integer.MIN_VALUE);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] > 0 && visit[i][j] == 0) {
                    for (int a = 0; a < N; a++) {
                        for (int b = 0; b < N; b++) {
                            if (board[a][b] == 0) visit[a][b] = 0;
                        }
                    }
                    Block cur = bfs(i, j);
                    if (cur == null) continue;
                    if (maxBlock.compareBlock(cur)) maxBlock = cur;
                }
            }
        }

        if (maxBlock.color == -2) return null;
        return maxBlock;
    }

    public static Block bfs(int x, int y) {
        int num = board[x][y];
        int cnt = 1, r_cnt = 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x, y});
        visit[x][y] = 1;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d], ny = cur[1] + dy[d];
                if (0 <= nx && nx < N && 0 <= ny && ny < N && visit[nx][ny] == 0) {
                    if (board[nx][ny] == num) {
                        cnt++;
                        visit[nx][ny] = 1;
                        q.add(new int[] {nx, ny});
                    } else if (board[nx][ny] == 0) {
                        cnt++;
                        r_cnt++;
                        visit[nx][ny] = 1;
                        q.add(new int[] {nx, ny});
                    }
                }
            }
        }

        if (cnt < 2) return null;
        else return new Block(x, y, num, cnt, r_cnt);
    }

    public static void remove(Block block) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {block.x, block.y});
        visit = new int[N][N];
        visit[block.x][block.y] = 1;
        board[block.x][block.y] = -2;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d], ny = cur[1] + dy[d];
                if (0 <= nx && nx < N && 0 <= ny && ny < N && visit[nx][ny] == 0) {
                    if (board[nx][ny] == block.color || board[nx][ny] == 0) {
                        board[nx][ny] = -2;
                        visit[nx][ny] = 1;
                        q.add(new int[] {nx, ny});
                    }
                }
            }
        }
    }

    public static void down() {
        for (int i = 0; i < N-1; i++) {
            for (int j = 0; j < N; j++) {
                int P = i;
                while (0 <= P && board[P][j] != -1 && board[P+1][j] == -2) {
                    board[P+1][j] = board[P][j];
                    board[P][j] = -2;
                    P--;
                }
            }
        }
    }

    public static void rotate90() {
        int[][] temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[N-j-1][i] = board[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.copyOf(temp[i], N);
        }
    }

}
