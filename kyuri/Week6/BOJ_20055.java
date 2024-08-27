import java.io.*;
import java.util.*;

/*
  단순 구현
  문제 자체를 이해하기 어려우나 그대로 구현하면 됨
  1) 벨트와 로봇을 오른쪽으로 한 칸 이동(회전) -> 내려지는 로봇이 있을 수 있으므로 확인
  2) 위쪽 벨트의 가장 오른쪽부터 로봇이 이동할 수 있다면 이동, 없다면 그대로 -> 내려지는 로봇이 있을 수 있으므로 확인
  3) 로봇 올리기
  4) 내구도가 0인 칸 수 확인 후 종료 or 반복
*/
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Integer> ro = new ArrayList<>();
        for (int i = 0; i < N; i++) ro.add(0);
        List<Integer> belt = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2*N; i++) {
            belt.add(Integer.parseInt(st.nextToken()));
        }

        int zero = 0, ans = 1;
        while (true) {
            Collections.rotate(belt, 1);
            Collections.rotate(ro, 1);
            ro.set(N-1, 0);

            for (int i = N-2; i >= 0; i--) {
                if (ro.get(i) == 1 && ro.get(i + 1) == 0 && belt.get(i + 1) > 0) {
                    belt.set(i + 1, belt.get(i + 1) - 1);
                    ro.set(i, 0);
                    ro.set(i + 1, 1);
                    if (belt.get(i+1) == 0) zero++;
                }
            }
            ro.set(N-1, 0);

            if (belt.get(0) > 0 && ro.get(0) == 0) {
                ro.set(0, 1);
                belt.set(0, belt.get(0) - 1);
                if (belt.get(0) == 0) zero++;
            }
            if (zero >= K) break;
            ans++;
        }

        System.out.println(ans);
    }
}
