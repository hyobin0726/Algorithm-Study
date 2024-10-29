import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] sum_arr = new int[N*N];
        int i = 0;
        for (int a : arr) {
            for (int b : arr) {
                sum_arr[i++] = a + b;
            }
        }
        Arrays.sort(sum_arr);

        int ans = -1;
        for (int k = 0; k < N; k++) {
            for (int c = 0; c < N; c++) {
                int target = arr[k] - arr[c];
                if (Arrays.binarySearch(sum_arr, target) > -1) {
                    ans = Math.max(ans, arr[k]);
                }
            }
        }

        System.out.println(ans);
    }
}
