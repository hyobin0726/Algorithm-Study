import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static int[][] map;
    static int aryp, aryattack ;
    static int bossp, boosattack ;
    static class ary{
        int x ,y,d;
        public ary(int d, int y, int x) {
            this.d = d;
            this.y = y;
            this.x = x;
        }
    }
    static class boss {
        int x,y,d;
        public boss(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
    //석순모음
    static Set<String> stones = new HashSet<>();
    static ary aryinfo;
    static boss bossinfo;
    //상우하좌
    static int[] dx = {-1,0,1,0};
    static int[] dy ={0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        aryinfo = new ary(0,0,0);
        bossinfo = new boss(0,0,0);

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                //아리
                if(map[i][j] == 2){
                    aryinfo.x=i;
                    aryinfo.y=j;
                    map[i][j] =0;
                }
                //보스
                if(map[i][j] == 3){
                    bossinfo.x=i;
                    bossinfo.y=j;
                    map[i][j] =0;
                }
                if (map[i][j] == 1) {
                    stones.add(i + "," + j);
                }
            }
        }
        if(aryinfo.x == bossinfo.x){
            if(aryinfo.y-bossinfo.y >0){
                aryinfo.d = 1;
                bossinfo.d=1;
            }else {
                aryinfo.d = 3;
                bossinfo.d=3;
            }
        }
        if(aryinfo.y == bossinfo.y){
            if(aryinfo.x-bossinfo.x >0){
                aryinfo.d = 2;
                bossinfo.d=2;
            }else {
                aryinfo.d = 0;
                bossinfo.d=0;
            }
        }

        st = new StringTokenizer(br.readLine());
        aryp = Integer.parseInt(st.nextToken());
        aryattack = Integer.parseInt(st.nextToken());
        bossp = Integer.parseInt(st.nextToken());
        boosattack =Integer.parseInt(st.nextToken());
//        System.out.println(aryinfo.x + " " + aryinfo.y + " " + aryinfo.d);
//        System.out.println(bossinfo.x + " " + bossinfo.y + " " + bossinfo.d);
        //게임 시작
        boolean isgame = true;
        while (isgame){
//            System.out.println("피" + aryp + " " + bossp);
            //종료조건 아리가 죽거나 보스가 죽거나
            //아리 공격
            bossp -= aryattack;
            if(bossp<=0){
                System.out.println("VICTORY!");
                break;
            }
            //아리 이동
            int lastX = aryinfo.x;
            int lastY = aryinfo.y;
            boolean moved = false;
            for (int i = 0; i < 4; i++) {
                int nx = aryinfo.x + dx[aryinfo.d];
                int ny = aryinfo.y + dy[aryinfo.d];

                if (0 <= nx && nx < n && 0 <= ny && ny < m && map[nx][ny] == 0 &&!(nx == bossinfo.x  && ny ==bossinfo.y )) {
                    aryinfo.x = nx;
                    aryinfo.y = ny;
                    moved = true;
                    break;
                } else {
                    aryp -= 1;
                    aryinfo.d = (aryinfo.d + 1) % 4;
                    if (aryp <= 0) {
                        System.out.println("CAVELIFE...");
                        return;
                    }
                }
            }

            if(aryp <= 0 ){
                System.out.println("CAVELIFE...");
                break;
            }
            //보스 공격
            if (!stones.isEmpty()) {
                if (bossAttack()) {
                    System.out.println("CAVELIFE...");
                    return;
                }
            }
            //보스 이동
            if (moved) {
                bossinfo.x = lastX;
                bossinfo.y = lastY;
                bossinfo.d = aryinfo.d;
            }
        }
    }
    static boolean bossAttack() {
        int temp_x = bossinfo.x, temp_y = bossinfo.y, temp_dir = bossinfo.d;
        int cnt = 1;

        while (true) {
            cnt++;
            int nx = dx[temp_dir], ny = dy[temp_dir];
            for (int i = 0; i < cnt / 2; i++) {
                temp_x += nx;
                temp_y += ny;
                if (stones.contains(temp_x + "," + temp_y)) {
                    return bossAttackFromStone(temp_x, temp_y);
                }
            }
            temp_dir = (temp_dir + 1) % 4;
            if (cnt >= n * m) break;
        }
        return false;
    }

    static boolean bossAttackFromStone(int stone_x, int stone_y) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        q.add(new int[]{stone_x, stone_y, boosattack});
        visited[stone_x][stone_y] = true;

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int x = temp[0];
            int y = temp[1];
            int tcnt = temp[2];

            if (x == aryinfo.x && y == aryinfo.y) {
                aryp -= tcnt;
                if (aryp <= 0) return true;
                break;
            }

            if (tcnt <= 0) continue;

            for (int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] != 1 && !visited[nx][ny] && !(nx == bossinfo.x && ny == bossinfo.y)) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny, tcnt - 1});
                }
            }
        }
        return false;
    }

}
