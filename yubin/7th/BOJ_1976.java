import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1976 {
    /**
     * 유니온 파인드 사용해서 같은 부모인지 체크
     */
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;

    static int N;  //도시의 수
    static int M;  //여행 계획에 속한 도시들의 수
    static int parent[];

    static void make(){
        parent = new int[N + 1];
        Arrays.fill(parent, -1);
    }
    static int find(int a){
        if(parent[a] < 0) return a;
        return parent[a] = find(parent[a]);
    }
    static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;
        parent[aRoot] += parent[bRoot];
        parent[bRoot] = aRoot;
        return true;
    }
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        make();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if(num == 1){
                    union(i + 1, j + 1);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int root = find(Integer.parseInt(st.nextToken()));
        boolean result = true;
        for (int i = 1; i < M; i++) {
            if(root == find(Integer.parseInt(st.nextToken()))) continue;
            result = false;
            break;
        }

        sb.append(result ? "YES" : "NO");
        System.out.println(sb);
    }
}
