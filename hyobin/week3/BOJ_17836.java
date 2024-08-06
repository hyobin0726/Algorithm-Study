import java.io.*;
import java.util.*;
public class BOJ_17836 {
    static int n;
    static int m;
    static int t;
    static int[][] arr;
    static int[][] visited;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        visited = new int[n][m];


        for (int i=0; i<n ;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs(0,0);
        //시간을 초과하는 경우, 벽이 있는 경우
        if (visited[n-1][m-1]>t || visited[n-1][m-1]==0) {
            System.out.println("Fail");
        }else if (visited[n-1][m-1] > 0) {
            System.out.println(visited[n-1][m-1]);
        }
    }
    static void bfs(int a, int b) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {a,b});
        int gram =0;

        while (!queue.isEmpty()) {

            int[] temp = queue.poll();
            int x= temp[0];
            int y = temp[1];

            for (int i =0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0<= nx  && nx<n && 0<= ny && ny<m) {
                    if (arr[nx][ny] == 2 ) {
                        arr[nx][ny]=1;
                        visited[nx][ny] = visited[x][y]+1;
                        gram = visited[nx][ny] + (n-1-nx) + (m-1-ny);
                    }
                    if (arr[nx][ny] == 0 && (visited[nx][ny] == 0 || visited[nx][ny] > visited[x][y] +1)) {
                        visited[nx][ny] = visited[x][y]+1;
                        queue.add(new int[] {nx,ny});
                    }
                }
            }
        }
        //벽때문에 못가는 경우, 그람을 활용한 경우
        if (visited[n-1][m-1] == 0){
            visited[n-1][m-1]=gram;
        }else if (visited[n-1][m-1] > gram){
            visited[n-1][m-1]=gram;
        }
    }

}