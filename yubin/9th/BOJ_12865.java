import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12865 {
    static BufferedReader br;
    static StringTokenizer st;
    static int N,K;
    static product[] products;
    static int[][] dp;
    static class product{
        int weight;
        int value;

        public product(int weight, int value){
            this.weight = weight;
            this.value = value;
        }
    }


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   //물품의 수
        K = Integer.parseInt(st.nextToken());   //버틸 수 있는 무게

        products = new product[N+1];
        products[0] = new product(0,0);
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            products[i] = new product(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        dp = new int[N+1][K+1];

        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < K+1; j++) {
                if(j - products[i].weight >= 0){
                    dp[i][j] = Math.max(dp[i-1][j], products[i].value + dp[i-1][j - products[i].weight]);
                }else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        System.out.println(dp[N][K]);
    }
}
