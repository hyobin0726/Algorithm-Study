/* DFS (리프노드에서 DFS 수행) */
/* 다른 방법 : 루트에서 최장 경로 구하고 -> 그 노드에서 DFS 수행 (무조건 DFS는 두 번만 실행됨) */
import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<Integer[]> graph[];
    static boolean visited[];
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];
        graph = new ArrayList[N+1];
        for (int i = 1; i < N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        boolean[] leaf = new boolean[N+1];
        Arrays.fill(leaf, true);
        for(int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[n1].add(new Integer[] {n2, cost});
            graph[n2].add(new Integer[] {n1, cost});
            leaf[n1] = false;
        }

        for (int i = 1; i < N+1; i++) {
            if (leaf[i]) {
                Arrays.fill(visited, false);
                dfs(i, 0);
            }
        }
        System.out.println(max);
    }

    public static void dfs(int v, int sum) {
        visited[v] = true;
        max = Math.max(max, sum);
        for (Integer[] next : graph[v]) {
            if (!visited[next[0]]) {
                dfs(next[0], sum + next[1]);
            }
        }
    }
}
