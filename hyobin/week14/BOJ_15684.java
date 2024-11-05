package BOJ.구현;
import java.io.*;
import java.util.*;

public class 사다리조작 {
    static int n, m, h;
    static int[][] map;
    static List<line> lines;
    static int min = Integer.MAX_VALUE;

    static class line {
        int sx, sy, ex, ey;

        public line(int sx, int sy, int ex, int ey) {
            this.sx = sx;
            this.sy = sy;
            this.ex = ex;
            this.ey = ey;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int[h + 1][n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = b + 1;
            map[a][b + 1] = b;
        }

        lines = new ArrayList<>();
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j < n; j++) {
                if (map[i][j] == 0 && map[i][j + 1] == 0) {
                    lines.add(new line(i, j, i, j + 1));
                }
            }
        }

        // 초기 상태에서 사다리 타기가 가능한지 확인
        if (checkLadders()) {
            System.out.println(0);
            return;
        }

        dfs(0, 0);

        System.out.println(min > 3 ? -1 : min);
    }

    // DFS로 사다리 추가 및 복구
    static void dfs(int idx, int cnt) {
        if (cnt > 3 || cnt >= min) {  // 사다리 수가 3 초과 또는 최소값보다 큰 경우 종료
            return;
        }

        if (checkLadders()) {  // 사다리가 올바른지 검사
            min = Math.min(min, cnt);
            return;
        }

        for (int i = idx; i < lines.size(); i++) {
            int a = lines.get(i).sx;
            int b = lines.get(i).sy;
            int c = lines.get(i).ex;
            int d = lines.get(i).ey;

            if (map[a][b] == 0 && map[c][d] == 0) {  // 사다리 추가 조건 확인
                map[a][b] = b + 1;
                map[c][d] = d - 1;

                dfs(i + 1, cnt + 1);  // 사다리 추가 후 다음 위치 탐색

                map[a][b] = 0;  // 상태 복구
                map[c][d] = 0;
            }
        }
    }

    // 모든 세로줄이 올바르게 연결되었는지 확인
    static boolean checkLadders() {
        for (int i = 1; i <= n; i++) {
            if (!ladder(i)) {
                return false;
            }
        }
        return true;
    }

    // 특정 시작점에서 사다리 타기 수행
    static boolean ladder(int num) {
        int temp = num;
        for (int i = 1; i <= h; i++) {
            if (map[i][temp] > 0) {
                temp = map[i][temp];
            }
        }
        return temp == num;
    }
}
