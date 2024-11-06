import java.util.*;
import java.io.*;

public class Main {
    static int N, ans = 0;
    static String[][] map;
    static ArrayList<int[]> teachers = new ArrayList<>();
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        map = new String[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken();
                if (map[i][j].equals("T")) {
                    teachers.add(new int[]{i, j});
                }
            }
        }

        obj(0);
        System.out.println(ans == 0 ? "NO" : "YES");
    }

    public static void obj(int cnt) {
        if (cnt == 3) {
            check();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].equals("X")) {
                    map[i][j] = "O";
                    obj(cnt + 1);
                    map[i][j] = "X";
                }
            }
        }
    }

    public static void check() {
        boolean ch = true;
        outer:
        for (int[] t : teachers) {
            for (int d = 0; d < 4; d++) {
                for (int i = 1; i < N; i++) {
                    int nx = t[0] + (dx[d] * i);
                    int ny = t[1] + (dy[d] * i);
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) break;
                    if (map[nx][ny].equals("O")) break;
                    if (map[nx][ny].equals("S")) {
                        ch = false;
                        break outer;
                    }
                }
            }
        }

        if (ch) ans++;
    }
}
