import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class BOJ_14499 {
    /**
     * BOJ 14499 G4 주사위 굴리기
     */

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static class Dice{
        int front;
        int left;
        int right;
        int top;
        int bottom1;
        int bottom2;  //바닥에 닿는 면

        public Dice(int front, int left, int right, int top, int bottom1, int bottom2) {
            this.front = front;
            this.left = left;
            this.right = right;
            this.top = top;
            this.bottom1 = bottom1;
            this.bottom2 = bottom2;
        }

        public void south(){
            int topTmp = top;
            int frontTmp = front;
            int bottom1Tmp = bottom1;
            int bottom2Tmp = bottom2;

            top = bottom2Tmp;
            front = topTmp;
            bottom1 = frontTmp;
            bottom2 = bottom1Tmp;
        }

        public void north(){
            int topTmp = top;
            int frontTmp = front;
            int bottom1Tmp = bottom1;
            int bottom2Tmp = bottom2;

            top = frontTmp;
            front = bottom1Tmp;
            bottom1 = bottom2Tmp;
            bottom2 = topTmp;
        }

        public void west(){
            int topTmp = top;
            int frontTmp = front;
            int bottom1Tmp = bottom1;
            int bottom2Tmp = bottom2;
            int leftTmp = left;
            int rightTmp = right;

            left = frontTmp;
            front = rightTmp;
            right = bottom2Tmp;
            bottom2 = leftTmp;
        }

        public void east(){
            int frontTmp = front;
            int bottom2Tmp = bottom2;
            int leftTmp = left;
            int rightTmp = right;

            left = bottom2Tmp;
            front = leftTmp;
            right = frontTmp;
            bottom2 = rightTmp;
        }
    }

    static Dice dice = new Dice(0,0,0,0,0,0);
    static int N,M,x,y,K;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        st = new StringTokenizer(br.readLine());
        int count = st.countTokens();
        for (int i = 0; i < count; i++) {
            turn(Integer.parseInt(st.nextToken()));
        }

        System.out.println(sb);
    }

    static void turn(int num){
        switch (num){
            case 1: //동쪽
                int nextX = x;
                int nextY = y + 1;
                //옆으로 갈 수 있는 경우
                if(check(nextX, nextY)) {
                    //이동
                    dice.east();
                    //밑에 숫자가 0인 경우
                    if (arr[nextX][nextY] == 0) {
                        arr[nextX][nextY] = dice.bottom2;
                    } else {
                        //밑에 숫자가 0이 아닌 경우
                        dice.bottom2 = arr[nextX][nextY];
                        arr[nextX][nextY] = 0;
                    }
                    x = nextX;
                    y = nextY;
                    sb.append(dice.front).append("\n");
                }
                break;
            case 2:  //서쪽
                nextX = x;
                nextY = y - 1;
                //옆으로 갈 수 있는 경우
                if(check(nextX, nextY)) {
                    //이동
                    dice.west();
                    //밑에 숫자가 0인 경우
                    if (arr[nextX][nextY] == 0) {
                        arr[nextX][nextY] = dice.bottom2;
                    } else {
                        //밑에 숫자가 0이 아닌 경우
                        dice.bottom2 = arr[nextX][nextY];
                        arr[nextX][nextY] = 0;
                    }
                    x = nextX;
                    y = nextY;
                    sb.append(dice.front).append("\n");
                }
                break;
            case 3:  //북쪽
                nextX = x - 1;
                nextY = y;
                //옆으로 갈 수 있는 경우
                if(check(nextX, nextY)) {
                    //이동
                    dice.north();
                    //밑에 숫자가 0인 경우
                    if (arr[nextX][nextY] == 0) {
                        arr[nextX][nextY] = dice.bottom2;
                    } else {
                        //밑에 숫자가 0이 아닌 경우
                        dice.bottom2 = arr[nextX][nextY];
                        arr[nextX][nextY] = 0;
                    }
                    x = nextX;
                    y = nextY;
                    sb.append(dice.front).append("\n");
                }
                break;
            case 4:  //남쪽
                nextX = x + 1;
                nextY = y;
                //옆으로 갈 수 있는 경우
                if(check(nextX, nextY)) {
                    //이동
                    dice.south();
                    //밑에 숫자가 0인 경우
                    if (arr[nextX][nextY] == 0) {
                        arr[nextX][nextY] = dice.bottom2;
                    } else {
                        //밑에 숫자가 0이 아닌 경우
                        dice.bottom2 = arr[nextX][nextY];
                        arr[nextX][nextY] = 0;
                    }
                    x = nextX;
                    y = nextY;
                    sb.append(dice.front).append("\n");
                }
                break;
        }
    }
    static boolean check(int x, int y){
        if(x < 0 || y < 0 || x >= N || y >= M) return false;
        return true;
    }
}
