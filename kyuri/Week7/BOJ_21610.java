import java.io.*;
import java.util.*;

/* 구현 */
class Cloud {
    int x, y;
    public Cloud(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int N, M;
    static int[][] board, visited;
    static Queue<Cloud> q = new LinkedList<>(); // 구름 위치 저장

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

        q.add(new Cloud(N-1, 0));
        q.add(new Cloud(N-1, 1));
        q.add(new Cloud(N-2, 0));
        q.add(new Cloud(N-2, 1));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            visited = new int[N][N];
            move(d, s);
            copy();
            newCloud();
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += board[i][j];
            }
        }
        System.out.println(sum);
    }

    // 구름 이동 위치 및 비 내림
    public static void move(int d, int s) {
        for (Cloud cloud : q) {
            cloud.x = (N + cloud.x + dx[d] * (s % N)) % N;
            cloud.y = (N + cloud.y + dy[d] * (s % N)) % N;
            board[cloud.x][cloud.y]++;
        }
    }

    // 대각서 비 복사
    public static void copy() {
        while (!q.isEmpty()) {
            Cloud cld = q.poll();
            visited[cld.x][cld.y] = 1;
            int cnt = 0;
            for (int i = 1; i <= 7; i+= 2) {
                int nx = cld.x + dx[i], ny = cld.y + dy[i];
                if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                    if (board[nx][ny] > 0) {
                        cnt++;
                    }
                }
            }
            board[cld.x][cld.y] += cnt;
        }
    }

    // 새로운 구름 생성 (직전 구름 위치 제외)
    public static void newCloud() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] != 1 && board[i][j] >= 2) {
                    board[i][j] -= 2;
                    q.add(new Cloud(i, j));
                }
            }
        }
    }

}
