import java.io.*;
import java.util.*;

public class BOJ_3584 {
    static int t;
    static int n;
    static ArrayList<Integer>[] arr;
    static boolean[] visited;
    static int a;
    static int b;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        t=Integer.parseInt(br.readLine());
        for (int test =0; test < t ; test ++ ) {
            n = Integer.parseInt(br.readLine());
            arr = new ArrayList[n + 1];
            visited = new boolean[n + 1];

            for (int i = 1; i < n + 1; i++) {
                arr[i] = new ArrayList<>();
            }
            for (int i = 0; i < n - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());
                arr[child].add(parent);
            }
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            System.out.println(dfs());
        }
    }
    static int dfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(a);
        visited[a] = true;
        queue.add(b);
        visited[b] = true;

        while (!queue.isEmpty()){
            int temp = queue.poll();
            for (int i =0; i<arr[temp].size(); i ++){
                if (visited[arr[temp].get(i)] ){
                    return arr[temp].get(i);
                }else {
                    visited[arr[temp].get(i)] =true;
                    queue.add(arr[temp].get(i));

                }
            }
        }
        return 0;
    }
}
