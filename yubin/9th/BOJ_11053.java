import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11053 {
    static BufferedReader br;
    static StringTokenizer st;

    static int N;
    static int[] length;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        length = new int[N];

        for (int i = 0; i < N; i++) {
            length[i] = 1;
            for (int j = 0; j < i; j++) {
                if(arr[j] < arr[i]){
                    length[i] = Math.max(length[i], length[j]+1);
                }
            }
        }
        
        int result = 0;
        for (int i = 0; i < N; i++) {
            result = Math.max(result, length[i]);
        }

        System.out.println(result);
    }
}
