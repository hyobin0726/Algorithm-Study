import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class wall{
        int x,y,sum;
        boolean destroy;

        public wall(int x, int y, int sum, boolean destroy) {
            this.x = x;
            this.y = y;
            this.sum = sum;
            this.destroy = destroy;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j) - '0';

            }
        }
        bfs();
    }

    static void bfs() {
        Queue<wall> q = new LinkedList<>();
        //x,y,칸이동수, 벽부신여부
        q.add(new wall (0, 0, 1,false));
        visited = new boolean[n][m][2];
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            wall temp = q.poll();
            if (temp.x == n - 1 && temp.y == m - 1) {
                System.out.println(temp.sum);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = temp.x + dx[i];
                int ny = temp.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m ){
                    //벽아닌경우
                    if(map[nx][ny] == 0){
                        //부신적이 없다면
                        if(!temp.destroy && !visited[nx][ny][0]){
                            q.add(new wall(nx,ny,temp.sum+1, false));
                            visited[nx][ny][0] = true;
                        //부신적이 있다면
                        }else if(temp.destroy && !visited[nx][ny][1]){
                            q.add(new wall(nx,ny, temp.sum+1, true));
                            visited[nx][ny][1] =true;
                        }
                    }
                    //벽인경우
                    else {
                        //한번도 부신적이없다면
                        if(!temp.destroy){
                            //이제부터 true 부신상태로 돌아감
                            q.add(new wall(nx,ny,temp.sum+1, true));
                            visited[nx][ny][1] =true;
                        }
                    }
                }
            }
        }
        System.out.println(-1);
    }
}
