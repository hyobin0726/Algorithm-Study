import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17135 {
    static int N;
    static int M;
    static int D;
    static int answer = 0;
    static int[][] map;
    static int[][] originMap;
    
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        originMap = new int[N+1][M+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                originMap[i][j] = map[i][j];
            }
        }
        
        int[] archer = new int[3];
        setArcher(1,0,archer);
        System.out.println(answer);
    }

    //조합
    public  static void setArcher(int start, int settingCount, int[] archer){
        if(settingCount == 3){
            //맵 되돌리기
            init();
            //공격
            attack(archer);
            return;
        }
        for (int i = start; i <= M; i++) {
            archer[settingCount] = i;
            setArcher(start+1, settingCount+1, archer);
        }
    }
    public static void init(){
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                map[i][j] = originMap[i][j];
            }
        }
    }
    
    public static int distance(int x1, int x2, int y1, int y2){
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }
    
    public static void attack(int[] archer){
        int count = 0;
        //매 턴마다, 최대 N번 진행
        for (int n = 1; n <= N ; n++) {
            //적 처지한 곳 위치 저장
            boolean[][] visited = new boolean[N+1][M+1];
            //궁수 반복
            for (int i = 0; i < 3; i++) {
                int archerX = archer[i];
                int minD = Integer.MAX_VALUE;
                int minX = Integer.MAX_VALUE;
                int minY = Integer.MAX_VALUE;

                //배열돌면서 적이 있는 곳일때 거리 구해서 최단 거리와, 그때 위치 업데이트
                for (int j = 1; j <= N; j++) {
                    for (int k = 1; k <= M; k++) {
                        if(map[j][k] == 1){
                            int distance = distance(k, archerX, j, N+1);
                            if(distance < minD){
                                minD = distance;
                                minX = k;
                                minY = j;
                            } else if (distance == minD) {
                                if(k < minX){
                                    minX = k;
                                    minY = j;
                                }
                            }
                        }
                    }
                }
                //구한 최단거리가 궁수가 처지할 수 있는 범위내라면
                if(minD <= D){
                    visited[minY][minX] = true;
                }
            }
            //처지가능한 곳 0으로 만들고 처지 count++
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    if(visited[i][j]){
                        map[i][j] = 0;
                        count++;
                    }
                }
            }
            //배열 한줄씩 내리기
            //제일 윗줄은 0행이 있기때문에 처리 안해도 됨
            for (int i = N; i >= 1; i--) {
                for (int j = 1; j <= M; j++) {
                    map[i][j] = map[i-1][j];
                }
            }
        }
        answer = Math.max(count, answer);
    }
}
