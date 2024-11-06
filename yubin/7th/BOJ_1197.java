import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1197{
    /**
     * 문제:  최소 스패닝 트리
     * 풀이 방법: 크루스칼 알고리즘 사용
     *
     * 1. 간선 정보로 Edge 클래스 만들기
     *      1.1 간선의 vertex와 weight 담는다.
     *      1.2 가중치 기준으로 정렬 필요 - Comparable 사용
     * 2. 유니온 파인드 함수 만들기
     *      2.1 같은 부모인지 확인가능한 find 함수
     *      2.2 같은 그룹으로 만들기 위해 부모를 합치는 union 함수
     *      2.3 부모 배열 만들기
     * 3. main 함수
     *      3.1 인풋과 Edge[]배열 값 넣어주기
     *      3.2 부모 배열 만들기
     *      3.3 정렬
     *      3.4 간선을 돌면서 union함수
     *          3.4.1 두 정점이 이미 같은 부모라면 사이클이 생기기때문에 패스
     *          3.4.2. 다른 부모면 해당 가중치 더하기
     *          3.4.3 간선이 (정점 - 1)개이면 최소 스패닝 트리 완성
     * 4. 가중치 합 출력
     */
    static int[] parents;
    static int V, E;

    static void make() {
        parents = new int[V + 1];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = -1;
        }
    }

    static int find(int a) {
        if(parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;

        parents[aRoot] += parents[bRoot];
        parents[bRoot] = aRoot;
        return true;

    }
    static class Edge implements Comparable<Edge>{
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            super();
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }


    }

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        Edge[] edges = new Edge[E];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i] = new Edge(Integer.parseInt(st.nextToken())
                    , Integer.parseInt(st.nextToken())
                    , Integer.parseInt(st.nextToken()));

        }

        make();

        Arrays.sort(edges);

        int cnt = 0;
        long cost = 0;

        for (Edge edge : edges) {
            if(union(edge.start, edge.end)) {
                cost += edge.weight;
                if(++cnt == V - 1) {
                    sb.append(cost);
                }
            }
        }

        System.out.println(sb);
    }
}

