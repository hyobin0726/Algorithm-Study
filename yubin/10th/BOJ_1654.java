import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1654 {
    /**
     * 랜선 자르기
     * 풀이 방법: 이분 탐색
     *
     * 1. 인풋 케이스 받는다. (K, N, LAN[])
     * 2. result 0으로 초기화
     * 3. 이분 탐색 실행한다.
     *      3.1 first = 1, end = 배열에서 제일 큰 값;
     *      3.2 whlie(first <= end)인 동안
     *          3.3 long mid = (first + end) / 2;
     *          3.4 int sum = check(mid)
     *          3.5 sum 값이 N보다 크면 result = mid으로 바꿔주기
     *              3.5.1 first = mid + 1;
     *          3.6 sum 값이 N보다 작으면 end = mid -1;
     * 4. 몇개 만들수 있는 지 확인하는 함수(int check(long mid))
     *      4.1 int sum = 0;
     *      4.2 LAN 배열 반복문 돌면서 나누기하여 sum 값에 더해주기
     *      4.3 return sum
     */

    static BufferedReader br;
    static StringTokenizer st;

    static int K,N;
    static int[] LAN;
    static long result;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        //1. 인풋 케이스 받는다. (K, N, LAN[])
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        LAN = new int[K];
        for (int i = 0; i < K; i++) {
            LAN[i] = Integer.parseInt(br.readLine());
        }

        //2. result 0으로 초기화
        result = 0;

        //3. 이분 탐색 실행한다.
        binarySearch();

        System.out.println(result);
    }
    static void binarySearch(){
        //3.1 first = 1, end = 배열에서 제일 큰 값;
        long first = 1;
        long end = 0;
        for (int lan:
             LAN) {
            end = Math.max(end, lan);
        }

        //3.2 whlie(first <= end)인 동안
        while(first <= end){
            //3.3 long mid = (first + end) / 2;
            long mid = (first + end) / 2;

            //3.4 int sum = check(mid)
            long sum = check(mid);

            //3.5 sum 값이 N보다 크면 result = mid로 바꿔주기
            if(sum >= N){
                result =  mid;
                //3.5.1 first = mid + 1;
                first = (mid + 1);
            }else{
                //3.6 sum 값이 N보다 작으면 end = mid -1;
                end = (mid - 1);
            }
        }
    }

    //몇개 만들수 있는 지 확인하는 함수(int check(long mid))
    static long check(long mid){
        //4.1 int sum = 0;
        long sum = 0;
        //4.2 LAN 배열 반복문 돌면서 나누기하여 sum 값에 더해주기
        for (int lan :
                LAN)
            sum += (int) (lan / mid);

        return sum;
    }
}
