import java.io.*;
import java.util.*;

public class BOJ_3190 {
    static  int[] dx = {0,1,0,-1};
    static  int[] dy = {1,0,-1,0};
    static int n;
    static int[][] map;
    static  HashMap<Integer, String> move = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        map = new int[n+1][n+1];
        for(int i =0; i<k;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] =1;
        }


        int l = Integer.parseInt(br.readLine());

        for(int i =0; i<l; i++){
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();
            move.put(time, direction);

        }
        solve();
    }
    static void solve() {
        LinkedList<int[]> snake = new LinkedList<>();
        snake.add(new int[]{1,1});
        int start = 0;
        int px=1;
        int py=1;
        int d =0;

        while(true){
            int nx = px+ dx[d];
            int ny = py + dy[d];
            start++;
            if(nx<1 || ny<1 || nx>n || ny>n){
                System.out.println(start);
                return;
            }

            for (int[] part : snake) {
                if (part[0] == nx && part[1] == ny) {
                    System.out.println(start);
                    return;
                }
            }

            if (map[nx][ny] == 1) {
                map[nx][ny] = 0;
                snake.add(new int[]{nx,ny});
            }else {
                snake.add(new int[]{nx,ny});
                snake.poll();
            }
            if (move.containsKey(start)){
                String data = move.get(start);
                if (data.equals("D")){
                    d+=1;
                    if(d==4) {
                        d=0;
                    }
                }else {
                    d-=1;
                    if(d==-1){
                        d=3;
                    }
                }
            }
            px=nx;
            py=ny;
        }
    }
}

