import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_17073 {
    /*
    요점은 0보다 큰 정점들은 각 마지막 노드인 리프노드들이다.
    1번 노드의 물의 양 / 리프노드 갯수 해주면 평균이 나온다.
     */
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static List<Integer>[] arrayLists;
    static int N;
    static int W;
    static int leafs;
    public static void main(String[] args) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        leafs = 0;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        arrayLists = new ArrayList[N+1];
        for (int i = 1; i < N+1; i++) {
            arrayLists[i] = new ArrayList<>();
        }
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int tmp1 = Integer.parseInt(st.nextToken());
            int tmp2 = Integer.parseInt(st.nextToken());

            arrayLists[tmp1].add(tmp2);
            arrayLists[tmp2].add(tmp1);
        }

        for (int i = 2; i <= N; i++) {
            if(arrayLists[i].size() == 1) leafs++;
        }

        System.out.printf("%.10f%n", (double)W/leafs);
    }
}

