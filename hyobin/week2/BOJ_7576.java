import java.io.*;
import java.util.*;

public class BOJ_7576 {
    static int n;
    static int m;
    static int[][] arr;
    static int[] dx = {0,0,-1,1 };
    static int[] dy = {-1,1,0,0 };
    static int day=0;

    static Queue<int[]> queue = new LinkedList<int[]>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

       arr = new int[n][m];
        for (int i = 0; i <n; i++ ){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1){
                    queue.offer(new int[] {j,i});
                }
            }
        }
        System.out.println(bfs());




    }
    static int bfs() {
        while (!queue.isEmpty()) {
            int[] t = queue.poll();
            int x = t[0];
            int y = t[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (0 <= nx && nx < m && 0 <= ny && ny < n) {
                    if (arr[ny][nx] == 0) {
                        queue.add(new int[]{nx, ny});
                        arr[ny][nx] = arr[y][x]+1;

                    }
                }
            }
        }
        for (int i=0; i <n ; i++){
            for (int j =0; j<m; j++){
                if (arr[i][j] == 0){
                    return -1;
                }
                day = Math.max(day, arr[i][j]);
            }
        }
        if (day  == 1){
            return 0;
        } else {
            return day-1;
        }
    }
}
