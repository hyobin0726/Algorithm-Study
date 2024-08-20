import java.io.*;
import java.util.*;

public class BOJ_2178 {
    static int n;
    static int m;
    static int[][] arr;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int[][] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        visited = new int[n][m];
        for (int i =0; i<n; i++){
            String[] str = br.readLine().split("");
            for(int j = 0 ;j<m;j++ ){
                arr[i][j] = Integer.parseInt(str[j]);
            }
        }
        bfs();
        System.out.println(visited[n-1][m-1]);
    }
    static void bfs(){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {0,0});
        visited[0][0] = 1;

        while (!queue.isEmpty()){
            int[] temp = queue.poll();
            int x = temp[0];
            int y = temp[1];
            for (int i =0; i <4; i++){
                int nx = x + dx[i];
                int ny = y+ dy[i];

                if (0<=nx && nx<n && 0<= ny && ny<m){
                    if (arr[nx][ny]==1 && ( visited[nx][ny] == 0 | visited[nx][ny] > visited[x][y]+1 )){
                        visited[nx][ny] =  visited[x][y]+1;
                        queue.add(new int[] {nx,ny});
                    }
                }
            }
        }

    }

}
