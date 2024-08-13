/* 조합, BFS */
import java.io.*;
import java.util.*;

public class Main {
    static int N, ans = Integer.MAX_VALUE;
    static int[] people, visited, arr;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        people = new int[N];
        arr = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            people[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++)
            list.add(new ArrayList<>());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++)
                list.get(i).add(Integer.parseInt(st.nextToken()));
        }

        comb(1);
        if (ans == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);
    }

    public static void comb(int depth) {
        if (depth == (N + 1)) {
            visited = new int[N + 1];
            int a = 0, b = 0;
            for (int i = 0; i < N; i++) {
                if (arr[i + 1] == 1) a += people[i];
                else b += people[i];
            }

            int cnt = 0;
            for (int i = 1; i <= N; i++) {
                if (visited[i] == 1) continue;
                bfs(i, arr[i]);
                cnt++;
            }

            if (cnt == 2) ans = Math.min(ans, Math.abs(a - b));
            return;
        }

        arr[depth] = 1;
        comb(depth + 1);

        arr[depth] = 0;
        comb(depth + 1);
    }

    public static void bfs(int num, int local) {
        Queue<Integer> q = new LinkedList<>();
        q.add(num);
        visited[num] = 1;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : list.get(cur)) {
                if (arr[next] == local && visited[next] == 0) {
                    visited[next] = 1;
                    q.add(next);
                }
            }
        }
    }
}
