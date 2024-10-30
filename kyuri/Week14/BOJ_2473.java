import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        long minVal = Long.MAX_VALUE;
        long[] ans = new long[3];
        Outer:
        for (int i = 0; i < N - 2; i++) {
            int first = i;
            int second = i + 1;
            int third = N - 1;

            while (second < third) {
                long sum = (long) arr[first] + arr[second] + arr[third];
                long absSum = Math.abs(sum);
                if (absSum < minVal) {
                    minVal = absSum;
                    ans = new long[]{arr[first], arr[second], arr[third]};
                }

                if (sum < 0) second++;
                else if (sum > 0) third--;
                else break Outer;
            }
        }

        for (long val : ans) {
            System.out.print(val + " ");
        }
    }

}
