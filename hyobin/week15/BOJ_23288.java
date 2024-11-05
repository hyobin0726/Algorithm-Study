import java.util.*;
import java.io.*;

public class Main {
    static int n,m,k;
    static int[][] map;
    static int[] dice;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static int d;
    static int nx;
    static int ny;
    static int ans;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n= Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //초기 주사위
        dice = new int[7];
        for(int i=0; i<7; i++){
            dice[i] = i;
        }
        //초기 좌표, 방향 정보
        nx= 0;
        ny=0;
        d=0; // 동
        ans = 0;
        for(int i=0; i<k; i++) {
            game();
        }
        System.out.println(ans);

    }
    static void game(){
        //게임 시작
        //주사위가 이동방향에 맞춰 굴러간다. 주사위 배열바꾸기 -> 벽에 부딫히는 경우 예외 발생
        nx += dx[d];
        ny += dy[d];
        if(0> nx || nx>= n || 0> ny || ny>=m){
            if(d == 0){
                d=2;
                nx += dx[d]*2;
                ny += dy[d]*2;
            }
            else if(d==1){
                d = 3;
                nx += dx[d]*2;
                ny += dy[d]*2;
            }else if(d==2){
                d=0;
                nx += dx[d]*2;
                ny += dy[d]*2;
            }
            else if(d==3){
                d=1;
                nx += dx[d]*2;
                ny += dy[d]*2;
            }
        }

        //동일 경우
        if(d ==0) {
            int temp = dice[1];
            dice[1] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[3];
            dice[3] = temp;

        }else if(d==1){
            int temp = dice[1];
            dice[1] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[5];
            dice[5] = temp;
        }
        else if(d==2){
            int temp = dice[1];
            dice[1] = dice[3];
            dice[3] = dice[6];
            dice[6] = dice[4];
            dice[4] = temp;
        }
        else if(d==3){
            int temp = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[2];
            dice[2] = temp;
        }
        //map의 수를 bfs에 넣어준다.
        int t= bfs(nx,ny);
        //점수 계산을 한다.
        int score = t * map[nx][ny];

        //map의 수와 dice[6]의 수에 따라 방향이 결정된다.
        if( dice[6]> map[nx][ny]  ){
            d = (d+1)%4;
        }else if( dice[6] < map[nx][ny]){
            if(d == 0){
                d=3;
            }else {
                d-=1;
            }
        }
        ans += score;
//        System.out.println(score);
//        System.out.println(d);
//        System.out.println(Arrays.toString(dice));

    }
    static int bfs(int nx,int ny){
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        q.add(new int[] {nx,ny});
        visited[nx][ny] = true;
        int tnum = map[nx][ny];
        int cnt =1;
        while (!q.isEmpty()){
            int[] temp = q.poll();

            int tx = temp[0];
            int ty = temp[1];

            for(int i=0; i<4; i++){
                int nnx = tx + dx[i];
                int nny = ty + dy[i];

                if(0<= nnx && nnx<n && 0<= nny && nny<m && !visited[nnx][nny]){
                    if(map[nnx][nny] == tnum){
                        visited[nnx][nny] = true;
                        cnt++;
                        q.add(new int[] {nnx,nny});
                    }
                }
            }

        }
        return cnt;

    }
}
