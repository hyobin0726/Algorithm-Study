import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1920 {
    /**
     * 수 찾기
     * 풀이 방법: 이분 탐색
     * <p>
     * 1. 입력 케이스 받는다.(N, arr[], M, test[])
     * 2. arr 정렬한다.
     * 3. case 돌면서, 이분탐색 실행한다.
     * 4. 이분탐색
     * 4.1 first = 0, end = N-1
     * 4.2 while(first <= end)동안
     * 4.2.1 중간값 찾는다.
     * 4.2.2 중간값이 해당 수와 같으면 return 1
     * 4.2.3 해당 값이 중간값보다 크면, first = mid+1;
     * 4.2.4 해당 값이 중간값보다 작으면, end = mid-1;
     * 4.3 while문을 빠져나온다면 값을 못찾았으므로 return 0
     */

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N, M;
    static int[] arr;
    static int[] test;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
//      //1. 입력 케이스 받는다.(N, arr[], M, test[])
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        test = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            test[i] = Integer.parseInt(st.nextToken());
        }

        //2. arr 정렬한다.
        Arrays.sort(arr);

        //3. case 돌면서, 이분탐색 실행한다.
        for (int i = 0; i < M; i++) {
            binarySearch(test[i]);
        }

        System.out.println(sb);
    }

    //4. 이분탐색
    static void binarySearch(int testNum) {
        //4.1 first = 0, end = N-1
        int first = 0;
        int end = N - 1;
        //4.2 while(first <= end)동안
        while (first <= end) {
            //4.2.1 중간값 찾는다.
            int mid = (first + end) / 2;
            //4.2.2 중간값이 해당 수와 같으면 1 출력
            if (arr[mid] == testNum) {
                sb.append("1").append("\n");
                return;
            }
            //4.2.3 해당 값이 중간값보다 크면, first = mid+1;
            if (testNum > arr[mid]) {
                first = mid + 1;
            } else {
                //4.2.4 해당 값이 중간값보다 작으면, end = mid-1;
                end = mid - 1;
            }
        }
        //4.3 while문을 빠져나온다면 값을 못찾았으므로 0 출력
        sb.append("0").append("\n");
    }
}
