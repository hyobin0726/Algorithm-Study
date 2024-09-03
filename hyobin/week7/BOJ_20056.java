import java.io.*;
import java.util.*;

public class BOJ_20056 {
    static int n, f, k;
    // 0: 질량, 1: 속도, 2: 방향
    static List<Fireball>[][] map;
    // 0: 상, 1: 우상, 2: 우, 3: 우하, 4: 하, 5: 좌하, 6: 좌, 7: 좌상
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    static class Fireball {
        int r, c, m, s, d;

        Fireball(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        f = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        List<Fireball> fireballs = new ArrayList<>();

        for (int i = 0; i < f; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fireballs.add(new Fireball(r, c, m, s, d));
        }

        for (int i = 0; i < k; i++) {
            move(fireballs);
            divide(fireballs);
        }

        int totalMass = 0;
        for (Fireball fb : fireballs) {
            totalMass += fb.m;
        }

        System.out.println(totalMass);
    }

    static void move(List<Fireball> fireballs) {
        map = new ArrayList[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for (Fireball fb : fireballs) {
            // 이동 후 위치
            // (fb.r + dx[fb.d] * fb.s % n + n) % n : 음수일 경우 n을 더해주고 n으로 나눠주기
            int nx = (fb.r + dx[fb.d] * fb.s % n + n) % n;
            int ny = (fb.c + dy[fb.d] * fb.s % n + n) % n;
            fb.r = nx;
            fb.c = ny;
            map[nx][ny].add(fb);
        }
    }

    static void divide(List<Fireball> fireballs) {
        fireballs.clear();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j].size() == 0) continue;
                if (map[i][j].size() == 1) {
                    fireballs.addAll(map[i][j]);
                    continue;
                }

                int sumM = 0, sumS = 0, count = map[i][j].size();
                boolean allEven = true, allOdd = true;

                for (Fireball fb : map[i][j]) {
                    sumM += fb.m;
                    sumS += fb.s;
                    //모두 홀수거나 짝수인지 확인
                    if (fb.d % 2 == 0) allOdd = false;
                    else allEven = false;
                }

                int nm = sumM / 5;
                int ns = sumS / count;

                if (nm == 0) continue;

                for (int d = 0; d < 4; d++) {
                    int nd = (allEven || allOdd) ? d * 2 : d * 2 + 1;
                    fireballs.add(new Fireball(i, j, nm, ns, nd));
                }
            }
        }
    }
}
