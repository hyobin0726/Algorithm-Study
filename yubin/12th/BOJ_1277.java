import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1277 {
    /**
     * 발전소
     * 문제 요약
     * 1 -> N번 가는 중간 전선 끊어져서 다시 연결시키기
     * 최소 전선 길이를 추가 사용하여 다싱 ㅕㄴ결시키기
     * 단, 전선의 길이 M 초과하면 안된다.
     *
     * 다익스트라
     */

    static BufferedReader br;
    static StringTokenizer st;

    static int N,W;
    static double M;
    static int[][] arr;
    static boolean[][] connected;


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  //발전소의 수
        W = Integer.parseInt(st.nextToken());  //남아있는 전선의 수

        M = Double.parseDouble(br.readLine());  //제한 길이

        //발전소
        arr = new int[N+1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        //연결 전선
        connected = new boolean[N+1][N+1];
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            connected[num1][num2] = true;
            connected[num2][num1] = true;
        }

        //1번 발전소에서 다른 발전소까지 연결하는데 필요한 추가 전선 길이의 최솟값
        double[] line = new double[N+1];
        Arrays.fill(line, Double.MAX_VALUE);
        line[1] = 0;

        //다익스트라
        PriorityQueue<double[]> priorityQueue = new PriorityQueue<>((o1,o2) -> o1[0] < o2[0] ? -1 : 0);
        priorityQueue.add(new double[] {0,1});

        double[] now;
        double len;
        while (!priorityQueue.isEmpty()){
            now = priorityQueue.poll();

            //이미 더 짧은 루트를 찾았거나, 현재 지점이 N 이라면 넘어감
            if(now[0] >  line[(int)now[1]] || (int) now[1] == N) continue;

            //현재 지점에서 다른 지점으로 더 짧은 루트로 갈 수 있는 경로를 찾는 경우
            for (int i = 1; i <= N ; i++) {
                if(connected[(int) now[1]][i]) len = now[0];
                else{
                    len = now[0] + Math.sqrt(Math.pow(arr[i][0] - arr[(int)now[1]][0],2) + Math.pow(arr[i][1] - arr[(int)now[1]][1],2));
                }
                if(line[i] > len){
                    line[i] = len;
                    priorityQueue.add(new double[] {len,i});
                }
            }
        }

        System.out.println((int) (line[N] * 1000));


    }
}
