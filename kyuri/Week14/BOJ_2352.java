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
            arr[Integer.parseInt(st.nextToken()) - 1] = i;
        }

        int[] LIS = new int[N];
        LIS[0] = arr[0];
        int ans = 1;
        for (int i = 1; i < N; i++) {
            int key = arr[i];
            if (LIS[ans - 1] < key) {
                LIS[ans] = key;
                ans++;
            } else {
                // Lovwer bound
                int l = 0, r = ans - 1;
                while (l <= r) {
                    int mid = (l + r) / 2;
                    if (LIS[mid] < key) {
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                LIS[l] = key;
            }
        }

        System.out.println(ans);
    }

}
