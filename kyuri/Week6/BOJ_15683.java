import java.io.*;
import java.util.*;

/*
  dir : 각 CCTV의 type 별 회전했을 때의 방향 저장
  cctv : 해당 좌표와 cctv 번호 저장
  조합으로 cctv의 모든 경우의 수 확인
*/
public class Main {
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static int[][][] dir = {{{0}}, {{0}, {1}, {2}, {3}}, {{2, 3}, {0, 1}},
            {{0, 3}, {1, 3}, {1, 2}, {0, 2}},
            {{0, 2, 3}, {0, 1, 3}, {1, 2, 3}, {0, 1, 2}},
            {{0, 1, 2, 3}}};
    static int N, M, ans;
    static int[][] map;
    static ArrayList<int[]> cctv;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cctv = new ArrayList<>();
        int zero = 0;
        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] >= 1 && map[i][j] <= 5){
                    cctv.add(new int[] {i, j, map[i][j]});
                } else if (map[i][j] == 0) zero++;
            }
        }

        ans = zero;
        combination(0, cctv.size(), map);
        System.out.println(ans);
    }

    public static void combination(int depth, int r, int[][] map) {
        if(depth == r) {
            // 모든 cctv를 확인한 후 사각지대 세기
            int cnt = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(map[i][j] == 0) cnt++;
                }
            }
            ans = Math.min(ans, cnt);
            return;
        }

        int num = cctv.get(depth)[2], x = cctv.get(depth)[0], y = cctv.get(depth)[1];
        for(int i = 0; i < dir[num].length; i++) {
            int[][] map_copy = new int[N][M];
            for(int k = 0; k < N; k++) {
                map_copy[k] = map[k].clone();
            }

            for(int j = 0; j < dir[num][i].length; j++){
                int d = dir[num][i][j];
                int nx = x + dx[d], ny = y + dy[d];
                while (true) {
                    if(nx < 0 || nx >= N || ny < 0 || ny >= M) break;
                    if(map[nx][ny] == 6) break;
                    map_copy[nx][ny] = -1;
                    nx += dx[d];
                    ny += dy[d];
                }
            }

            combination(depth + 1, r, map_copy);
        }
    }

}
