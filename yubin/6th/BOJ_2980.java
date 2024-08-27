import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2980 {
	/**
	 * 
	 * 1. 신호등 갯수만큼 반복을 돈다.
	 * 		1.1 신호등 위치에서 현재 위치를 시간으로 더해주고, 위치는 신호등 위치로 변경한다.
	 * 		1.2 신호등이 빨간 불일 경우, 남은 빨간불 신호등 시간을 더해준다.
	 * 2. 남은 위치까지 시간을 더해준다.
	 */
	
	public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
 
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
 
        int position = 0;
        int time = 0;
 
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            int D = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());
 
            time += D - position;
            position = D;
 
            int gap = time % (R + G);
            if (gap < R) time += R - gap;
        }
 
        time += L-position;
 
        System.out.println(time);
    }
}
