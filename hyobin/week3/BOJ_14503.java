import java.io.*;
import java.util.*;

public class BOJ_14503 {
    static int [][] arr;
    //뷱동남서 문제에서 0321 주어짐
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int n;
    static int m;
    static int cnt;

    public static void main(String[] args)  throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for (int i=0 ; i < n ; i++ ) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j <m ; j++ ) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(r,c,d);
        System.out.println(cnt);



    }
    static void dfs(int r, int c , int d ) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {r,c,d});
        cnt=1;
        arr[r][c]=-1;

        while (!queue.isEmpty()) {
            int[] clean = queue.poll();
            int x = clean[0];
            int y = clean[1];


            for (int i = 0 ; i<4;i++) {
                //반시계 방향으로 돌기, 방향 바뀜
                if (d == 0){
                    d=4;
                }
                d-= 1;

                int nx = x + dx[d%4];
                int ny = y + dy[d%4];


                if (0<= nx && nx<n && 0<= ny && ny<m ) {
                    if (arr[nx][ny] == 0) {
                        arr[nx][ny] = -1;
                        queue.add(new int[]{nx,ny});

                        cnt +=1;
                        break;
                    }
                }
            }
            if (queue.isEmpty()) {
                //북향일경우 남쪽, 서향일 경우 동쪽으로 이동,방향은 그대로

                int nx = x + dx[(d+2)%4];
                int ny = y + dy[(d+2)%4];
                if (0<= nx && nx<n && 0<= ny && ny<m ) {
                    if (arr[nx][ny] == -1) {
                        queue.add(new int[]{nx,ny});
                    }else {
                        return;
                    }
                }
            }

        }
    }
}
