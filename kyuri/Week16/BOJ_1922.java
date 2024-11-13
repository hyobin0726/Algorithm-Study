import java.io.*;
import java.util.*;

public class Main {
    static int N, M, ans;
    static int[] parent;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
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
        System.out.println(ans);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    public static int find(int a) {
        if (parent[a] == a) return a;
        int root_node = find(parent[a]);
        parent[a] = root_node;
        return root_node;
    }

    public static void kruskal() {
        ans = 0;
        for (int i = 0; i < M; i++) {
            int a = graph[i][0], b = graph[i][1];
            if (find(a) != find(b)) {
                ans += graph[i][2];
                union(a, b);
            }
        }
    }
}
