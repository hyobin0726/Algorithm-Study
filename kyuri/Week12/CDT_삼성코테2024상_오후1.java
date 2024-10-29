import java.io.*;
import java.util.*;

public class Main {
    static int R, C, K;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    static boolean[][] isExit;
    static int ans = 0;

    // 숲 내에 있는지 확인
    public static boolean isRange(int x, int y) {
        if (3 <= x && x < R + 3 && 0 <= y && y < C) {
            return true;
        } return false;
    }

    // 맵 초기화
    public static void reset() {
        for (int i = 0; i < R + 3; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = 0;
                isExit[i][j] = false;
            }
        }
    }

    // 중심이 x, y일 때 가능한지 확인
    public static boolean canMove(int x, int y) {
        boolean flag = (0 <= y - 1 && y + 1 < C && x + 1 < R + 3);
        flag = flag && (map[x-1][y-1] == 0);
        flag = flag && (map[x-1][y] == 0);
        flag = flag && (map[x-1][y+1] == 0);
        flag = flag && (map[x][y-1] == 0);
        flag = flag && (map[x][y] == 0);
        flag = flag && (map[x][y+1] == 0);
        flag = flag && (map[x+1][y] == 0);
        return flag;
    }

    // 정령 이동 확인
    public static int bfs(int x, int y) {
        int res = x;
        Queue<int[]> q = new LinkedList<>();
        int[][] visit = new int[R + 3][C];
        q.offer(new int[] {x, y});
        visit[x][y] = 1;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int k = 0; k < 4; k++) {
                int nx = cur[0] + dx[k], ny = cur[1] + dy[k];
                if (isRange(nx, ny) && visit[nx][ny] == 0) {
                    if (map[nx][ny] == map[cur[0]][cur[1]] || (map[nx][ny] != 0 && isExit[cur[0]][cur[1]])) {
                        q.offer(new int[] {nx, ny});
                        visit[nx][ny] = 1;
                        res = Math.max(res, nx);
                    }
                }
            }
        }
        return res;
    }

    // 이동 및 결과 계산
    public static void down(int x, int y, int d, int id) {
        if (canMove(x + 1, y)) {
            down(x + 1, y, d, id);
        } else if (canMove(x + 1, y - 1)) {
            down(x + 1, y - 1, (d+3)%4, id);
        } else if (canMove(x + 1, y + 1)) {
            down(x + 1, y + 1, (d+1)%4, id);
        }
        // 골렘이 더 이상 이동하지 못하는 경우
        else {
            if (!isRange(x - 1, y - 1) || !isRange(x  + 1, y + 1)) {
                reset();
            } else {
                map[x][y] = id;
                for (int k = 0; k < 4; k++) {
                    map[x + dx[k]][y + dy[k]] = id;
                }
                isExit[x + dx[d]][y + dy[d]] = true;
                ans += bfs(x, y) - 3 + 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[R+3][C];
        isExit = new boolean[R+3][C];
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            down(0, c, d, i);
        }

        System.out.println(ans);
    }

}
