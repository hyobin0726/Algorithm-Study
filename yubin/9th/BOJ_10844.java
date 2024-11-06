import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10844 {
    static BufferedReader br;
    static long[][] arr;
    static int N;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new long[N+1][10];

        arr[1][0] = 0;
        for (int i = 1; i < 10; i++) {
            arr[1][i] = 1;
        }

        for (int i = 2; i < N+1; i++) {
            for (int j = 0; j < 10; j++) {
                if(j == 0){
                    arr[i][j] = arr[i-1][1];
                } else if (j == 9) {
                    arr[i][j] = arr[i-1][8];
                }else {
                    arr[i][j] = (arr[i-1][j-1] + arr[i-1][j+1]) % 1000000000;
                }
            }
        }

        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += arr[N][i];
        }

        System.out.println(sum % 1000000000);

    }
}
