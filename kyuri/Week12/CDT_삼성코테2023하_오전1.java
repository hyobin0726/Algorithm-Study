import java.util.*;
import java.io.*;

public class Main {
    public static final int MAX_N = 31;
    public static final int MAX_L = 41;

    public static int L, N, Q;
    public static int[][] info = new int[MAX_L][MAX_L];
    public static int[] bef_k = new int[MAX_N]; // 기사 별 초기 체력
    public static int[] r = new int[MAX_N], c = new int[MAX_N], h = new int[MAX_N], w = new int[MAX_N], k = new int[MAX_N];
    public static int[] nr = new int[MAX_N], nc = new int[MAX_N], dmg = new int[MAX_N];
    public static boolean[] is_moved = new boolean[MAX_N];
    public static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    public static boolean tryMovement(int idx, int dir) {
        // 초기화
        for(int i = 1; i <= N; i++) {
            dmg[i] = 0;
            is_moved[i] = false;
            nr[i] = r[i];
            nc[i] = c[i];
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(idx);
        is_moved[idx] = true;

        while(!q.isEmpty()) {
            int x = q.poll();
            nr[x] += dx[dir];
            nc[x] += dy[dir];

            if(nr[x] < 1 || nc[x] < 1 || nr[x] + h[x] - 1 > L || nc[x] + w[x] - 1 > L)
                return false;

            // 다른 조각이나 장애물과 충돌하는지 검사
            for(int i = nr[x]; i <= nr[x] + h[x] - 1; i++) {
                for(int j = nc[x]; j <= nc[x] + w[x] - 1; j++) {
                    if(info[i][j] == 1) dmg[x]++;
                    if(info[i][j] == 2) return false;
                }
            }

            // 다른 조각과 충돌하는 경우, 해당 조각도 이동
            for(int i = 1; i <= N; i++) {
                if(is_moved[i] || k[i] <= 0) continue;
                if(r[i] > nr[x] + h[x] - 1 || nr[x] > r[i] + h[i] - 1) continue;
                if(c[i] > nc[x] + w[x] - 1 || nc[x] > c[i] + w[i] - 1) continue;
                q.add(i);
                is_moved[i] = true;
            }
        }

        dmg[idx] = 0;
        return true;
    }

    public static void movePiece(int idx, int dir) {
        if(k[idx] <= 0) return;

        if(tryMovement(idx, dir)) {
            for(int i = 1; i <= N; i++) {
                r[i] = nr[i];
                c[i] = nc[i];
                k[i] -= dmg[i];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= L; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= L; j++)
                info[i][j] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            r[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
            h[i] = Integer.parseInt(st.nextToken());
            w[i] = Integer.parseInt(st.nextToken());
            k[i] = Integer.parseInt(st.nextToken());
            bef_k[i] = k[i];
        }

        for(int i = 1; i <= Q; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            movePiece(idx, dir);
        }

        long ans = 0;
        for(int i = 1; i <= N; i++) {
            if(k[i] > 0) ans += bef_k[i] - k[i];
        }

        System.out.println(ans);
    }
}
