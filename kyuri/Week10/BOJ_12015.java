import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] LIS = new int[N];
        LIS[0] = arr[0];
        int ans = 1;
        for (int i = 1; i < N; i++) {
            int target = arr[i];

            if (LIS[ans-1] < target) {
                ans++;
                LIS[ans-1] = target;
            } else {
                int left = 0, right = ans;
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (LIS[mid] < target) left = mid + 1;
                    else right = mid;
                }
                LIS[left] = target;
            }
        }

        System.out.println(ans);
    }
}
