import java.io.*;
import java.util.*;
public class Main {
    static int n,l,r;
    static int day;
    static int[][] map;
    static boolean isnext;
    static boolean[][] visited;
    static int[] dx={1,-1,0,0};
    static int[] dy={0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n=Integer.parseInt(st.nextToken());
        l=Integer.parseInt(st.nextToken());
        r=Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        isnext = true;
        day =0;
        while (isnext){
            visited = new boolean[n][n];
            isnext = false;
            for(int i =0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(!visited[i][j]){
                        bfs(i,j);
                    }
                }
            }
//            for(int i =0; i<n; i++){
//                for(int j=0; j<n; j++){
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();

            day++;
//            System.out.println(day);
        }
        System.out.println(day-1);
    }
    static void bfs(int x, int y){
        Queue<int[]> q = new LinkedList<>();
        LinkedList<int[]> point = new LinkedList<>();
        int cnt=1;
        int sum=map[x][y];

        point.add(new int[] {x,y});
        q.add(new int[] {x,y});
        visited[x][y] = true;
        while (!q.isEmpty()){
            int[] temp = q.poll();
            int tx = temp[0];
            int ty = temp[1];

            for(int i=0; i<4; i++){
                int nx = tx + dx[i];
                int ny = ty + dy[i];

                if(0<=nx && nx<n && 0<=ny && ny<n && !visited[nx][ny]) {
                    if (Math.abs(map[tx][ty] - map[nx][ny]) <= r && Math.abs(map[tx][ty] - map[nx][ny]) >= l) {
                        q.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                        point.add(new int[]{nx, ny});
                        cnt++;
                        sum += map[nx][ny];
                    }
                }
            }
        }
        if(point.size() >1) {

            isnext = true;
            divide(point, cnt, sum);
        }
    }
    static void divide(LinkedList<int[]> point, int cnt, int sum){
        int result = sum/cnt;
        for(int i =0; i<point.size(); i++){
            int tx = point.get(i)[0];
            int ty = point.get(i)[1];
            map[tx][ty] = result;

        }
    }

}
