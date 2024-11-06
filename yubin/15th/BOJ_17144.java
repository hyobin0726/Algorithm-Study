import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17144 {
    /**
     * BOJ 17144 G4 미세먼지 안녕!
     */
    static BufferedReader br;
    static StringTokenizer st;

    static int R,C,T;
    static int[][] arr;
    static AirMachine airMachine;
    static int[] dx = {-1,1,0,0};  //4방 탐색 용도
    static int[] dy = {0,0,-1,1};
    static int[] dx1 = {-1,0,1,0}; //시계방향, 위에 사용
    static int[] dy1 = {0,1,0,-1};
    static int[] dx2 = {1,0,-1,0}; //반시계방향, 아래쪽 사용
    static int[] dy2 = {0,1,-0,-1};

    static int sum;

    static class AirMachine{
        int x1, y1;
        int x2, y2;
        public AirMachine(int x1, int y1, int x2, int y2){
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());  //몇 초

        arr = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == -1 && airMachine == null) {
                    airMachine = new AirMachine(i, j, i + 1, j);
                }
            }
        }
        
        for (int t = 0; t < T; t++) {
            spread(arr);
            runMachine1(arr, airMachine.x1, airMachine.y1, dx1, dy1);
            runMachine2(arr, airMachine.x2, airMachine.y2, dx2, dy2);

        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] != -1) {
                    sum += arr[i][j];
                }
            }
        }

        System.out.println(sum);
    }
    static void spread(int[][] arr){
        int[][] addArr = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(arr[i][j] >= 5){
                    int spreadNum = arr[i][j] / 5;
                    int spreadCnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int nextX = i + dx[k];
                        int nextY = j + dy[k];
                        
                        if(nextX < 0 || nextY < 0 || nextX >= R || nextY >= C) continue;
                        if(arr[nextX][nextY] == -1) continue;
                        
                        addArr[nextX][nextY] += spreadNum;
                        spreadCnt++;
                    }
                    
                    arr[i][j] -= spreadNum * spreadCnt;
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                arr[i][j] += addArr[i][j];
            }
        }
    }

    static void runMachine1(int[][] arr, int x, int y, int[] dx, int[] dy){
        //위쪽
        int index = 0;
        int moveX = x;
        int moveY = y;
        while(true){

            int nextX = moveX + dx[index];
            int nextY = moveY + dy[index];


            //경계 만나면 방향 바꾸기
            if(nextX < 0 || nextY < 0 || nextX >= R || nextY >= C || (nextX == x && nextY == y) || (nextY == 0 || nextY == C-1) && nextX > x) {
                index++;
                if (index == 4) {
                    arr[moveX][moveY] = 0;
                    break;  // 네 방향 모두 확인하면 종료
                }
                continue;
            }

            //값 이동하기
            if(moveX == x && moveY == y) {
            }else{
                arr[moveX][moveY] = arr[nextX][nextY];
            }
            moveX = nextX;
            moveY = nextY;

        }

    }

    static void runMachine2(int[][] arr, int x, int y, int[] dx, int[] dy){
        //위쪽
        int index = 0;
        int moveX = x;
        int moveY = y;
        while(true){

            int nextX = moveX + dx[index];
            int nextY = moveY + dy[index];


            //경계 만나면 방향 바꾸기
            if(nextX < 0 || nextY < 0 || nextX >= R || nextY >= C || (nextX == x && nextY == y) || (nextY == 0 || nextY == C-1) && nextX < x) {
                index++;
                if (index == 4) {
                    arr[moveX][moveY] = 0;
                    break;  // 네 방향 모두 확인하면 종료
                }
                continue;
            }

            //값 이동하기
            if(moveX == x && moveY == y) {
            }else{
                arr[moveX][moveY] = arr[nextX][nextY];
            }
            moveX = nextX;
            moveY = nextY;

        }

    }
}
