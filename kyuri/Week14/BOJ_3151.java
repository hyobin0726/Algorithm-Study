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

        long ans = 0;
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                int lower = search(0, j+1, arr[i] + arr[j]);
                int upper = search(1, j+1, arr[i] + arr[j]);
                ans += upper - lower;
            }
        }

        System.out.println(ans);
    }

    public static int search(int type, int s, int target) {
        int e = N - 1, mid;
        int min_idx = N;
        while (s <= e) {
            mid = (s + e) / 2;
            if (type == 0 && arr[mid] + target >= 0) {
                e = mid - 1;
                min_idx = Math.min(min_idx, mid);
            } else if (type == 1 && arr[mid] + target > 0) {
                e = mid - 1;
                min_idx = Math.min(min_idx, mid);
            } else {
                s = mid + 1;
            }
        }
        return min_idx;
    }

}
