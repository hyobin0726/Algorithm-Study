/* DFS */
import java.util.*;
import java.io.*;

public class Main {
    static int[] graph;
    static boolean[] visited;
    static ArrayList<Integer> cycle;
    static int team;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());

            graph = new int[N+1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                int e = Integer.parseInt(st.nextToken());
                graph[i] = e;
            }

            visited = new boolean[N+1];
            team = 0;
            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    cycle = new ArrayList<>();
                    dfs(i);
                }
            }
            System.out.println(N - team);
        }

    }

    public static int dfs(int node) {
        visited[node] = true;
        cycle.add(node);
        int next = graph[node];
        if (visited[next]) {
            if (cycle.contains(next)) {
                team += (cycle.size() - cycle.indexOf(next));
            }
            return 0;
        } else {
            return dfs(next);
        }
    }
}
