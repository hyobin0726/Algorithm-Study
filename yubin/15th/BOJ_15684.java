import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15684 {
    /**
     * BOJ 15684 G3 사다리 조작
     * 핵심 풀이: 구현 + DFS
     *
     * 초반 생각
     * 1. 사다리 배열 만들기
     * 2. 사다리 놓을 때 주의점: 두 가로선이 연속하거나 서로 접하면 안된다.
     * 3. DFS 사다리갯수 1,2,3 일때로 가능한지 보기 만약 작은 값이 바로 나오면 즉시 종료
     *  3.1 기저 조건: 사다리갯수만큼 추가하고 자기 자신 결과나오는지 확인 후 변경
     *  3.2 재귀: 놓은적 없고, 주변에 사다리 없으면 놓기
     */

    static BufferedReader br;
    static StringTokenizer st;
    static int N,M,H;
    static boolean[][] ladder;
    static int min;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  //세로선 개수
        M = Integer.parseInt(st.nextToken());  //가로선 개수
        H = Integer.parseInt(st.nextToken());  //세로선마다 가로선을 놓을 수 있는 위치의 개수

        ladder = new boolean[H][N-1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            //b번 세로선과 b+1번 세로선을 a번 점선 위치에서 연결
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a-1][b-1] = true;
        }

//        System.out.println(Arrays.deepToString(ladder));
        //check

        min = Integer.MAX_VALUE;
        for (int i = 0; i <= 3; i++) {
            DFS(ladder, i, 0);
            if(min != Integer.MAX_VALUE){
                System.out.println(min);
                return;
            }
        }

        if(min == Integer.MAX_VALUE){
            System.out.println(-1);
        }
    }

    static void DFS(boolean[][] ladder, int count, int depth){
        //기저 조건: 사다리갯수만큼 추가하고 자기 자신 결과나오는지 확인 후 변경
        if(depth == count){
            //사다리 결과가 맞는 경우
            if(checkLadders()){
                min = Integer.min(min, depth);
            }
            return;
        }

        //재귀: 놓은적 없고, 주변에 사다리 없으면 놓기
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N-1; j++) {
                //사다리 놓을 수 있는지 확인
                if(isAvailable(i,j)){
                    ladder[i][j] = true;
                    DFS(ladder, count, depth+1);
                    ladder[i][j] = false;
                }
            }
        }
    }

    static boolean checkLadders(){
        //세로선마다
        for (int i = 0; i < N; i++) {
            int location = i;
            //가로 내려가는데
            for (int j = 0; j < H; j++) {
                //사다리가 왼쪽에 있는 경우, 왼쪽으로 이동
                if(location > 0 && ladder[j][location-1]) location--;
                //사다리가 오른쪽에 있는 경우, 오른쪽으로 이동
                else if(location < N-1 && ladder[j][location]) location++;
                //사다리가 없는 경우는 그냥 넘어간다
            }

            //자신의 번호로 도착하지 않는 경우 즉시 종료
            if(location != i) return false;
        }
        //싹 다 자신의 번호로 도착하는 경우이므로 true
        return true;
    }

    static boolean isAvailable(int i, int j){
        //사다리가 있으면 안된다.
        if(ladder[i][j]) return false;
        //두 가로선이 연속하거나 서로 접하면 안된다.
        //왼쪽
        if(j > 0 && ladder[i][j-1]) return false;
        //오른쪽
        if(j < N-2 && ladder[i][j+1]) return false;

        //되는 경우
        return true;
    }
}
