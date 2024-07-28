/* 중간값 위치보다 큰 경우, 그래프 두 개 생성, 다른 방법 보기.. */
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] big_graph = new ArrayList[N+1];
        ArrayList<Integer>[] small_graph = new ArrayList[N+1];
        int[] big_ans = new int[N+1];
        int[] small_ans = new int[N+1];

        for (int i = 0; i <= N; i++) {
            big_graph[i] = new ArrayList<>();
            small_graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            big_graph[a].add(b);
            small_graph[b].add(a);
        }

        for (int i = 1; i <= N; i++) {
            int[] b_visited = new int[N+1];
            int[] s_visited = new int[N+1];
            big_ans[i] = dfs(N, big_graph, i, b_visited, 0);
            small_ans[i] = dfs(N, small_graph, i, s_visited, 0);
        }

        int mid = (N + 1) / 2;
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            if (big_ans[i] >= mid || small_ans[i] >= mid) { ans++; }
        }
        System.out.println(ans);
    }

    public static int dfs(int N, ArrayList<Integer>[] graph, int v, int[] visited, int cnt) {
        visited[v] = 1;
        for (int e : graph[v]) {
            if (visited[e] == 0) {
                cnt = dfs(N, graph, e, visited, cnt+1);
            }
        }
        return cnt;
    }
}
