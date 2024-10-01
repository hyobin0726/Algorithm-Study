package BOJ.구현;
import java.io.*;
import java.util.*;

public class 연구소3 {
    static int n, m;
    static int[][] map;
    static List<int[]> virus;
    static int emptySpace = 0;
    static int minTime = Integer.MAX_VALUE;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        virus = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    virus.add(new int[]{i, j});
                } else if (map[i][j] == 0) {
                    emptySpace++;
                }
            }
        }

        if (emptySpace == 0) {
            System.out.println(0);
            return;
        }

        List<int[]> list = new ArrayList<>();
        combi(0, list, 0);

        System.out.println(minTime == Integer.MAX_VALUE ? -1 : minTime);
    }

    static void combi(int cnt, List<int[]> list, int idx) {
        if (cnt == m) {
            bfs(list);
            return;
        }

        for (int i = idx; i < virus.size(); i++) {
            list.add(virus.get(i));
            combi(cnt + 1, list, i + 1);
            list.remove(list.size() - 1);
        }
    }

    static void bfs(List<int[]> activeVirus) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        int[][] time = new int[n][n];

        for (int[] v : activeVirus) {
            queue.add(v);
            visited[v[0]][v[1]] = true;
        }

        int infected = 0;
        int currentTime = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny] && (map[nx][ny] == 0 || map[nx][ny] == 2)) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                    time[nx][ny] = time[x][y] + 1;

                    if (map[nx][ny] == 0) {
                        infected++;
                        currentTime = time[nx][ny];
                    }
                }
            }
        }

        if (infected == emptySpace) {
            minTime = Math.min(minTime, currentTime);
        }
    }
}
