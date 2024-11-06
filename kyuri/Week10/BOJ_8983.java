import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] arr = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int[][] animals = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            animals[i][0] = Integer.parseInt(st.nextToken());
            animals[i][1] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        for (int[] animal : animals) {
            int a = animal[0], b = animal[1];
            int left = 0, right = M-1;
            int max = a - b + L, min = a + b - L;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (min <= arr[mid] && arr[mid] <= max) {
                    ans++;
                    break;
                } else if (arr[mid] < min) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        System.out.println(ans);
    }
}
