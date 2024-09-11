import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    //북남서동
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    //90도 회전으로 할 수 있는 경우의 수
    static int[][][] mode = {
            {{0}},
            {{0},{1},{2},{3}},   //cctv1
            {{2,3},{0,1}},  //cctv2
            {{0,3},{1,3},{1,2},{0,2}},  //cctv3
            {{0,2,3},{0,1,3},{1,2,3},{0,1,2}},   //cctv4
            {{0,1,2,3}}  //cctv5
    };

    static int N,M,answer;
    static int[][] map;
    static int zeroCnt = 0;

    static ArrayList<CCTV> list = new ArrayList<>();

    static class CCTV{
        int r, c, num;

        public CCTV(int r, int c, int num){
            this.r = r;
            this.c = c;
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 0) zeroCnt++;
                else if (map[i][j] >= 1 && map[i][j] <= 5) {
                    list.add(new CCTV(i,j,map[i][j]));
                }
            }
        }

        answer = zeroCnt;
        dfs(0, map);
        System.out.println(answer);
    }

    //각 cctv마다 특정 경우에 조합으로 감시했을 때 사각지대가 얼마인지 구하는 메소드
    static void dfs(int depth, int[][] map){
        //모든 cctv를 다 확인해봤다면, 사각지대의 최소값을 갱신
        if(depth == list.size()){
            answer = Math.min(answer, check(map));
            return;
        }

        CCTV now = list.get(depth);
        int num = now.num;
        int r = now.r;
        int c = now.c;

        //각 cctv가 감시할 수 있는 방향을 모두 검사
        for (int i = 0; i < mode[num].length; i++) {
            //배열 복사하기
            //원본 맵을 수정하지 않고 감시 영역을 체크하기 위해서 사용
            int[][] copy = new int[N][M];
            for (int a = 0; a < N; a++) {
                for (int b = 0; b < M; b++) {
                    copy[a][b] = map[a][b];
                }
            }

            for (int j = 0; j < mode[num][i].length; j++) {
                int dir = mode[num][i][j];

                int nr = r + dr[dir];
                int nc = c + dc[dir];

                //격자 범위 벗어나거나, 벽(6)에 닿기 전까지 현재 방향으로 계속 이동
                while(true){
                    if(nr < 0 || nr >= N || nc < 0 || nc >= M) break;
                    if(map[nr][nc] == 6) break;

                    copy[nr][nc] = -1;
                    nr += dr[dir];
                    nc += dc[dir];
                }

            }
            dfs(depth+1, copy);
        }
    }
    static int check(int[][] map){
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 0) cnt++;
            }
        }

        return cnt;
    }
}
