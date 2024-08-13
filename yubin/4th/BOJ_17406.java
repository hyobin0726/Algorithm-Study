import java.io.*;
import java.util.*;
import java.util.jar.JarEntry;

public class BOJ_17406 {

    static int N,M,K;
    static int[][] arr;
    static int[][] turnArr;
    static int result = Integer.MAX_VALUE;
    static int[] turn;
    static boolean[] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        turnArr = new int[K][3];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            turnArr[i][0] = Integer.parseInt(st.nextToken()) - 1;
            turnArr[i][1] = Integer.parseInt(st.nextToken()) - 1;
            turnArr[i][2] = Integer.parseInt(st.nextToken());
        }

        turn = new int[K];
        visited = new boolean[K];

        order(0);

        System.out.println(result);
    }

    public static void order(int depth){
        if(depth == K){
            int min = turning();
            result = Math.min(min, result);
            return;
        }
        for (int i = 0; i < K; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            turn[depth] = i;
            order(depth + 1);
            visited[i] = false;
        }
    }

    public static int turning(){
        int[][] temp = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[i][j] = arr[i][j];
            }
        }

        for (int i = 0; i < K; i++) {
            int r = turnArr[turn[i]][0];
            int c = turnArr[turn[i]][1];
            int s = turnArr[turn[i]][2];

            //회전시키기

            int[] point1 = {r-s, c-s}; //왼쪽위
            int[] point2 = {r+s, c+s};

            int turnNum = Math.min(point2[0]-point1[0]+1, point2[1]-point1[1]+1) / 2;

            for (int j = 0; j < turnNum; j++) {
                int x = point1[0] + j;
                int y = point1[1] + j;
                int startX = x;
                int startY = y;
                int tmpNum = temp[x][y];
                int dir = 0;

                while(dir < 4) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];

                    if(nx < point1[0]+j || ny < point1[1]+j || nx > point2[0]-j || ny > point2[1]-j) {
                        dir++;
                        continue;
                    }

                    temp[x][y] = temp[nx][ny];
                    x = nx;
                    y = ny;
                }

                temp[startX][startY+1] = tmpNum;
            }
        }

        int sum = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++) {
            int minTmp = 0;
            for(int j = 0; j < M; j++) {
                minTmp += temp[i][j];
            }
            sum = Math.min(sum, minTmp);
        }
        return sum;
    }

}


