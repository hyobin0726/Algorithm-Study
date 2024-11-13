import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] dist;
    static boolean[] visited;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] dist;
    static boolean[] visited;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        for (int i = 0; i < N; i++) {
           st = new StringTokenizer(br.readLine());
           for (int j = 0; j < N; j++) {
               graph[i][j] = Integer.parseInt(st.nextToken());
           }
        }

        dist = new int[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            dist[i] = (int) 1e9;
        }

        dist[0] = 0;
        long ans = 0;
        for(int i = 0; i < N; i++) {
            int minIndex = -1;
            for(int j = 0; j < N; j++) {
                if(visited[j]) continue;

                if(minIndex == -1 || dist[minIndex] > dist[j]) minIndex = j;
            }

            visited[minIndex] = true;
            ans += dist[minIndex];

            for(int j = 0; j < N; j++) {
                if(graph[minIndex][j] > 0)
                    dist[j] = Math.min(dist[j], graph[minIndex][j]);
            }
        }

        System.out.print(ans);
    }

}
        graph = new int[N][N];
        for (int i = 0; i < N; i++) {
           st = new StringTokenizer(br.readLine());
           for (int j = 0; j < N; j++) {
               graph[i][j] = Integer.parseInt(st.nextToken());
           }
        }

        dist = new int[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            dist[i] = (int) 1e9;
        }

        dist[0] = 0;
        int ans = 0;
        for(int i = 0; i < N; i++) {
            int minIndex = -1;
            for(int j = 0; j < N; j++) {
                if(visited[j]) continue;

                if(minIndex == -1 || dist[minIndex] > dist[j]) minIndex = j;
            }

            visited[minIndex] = true;
            ans += dist[minIndex];

            for(int j = 0; j < N; j++) {
                if(graph[minIndex][j] > 0)
                    dist[j] = Math.min(dist[j], graph[minIndex][j]);
            }
        }

        System.out.print(ans);
    }

}
