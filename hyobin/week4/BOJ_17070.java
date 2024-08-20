import java.io.*;
import java.util.*;

public class BOJ_17070 {
    static int n;
    static int[][] arr ;
    static boolean[][] visited ;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        arr = new int[n][n];
        visited = new boolean[n][n];

        for (int i =0; i < n ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1){
                    visited[i][j] = true;
                }

            }
        }

        visited[0][0] = true;
        visited[0][1] = true;
        pipe(0,1,1);
        System.out.println(cnt);

    }
    //가로1, 세로2, 대각선3
    static void pipe(int x , int y , int d ) {
        if (x == n-1 && y == n-1) {
            cnt+=1;
            return;
        }

        if (d == 1) {
            if (y+1 < n && arr[x][y+1] == 0 && !visited[x][y+1]) {
                visited[x][y+1] = true;
                pipe(x, y+1, 1);
                visited[x][y+1] = false;
            }
            if (x+1 < n &&  y+1 < n && arr[x+1][y+1] == 0  && !visited[x+1][y+1] &&!visited[x][y+1] &&!visited[x+1][y] ) {
                visited[x+1][y+1] = true;
                pipe(x+1, y+1, 3);
                visited[x+1][y+1] = false;

            }
        }
        else if (d ==2) {
            if (x+1 < n && arr[x+1][y] == 0 && !visited[x+1][y]) {
                visited[x+1][y] = true;
                pipe(x+1, y, 2);
                visited[x+1][y] = false;
            }
            if (x+1 < n &&  y+1 < n && arr[x+1][y+1] == 0 && !visited[x+1][y+1] &&!visited[x][y+1] &&!visited[x+1][y] ) {
                visited[x+1][y+1] = true;
                pipe(x+1, y+1, 3);
                visited[x+1][y+1] = false;

            }
        }
        else if (d==3) {
            if (x+1 < n && arr[x+1][y] == 0 && !visited[x+1][y]) {
                visited[x+1][y] = true;
                pipe(x+1, y, 2);
                visited[x+1][y] = false;
            }
            if (y+1 < n && arr[x][y+1] == 0&& !visited[x][y+1]) {
                visited[x][y+1] = true;
                pipe(x, y+1, 1);
                visited[x][y+1] = false;
            }
            if (x+1 < n &&  y+1 < n && arr[x+1][y+1] == 0 && !visited[x+1][y+1] &&!visited[x][y+1] &&!visited[x+1][y] ) {
                visited[x+1][y+1] = true;
                pipe(x+1, y+1, 3);
                visited[x+1][y+1] = false;

            }
        }
    }
}