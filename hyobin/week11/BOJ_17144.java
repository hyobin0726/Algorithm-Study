import java.util.*;
import java.io.*;
public class Main {
    static int r,c,t;
    static int[][] map;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static List<int[]> clean;
    static List<int[]> dust;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c= Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        map = new int[r][c];
        //공기청정기 좌표 찾기
        clean = new ArrayList<>();
        for(int i=0; i<r; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<c; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1){
                    clean.add(new int[] {i,j});
                }
            }
        }

        //t초 동안 일어남
        for(int tc =0; tc<t; tc++){
            dust = new ArrayList<>();
            //미세먼지 확산
            for(int i=0; i<r; i++){
                for(int j=0; j<c; j++){
                    if(map[i][j] >0){
                        dust.add(new int[] {i,j});
                    }
                }
            }
            spread();
            //공기청정기
            turnon();

        }
        int result =0;
        //0 이상인 값 합산
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(map[i][j] > 0) {
                    result += map[i][j];
                }
            }
        }
        System.out.println(result);

    }
    static void spread(){

        int[][] tempMap = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                tempMap[i][j] = map[i][j];
            }
        }
        //0보다 크면 상하좌우로 확산시작(현재크기/5)만큼 + , 확산가능하다면 cnt++
        //벽을 벗어나거나 -1이면 안됨

        for(int d = 0; d< dust.size(); d++){
            int x = dust.get(d)[0];
            int y = dust.get(d)[1];
            int cnt =0;
            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(0<= nx && nx<r && 0<= ny && ny<c){
                    if(map[nx][ny] != -1){
                        tempMap[nx][ny] += map[x][y]/5;
                        cnt++;
                    }
                }
            }
            //현재크기 = (현재크기-확산된양*확산된 방향 수cnt)
            tempMap[x][y]-= map[x][y]/5*cnt;
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] = tempMap[i][j];
            }
        }

    }

    static void turnon(){
        //첫번째 공기청정기 - 반시계
        int x1= clean.get(0)[0];
        int y1= clean.get(0)[1];
        // 아래쪽 방향
        for (int i = x1 - 1; i > 0; i--) {
            map[i][y1] = map[i - 1][y1];
        }
        // 왼쪽 방향
        for (int i = 0; i < c - 1; i++) {
            map[0][i] = map[0][i + 1];
        }
        // 위쪽 방향
        for (int i = 0; i < x1; i++) {
            map[i][c - 1] = map[i + 1][c - 1];
        }
        // 오른쪽 방향
        for (int i = c - 1; i > 1; i--) {
            map[x1][i] = map[x1][i - 1];
        }

        //나오는 부분은 0으로 청정공기
        map[x1][1] = 0;

        // 두 번째 공기청정기 - 시계 방향
        int x2 = clean.get(1)[0];
        int y2 = clean.get(1)[1];

        // 위쪽 방향
        for (int i = x2 + 1; i < r - 1; i++) {
            map[i][y2] = map[i + 1][y2];
        }
        // 왼쪽 방향
        for (int i = 0; i < c - 1; i++) {
            map[r - 1][i] = map[r - 1][i + 1];
        }
        // 아래쪽 방향
        for (int i = r - 1; i > x2; i--) {
            map[i][c - 1] = map[i - 1][c - 1];
        }
        // 오른쪽 방향
        for (int i = c - 1; i > 1; i--) {
            map[x2][i] = map[x2][i - 1];
        }
        //나오는 부분은 0으로 청정공기
        map[x2][1] = 0;

    }

}
