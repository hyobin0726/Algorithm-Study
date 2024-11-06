import java.io.*;
import java.util.*;

/*
  키 순서대로 서 있으므로, 인접한 원생들의 키 차이를 diff 배열에 저장
  K개의 조를 뽑으므로 diff 중 K-1 개를 없애는 것과 동일
  최소값이 되어야 하므로 diff 배열을 오름차순으로 정렬한 후 뒤에서 K-1개를 제외한 값들을 더함
*/
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] li = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            li[i] = Integer.parseInt(st.nextToken());
        }

        int[] diff = new int[N-1];
        for (int i = 0; i < N-1; i++) diff[i] = li[i+1] - li[i];

        int ans = 0;
        Arrays.sort(diff);
        for (int i = 0; i < N-K; i++) ans += diff[i];

        System.out.println(ans);
    }
}
