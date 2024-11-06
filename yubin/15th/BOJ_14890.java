import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14890 {

    /**
     * BOJ 14890 G3 경사로
     * 퓰이: 구현
     *
     * 초반 풀이 생각한거
     * 1. 한 줄 돌면서,
     *  1.1 현재 칸과 앞 칸이 차이가  1이상 나면 그냥 break;
     *  1.2 현재 칸과 앞 칸의 차이가 -1이라면,
     *      1.2.1 앞 칸+1부터 L개 칸이 내 칸 -1 인지 확인, 아니면 중단 맞으면 현재 인덱스 + (L + 1), 경사로 체크하기, 경계 벗어나면 break
     *  1.3 현재 칸과 앞 칸의 차이가 +1이라면,
     *      1.3.1 현재칸부터 L개 뒤쪽 확인
     *          1.2.1.2 경계 벗어나면  break
     *          1.3.1.1 만약 현재칸과 같지 않은 칸이 잇다면 break
     *          1.3.1.2 경사로가 있는 칸이면 break
     *      1.3.2 경사로를 놓을 수 있으면
     *          1.3.2.1 경사로 칸 true 시키기
     *          1.3.2.2 인덱스+1시키기
     *  1.4 현재 칸과 앞 칸의 차이가 0이면 그냥 인덱스 + 1
     */
    static BufferedReader br;
    static StringTokenizer st;

    static int N,L;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int sum = 0;
        //가로
        for (int i = 0; i < N; i++) {
            int[] line = arr[i];
            if(isAvailable(line)) {
                sum++;
            }
        }
        //세로
        for (int i = 0; i < N; i++) {
            int[] line = new int[N];
            for (int j = 0; j < N; j++) {
                line[j] = arr[j][i];
            }
            if(isAvailable(line)) {
                sum++;
            }
        }

        System.out.println(sum);
    }

    static boolean isAvailable(int[] arr){
        boolean[] check = new boolean[N];

        int index = 0;
        while(index != N-1){
            //1.1 현재 칸과 앞 칸이 차이가  1이상 나면 그냥 break;
            if(Math.abs(arr[index] - arr[index + 1]) > 1) return false;
            //1.2 현재 칸과 앞 칸의 차이가 0이면 그냥 인덱스 + 1
            if(Math.abs(arr[index] - arr[index + 1]) == 0){
                index++;
                continue;
            }
            //1.3 현재 칸과 앞 칸의 차이가 +1이라면,
            if(arr[index] - arr[index + 1] == -1){
                //1.3.1 현재칸부터 L-1개 뒤쪽 확인
                for (int i = 0; i < L; i++) {
                    //1.3.1.1 경계 벗어나면  break
                    int next = index - i;
                    if(next < 0) return false;
                    //1.3.1.2만약 현재칸과 같지 않은 칸이 잇다면 break
                    if(arr[index] != arr[next]) return false;
                    //1.3.1.3 경사로가 있는 칸이면 break
                    if(check[next]) return false;
                }
                //1.3.2 경사로를 놓을 수 있는 경우다
                //1.3.2.1 경사로 칸 true 시키기
                for (int i = 0; i < L; i++) {
                    check[index - i] = true;
                }
                //1.3.2.2 인덱스+1시키기
                index++;
            }else if (arr[index] - arr[index + 1] == 1){
                //1.4 현재 칸과 앞 칸의 차이가 -1이라면,
                //1.4.1 현재칸+1부터 L-1개 앞쪽 확인
                for (int i = 0; i < L; i++) {
                    int next = index+1 + i;
                    //1.4.1.1 경계 벗어나면  break
                    if(next > N-1) return false;
                    //1.4.1.2만약 현재칸과 같지 않은 칸이 있다면 break
                    if(arr[index+1] != arr[next]) return false;
                    //1.4.1.3 경사로가 있는 칸이면 break
                    if(check[next]) return false;
                }
                //1.4.2 경사로를 놓을 수 있는 경우다
                //1.4.2.1 경사로 칸 true 시키기
                for (int i = 0; i < L; i++) {
                    check[(index+1) + i] = true;
                }
                index += L;
            }
        }
        return true;
    }

}
