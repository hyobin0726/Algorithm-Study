import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573 {
    /**
     * BOJ 2573 G4 빙산
     */

    static BufferedReader br;
    static StringTokenizer st;

    static int N,M;
    static int[][] arr;
    static boolean[][] check;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        check = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] != 0) check[i][j] = true;
            }
        }

        int time = 0;

        while(true){
            time++;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(arr[i][j] == 0) check[i][j] = false;
                    else check[i][j] = true;
                }
            }

            //녹이기 작업
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(check[i][j]){
                        int cnt = 0;
                        for (int k = 0; k < 4; k++) {
                            int nextX = i + dx[k];
                            int nextY = j + dy[k];

                            if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) continue;

                            if(arr[nextX][nextY] == 0 && !check[nextX][nextY]) cnt++;
                        }

                        int tmp = arr[i][j] - cnt;
                        if(tmp <= 0){
                            arr[i][j] = 0;
                        }else{
                            arr[i][j] = tmp;
                        }
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(arr[i][j] == 0) check[i][j] = true;
                    else check[i][j] = false;
                }
            }

            //완료
            //덩어리 몇개인지 확인
            int regionCnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(arr[i][j] != 0 && !check[i][j]){
                        bfs(i,j, check);
                        regionCnt++;
                    }
                }
            }

            if(regionCnt == 1){
                continue;
            } else if (regionCnt > 1) {
                System.out.println(time);
                break;
            } else if (regionCnt == 0) {
                System.out.println(0);
                break;
            }
        }


    }

    static class Node{
        int x,y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static void bfs(int x, int y, boolean[][] check){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x,y));
        check[x][y] = true;

        while (!queue.isEmpty()){
            Node now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) continue;
                if(check[nextX][nextY] || arr[nextX][nextY] == 0) continue;

                queue.add(new Node(nextX, nextY));
                check[nextX][nextY] = true;

            }
        }
    }
}
