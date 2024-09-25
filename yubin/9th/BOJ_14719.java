import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14719 {
    /**
     * 1. 입력받기(H,W, arr)
     * 2. 열마다,
     *      2.1 해당열 기준으로 왼쪽, 오른쪽 최대높이 찾는다
     *      2.2 왼쪽, 오른쪽 최대높이가 해당 열보다 크다면,
     *          둘 중에 더 작은 최대높이 - 해당열 높이계산하면 그 열의 빗물 크기
     */
    static BufferedReader br;
    static StringTokenizer st;

    static int H,W;
    static int[] arr;
    static int result;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
//        1. 입력받기(H,W, arr)
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        arr = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        result = 0;
        //2. 열마다,
        for (int i = 0; i < W; i++) {
            //2.1 해당열 기준으로 왼쪽, 오른쪽 최대높이 찾는다
            int left = Integer.MIN_VALUE;
            int right = Integer.MIN_VALUE;

            for (int j = 0; j < i; j++) {
                left = Math.max(left, arr[j]);
            }
            for (int j = i+1; j < W; j++) {
                right = Math.max(right, arr[j]);
            }

            //2.2 왼쪽, 오른쪽 최대높이가 해당 열보다 크다면,
            //둘 중에 더 작은 최대높이 - 해당열 높이계산하면 그 열의 빗물 크기
            if(left > arr[i] && right > arr[i]){
                result += Math.min(left, right) - arr[i];
            }
        }
        System.out.println(result);
    }
}
