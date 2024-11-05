import java.io.*;
import java.util.*;

public class Main {
    static int n, m, d;
    static int[][] map;
    static int[][] copy;
    static int ans;

    static Set<String> temp; // 중복 제거를 위한 Set으로 변경
    static int[] dx = {0, -1, 0};
    static int[] dy = {-1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        copy = new int[n][m];
        ans = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                copy[i][j] = map[i][j];
            }
        }

        // 궁수 위치 조합
        boolean[] arr = new boolean[m];
        combi(0, 0, arr);
        System.out.println(ans);
    }

    static void combi(int idx, int depth, boolean[] arr) {
        if (depth == 3) {
            List<Integer> archer = new LinkedList<>();
            for (int i = 0; i < m; i++) {
                if (arr[i]) {
                    archer.add(i);
                }
            }
            game(archer);

            // 맵 복원
            for (int i = 0; i < n; i++) {
                for(int j=0; j<m; j++){
                    map[i][j] =copy[i][j];
                }
            }
            return;
        }
        for (int i = idx; i < m; i++) {
            arr[i] = true;
            combi(i + 1, depth + 1, arr);
            arr[i] = false;
        }
    }

    static void game(List<Integer> archer) {
        boolean isgame = true;
        int tans = 0;
        while (isgame) {
            isgame = false;
            temp = new HashSet<>(); 

            // 각 궁수가 공격할 적 찾기
            for (int arch : archer) {
                bfs(arch);
            }

            // 적 제거
            for (String t : temp) {
                String[] parts = t.split(",");
                int tx = Integer.parseInt(parts[0]);
                int ty = Integer.parseInt(parts[1]);
                if (map[tx][ty] == 1) {
                    map[tx][ty] = 0;
                    tans++;
                }
            }

            // 적 이동
            for (int j = 0; j < m; j++) {
                if (map[n - 1][j] == 1) {
                    map[n - 1][j] = 0;
                }
            }
            for (int i = n - 2; i >= 0; i--) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 1) {
                        map[i][j] = 0;
                        map[i + 1][j] = 1;
                        isgame = true;
                    }
                }
            }
        }
        ans = Math.max(ans, tans);
    }

    static void bfs(int y) {
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> q = new LinkedList<>();
        visited[n - 1][y] = true;
        q.add(new int[]{n - 1, y, 1});

        int[] target = null;

        while (!q.isEmpty()) {
            int[] t = q.poll();
            int tx = t[0];
            int ty = t[1];
            int cnt = t[2];

            if (cnt > d) break;

            // 적 발견 시 처리 (거리 우선, 가장 왼쪽 우선)
            if (map[tx][ty] == 1) {
                if (target == null || cnt < target[2] || (cnt == target[2] && ty < target[1])) {
                    target = new int[]{tx, ty, cnt};
                }
                continue;
            }

            // 3방향 탐색
            for (int i = 0; i < 3; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < m && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny, cnt + 1});
                }
            }
        }

        // 타겟이 존재하면 temp에 추가
        if (target != null) {
            temp.add(target[0] + "," + target[1]);
        }
    }
}
