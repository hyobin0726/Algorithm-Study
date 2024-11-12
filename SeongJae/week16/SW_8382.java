import java.io.*;
import java.util.*;

public class Solution {

    // 가로이동(하,상), 세로이동(우,좌)
    static int[] dx = { 1, -1, 0, 0 }; // 하, 상, 우, 좌
    static int[] dy = { 0, 0, 1, -1 }; // 하, 상, 우, 좌

    static class Info {
        int x, y, dir, cnt;

        Info(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            
        if (x1 == x2 && y1 == y2) {
            System.out.println("#" + tc + " " + 0);
        } else {
            System.out.println("#" + tc + " " + bfs(x1 + 100, y1 + 100, x2 + 100, y2 + 100));
        }
    
        }
    }
    private static int bfs(int x1, int y1, int x2, int y2) {
        Queue<Info> q = new LinkedList<>();
        boolean[][][] visited = new boolean[201][201][2]; 
        
        visited[x1][y1][0] = true;
        visited[x1][y1][1] = true;
        q.add(new Info(x1, y1, 0, 0)); // 가로 방향
        q.add(new Info(x1, y1, 2, 0)); // 세로 방향

        while (!q.isEmpty()) {
            Info cur = q.poll();
            if (cur.x == x2 && cur.y == y2) {
                return cur.cnt;
            }

            // 현재 가로 방향
            if (cur.dir == 0 || cur.dir == 1) {
                for (int dir = 2; dir < 4; dir++) { // 세로 방향
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];

                    if (nx >= 0 && ny >= 0 && nx <= 200 && ny <= 200 && !visited[nx][ny][dir/2]) {
                        visited[nx][ny][dir/2] = true;
                        q.add(new Info(nx, ny, dir, cur.cnt + 1)); // 세로 방향으로 이동
                        
                    }
                }
            } else { // 현재 세로 방향
                for (int dir = 0; dir < 2; dir++) { // 가로 방향
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];

                    if (nx >= 0 && ny >= 0 && nx <= 200 && ny <= 200 && !visited[nx][ny][dir/2]) {
                        visited[nx][ny][dir/2] = true;
                        q.add(new Info(nx, ny, dir, cur.cnt + 1)); 
                    }
                }
            }
        }
        return -1; 
    }
}
