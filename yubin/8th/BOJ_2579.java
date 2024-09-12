import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2579 {
    static BufferedReader br;
    static int N;
    static int[] arr;
    static int[] sum;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        sum = new int[N];
        if(N == 1){
            System.out.println(arr[0]);
        } else if (N == 2) {
            System.out.println(arr[0] + arr[1]);
        } else if (N == 3) {
            System.out.println(Math.max(arr[0], arr[1]) + arr[2]);   
        }else {
            sum[0] = arr[0];
            sum[1] = arr[0] + arr[1];
            sum[2] = Math.max(arr[0], arr[1]) + arr[2];

            for (int i = 3; i < N; i++) {
                sum[i] = Math.max(sum[i - 2], (sum[i - 3] + arr[i - 1])) + arr[i];
            }

            System.out.println(sum[N - 1]);
        }
    }
}
