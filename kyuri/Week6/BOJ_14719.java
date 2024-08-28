import java.io.*;
import java.util.*;

/*
  처음과 끝은 빗물이 차지 않으므로 제외하고 인덱스 1부터 확인
  현재 인덱스에서 왼쪽에서 가장 큰 값과, 오른쪽에서 가장 큰 값을 찾아서 왼쪽과 오른쪽 중 작은 값으로
  현재 인덱스의 높이와 차이를 계산
*/
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] li = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            li[i] = Integer.parseInt(st.nextToken());
        }

        int l_idx = 0, r_idx = 0, ans = 0;
        if (W > 2) {
            for (int i = 1; i < W - 1; i++) {
                if (r_idx <= i) {
                    r_idx = i + 1;
                    for (int j = r_idx; j < W; j++) {
                        if (li[r_idx] < li[j]) r_idx = j;
                    }
                }
                if (li[l_idx] < li[i-1]) l_idx = i - 1;

                int dif = Math.min(li[l_idx], li[r_idx]) - li[i];
                ans += Math.max(dif, 0);
            }
        }

        System.out.println(ans);
    }
}
