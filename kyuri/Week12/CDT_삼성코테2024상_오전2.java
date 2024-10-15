import java.io.*;
import java.util.*;
public class Main {
    final static int INF = 0x7fffffff;
    final static int MAX_N = 2000;
    final static int MAX_ID = 30005;
    static int N, M, S;
    static int[][] graph = new int[MAX_N][MAX_N]; // 간선 인접 행렬
    static int[] Dist = new int[MAX_N];
    static boolean[] isMade = new boolean[MAX_ID], isCancel = new boolean[MAX_ID];
    static PriorityQueue<Package> pq = new PriorityQueue<>(); // 최적의 여행 상품

    // 여행 상품
    static class Package implements Comparable<Package> {
        int id, revenue, dest, profit;

        public Package(int id, int revenue, int dest, int profit) {
            this.id = id;
            this.revenue = revenue;
            this.dest = dest;
            this.profit = profit;
        }

        // pq 위한 정렬 기준
        @Override
        public int compareTo(Package other) {
            if (this.profit == other.profit) {
                return Integer.compare(this.id, other.id);
            }
            return Integer.compare(other.profit, this.profit);
        }
    }


    // S에서 각 도시로 가는 최단 거리
    static void dijkstra() {
        boolean[] visit = new boolean[N];
        Arrays.fill(Dist, INF);
        Dist[S] = 0;

        for (int i = 0; i < N - 1; i++) {
            int v = 0, minDist = INF;
            for (int j = 0; j < N; j++) {
                if (!visit[j] && minDist > Dist[j]) {
                    v = j;
                    minDist = Dist[j];
                }
            }
            visit[v] = true;
            for (int j = 0; j < N; j++) {
                if (!visit[j] && Dist[v] != INF && graph[v][j] != INF && Dist[j] > Dist[v] + graph[v][j]) {
                    Dist[j] = Dist[v] + graph[v][j];
                }
            }
        }
    }

    // 200 여행 상품 추가
    static void addPackage(int id, int revenue, int dest) {
        isMade[id] = true;
        int profit = revenue - Dist[dest];
        pq.offer(new Package(id, revenue, dest, profit));
    }

    // 300 여행 상품 취소 기록
    static void cancelPackage(int id) {
        if (isMade[id]) isCancel[id] = true;
    }

    // 400 최적 여행 상품 판매
    static int sellPackage() {
        while (!pq.isEmpty()) {
            Package p = pq.peek();
            if (p.profit < 0) break;
            pq.poll();
            if (!isCancel[p.id]) return p.id;
        }
        return -1;
    }

    // 500 출발지 변경
    static void changeStart() {
        dijkstra();
        List<Package> packages = new ArrayList<>();
        while (!pq.isEmpty()) {
            packages.add(pq.poll());
        }
        for (Package p : packages) {
            addPackage(p.id, p.revenue, p.dest);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            switch (T) {
                case 100:
                    N = Integer.parseInt(st.nextToken());
                    M = Integer.parseInt(st.nextToken());
                    for (int i = 0; i < N; i++) {
                        Arrays.fill(graph[i], INF);
                        graph[i][i] = 0;
                    }
                    for (int i = 0; i < M; i++) {
                        int u = Integer.parseInt(st.nextToken());
                        int v = Integer.parseInt(st.nextToken());
                        int w = Integer.parseInt(st.nextToken());
                        // 두 도시간 여러 간선이 주어질 수 있으므로 min 값으로 저장
                        graph[u][v] = Math.min(graph[u][v], w);
                        graph[v][u] = Math.min(graph[v][u], w);
                    }
                    dijkstra();
                    break;
                case 200:
                    int id = Integer.parseInt(st.nextToken());
                    int revenue = Integer.parseInt(st.nextToken());
                    int dest = Integer.parseInt(st.nextToken());
                    addPackage(id, revenue, dest);
                    break;
                case 300:
                    int cancelId = Integer.parseInt(st.nextToken());
                    cancelPackage(cancelId);
                    break;
                case 400:
                    System.out.println(sellPackage());
                    break;
                case 500:
                    S = Integer.parseInt(st.nextToken());
                    changeStart();
                    break;
            }
        }
    }
}
