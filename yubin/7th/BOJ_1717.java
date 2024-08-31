import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1717 {
    /**
     * 1. 간선 정보로 Edge 클래스 만들기
     *      1.1 간선의 vertex와 weight 담는다.
     *      1.2 가중치 기준으로 정렬 필요 - Comparable 사용
     * 2. 유니온 파인드 함수 만들기
     *      2.1 같은 부모인지 확인가능한 find 함수
     *      2.2 같은 그룹으로 만들기 위해 부모를 합치는 union 함수
     *      2.3 부모 배열 만들기
     * 3. main 함수
     *          3.1 두 정점이 같은 부모라면 YES
     *          3.2 다른 부모라면 NO
     */
    static int[] unf;
    public static int Find(int v) {
        if(v == unf[v]) return v;
        return unf[v] = Find(unf[v]);
    }
    public static void union(int a, int b) {
        int fa = Find(a);
        int fb = Find(b);
        if(fa != fb) {
            if( fa < fb ) {
                unf[fb] = fa;
            }else {
                unf[fa] = fb;
            }
        }
    }

    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;

    static int TC;
    static int N;
    static int M;

    public static void main(String[] args) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();


        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        unf = new int[N+1];
        for (int i = 1; i <= N; i++) {
            unf[i] = i;
        }
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int check = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(check == 0) {
                union(a,b);
            }else {
                int fa = Find(a);
                int fb = Find(b);
                if(fa == fb) sb.append("YES\n");
                else sb.append("NO\n");
            }
        }
        System.out.print(sb.toString());
    }
}

