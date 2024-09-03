import java.io.*;
import java.util.*;
public class BOJ_2980 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int time = 0;
        int temp = 0;

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            //신호등 간 시간
            time += d - temp;
            //현재 위치
            temp = d;

            int cycle = r + g;
            // 현재시간를 주기로 나눈 나머지가 빨간불이면
            if (time % cycle < r) {
                time += r - time % cycle;
            }
        }
        //마지막 신호등에서 도착지까지 걸리는 시간
        time += l - temp;

        System.out.println(time);
    }
}
