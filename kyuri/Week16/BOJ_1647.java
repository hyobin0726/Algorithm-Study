import java.io.*;
import java.util.*;

public class Main {
    static int N, M, ans, max_val;
    static int[] parent;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[M][3];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            graph[i][0] = Integer.parseInt(st.nextToken());
            graph[i][1] = Integer.parseInt(st.nextToken());
            graph[i][2] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(graph, (o1, o2) -> o1[2] - o2[2]);

        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        kruskal();
        System.out.println(ans - max_val);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    public static int find(int a) {
        if (parent[a] == a) return a;
        return find(parent[a]);
    }

    public static void kruskal() {
        ans = 0;
        max_val = 0;
        for (int i = 0; i < M; i++) {
            int a = graph[i][0], b = graph[i][1];
            if (find(a) != find(b)) {
                ans += graph[i][2];
                max_val = Math.max(max_val, graph[i][2]);
                union(a, b);
            }
        }
    }
}
