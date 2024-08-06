import java.io.*;
import java.util.*;

public class BOJ_17391 {
    static int n;
    static int m;
    static int[][] arr;
    static int[][] result;


    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        result = new int[n][m];
        for (int i=0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0,0});
//cnt를 업데이트하며 탐색
        while (!queue.isEmpty()){
            int[] temp = queue.poll();
            int x = temp[0];
            int y = temp[1];

            for (int i = 1; i < arr[x][y]+1; i++){
                int nx = x +i;
                //방문한적 없거나, 현재 cnt보다 짧은 경우
                if (nx<n && (result[nx][y] ==0 || result[nx][y] > result[x][y]+1)){
                    result[nx][y] = result[x][y]+1;
                    queue.add(new int[] {nx,y});
                }

            }for (int i = 1; i < arr[x][y]+1; i++){
                int ny = y +i;
                if (ny<m && (result[x][ny] ==0 || result[x][ny] > result[x][y]+1)){
                    result[x][ny] = result[x][y]+1;
                    queue.add(new int[] {x,ny});
                }
            }
        }
        System.out.println(result[n-1][m-1]);
    }
}
