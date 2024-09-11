import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] map;
    static int eat;
    static int[] dx = {-1,0,0,1};
    static int[] dy = {0,-1,1, 0};
    static boolean[][] visited;
    static int shark;
    static int ans;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        StringTokenizer st;
        ans =0;
        eat =0; // 먹은 마리 수
        shark=2; // 초기 상어 크기
        int s =0;
        int e =0;

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) {
                    //상어 초기 좌표
                    s =i;
                    e=j;
                    map[i][j] =0;
                }
            }
        }
        while (true){
            // 거리비교 -> x 값 비교 -> y 값 비교
            PriorityQueue<int[]> q = new PriorityQueue<>((a,b) -> a[2] != b[2] ? Integer.compare(a[2], b[2])  : (a[0] != b[0] ? Integer.compare(a[0], b[0])  : Integer.compare(a[1], b[1])));
            q.add(new int[] {s,e, 0});
            visited = new boolean[n][n];
            visited[s][e] =true;
            boolean check = false; // 물고기 먹었는지 체크하면서 반복


            while (!q.isEmpty()) {

                int[] temp = q.poll();
                int tx = temp[0];
                int ty = temp[1];
                int dist = temp[2];
                //물고기 먹을 수 있는 경우 (0이 아니면서 크기보다 작은경우)
                if (map[tx][ty] != 0 && shark > map[tx][ty]) {
                    map[tx][ty] = 0; //물고기 먹음
                    eat++;
                    ans += dist;
                    check = true;
                    s=tx;
                    e=ty;
                    break;

                }
                for (int i = 0; i < 4; i++) {
                    int nx = tx + dx[i];
                    int ny = ty + dy[i];
                    if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                        if (!visited[nx][ny] && shark >= map[nx][ny]) {
                            visited[nx][ny] = true;
                            //x,y,거리 증가
                            q.add(new int[]{nx, ny, dist+1});
                        }
                    }
                }
            }
            //먹을 물고기가 없다면
            if(!check){
                break;
            }
            //상어 크기 업
            if (shark == eat){
                shark++;
                eat =0;
            }

            }
        System.out.println(ans);

    }

}
