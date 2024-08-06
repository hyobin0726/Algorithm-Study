import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.function.Function;

public class BOJ_17391 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        for(int i = 0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < m ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int result = bfs(map,n,m);
        System.out.println(result);
    }
    private static final int[] DY = {0,1};
    private static final int[] DX = {1,0};
    private static int bfs(int[][] map, int n, int m) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,0});
        int time = 0;
        boolean[][] visited = new boolean[n][m];
        while(!q.isEmpty()){
            int size = q.size();
            for(int s = 0 ; s < size ; s++){
                int[] now = q.poll();
                if(now[0] == n-1 && now[1] == m-1){
                    return time;
                }
                int power = map[now[0]][now[1]];
                for(int i = 0 ; i < 2; i++){
                    for(int j = 1 ; j <= power ; j++){
                        int ny = now[0] + j * DY[i];
                        int nx = now[1] + j * DX[i];
                        if(ny >= n || nx >= m){
                            break;
                        }
                        if(!visited[ny][nx]){
                            visited[ny][nx] = true;
                            q.offer(new int[]{ny,nx});
                        }
                    }
                }
            }
            time++;
        }
        return -1;
    }
}