import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

    public class BOJ_1912 {
        static BufferedReader br;
        static StringTokenizer st;
        static int N;
        static int[] arr;

        public static void main(String[] args) throws IOException {
            br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int currentSum = arr[0];
            int maxSum = arr[0];

            for (int i = 1; i < N; i++) {
                currentSum = Math.max(arr[i], currentSum + arr[i]);
                maxSum = Math.max(currentSum, maxSum);
            }

            System.out.println(maxSum);
        }
    }
