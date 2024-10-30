import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+2];
        arr[0] = 0;
        arr[N+1] = L;
        if (N != 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
        }
        Arrays.sort(arr);

        int s = 1, e = L - 1;
        int cnt, ans = 0;
        while (s <= e) {
            cnt = 0;
            int mid = (s + e) / 2;
            for (int i = 1; i < N+2; i++) {
                if (arr[i] - arr[i-1] > mid) {
                    cnt += (arr[i] - arr[i-1] - 1) / mid;
                }
            }

            if (cnt > M) s = mid + 1;
            else {
                e = mid - 1;
                ans = mid;
            }
        }

        System.out.println(ans);
    }

}
