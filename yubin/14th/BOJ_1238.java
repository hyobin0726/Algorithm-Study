import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1238 {
    /**
     * BOJ 1238 G3 파티
     * 풀이: 다익스트라
     */
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N,M,X;
    static ArrayList<Node>[] adj, r_adj;
    static int[] dist, r_dist;

    static class Node implements Comparable<Node>{
        int node;
        int time;

        public Node(int node, int time){
            this.node = node;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  //N개의 숫자
        M = Integer.parseInt(st.nextToken());  //M개의 단방향 도로
        X = Integer.parseInt(st.nextToken());  //목적지 마을 번호

        adj = new ArrayList[N+1];
        r_adj = new ArrayList[N+1];
        dist = new int[N+1];
        r_dist = new int[N+1];

        for (int i = 1; i < N+1; i++) {
            adj[i] = new ArrayList<>();
            r_adj[i] = new ArrayList<>();
        }
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(r_dist, Integer.MAX_VALUE);

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            adj[start].add(new Node(end, value));
            r_adj[end].add(new Node(start, value));
        }

        dijkstra(adj, dist, X);
        dijkstra(r_adj, r_dist, X);

        int max = Integer.MIN_VALUE;
        for (int i = 1; i < N+1; i++) {
            max = Math.max(max, dist[i] + r_dist[i]);
        }
        System.out.println(max);
    }

    public static void dijkstra(ArrayList<Node>[] arr, int[] dist, int start){
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Node(start, 0));
        dist[start] = 0;

        while (!priorityQueue.isEmpty()){
            Node now = priorityQueue.poll();
            for (Node next : arr[now.node]) {
                if(dist[next.node] > dist[now.node] + next.time){
                    dist[next.node] = dist[now.node] + next.time;
                    priorityQueue.add(new Node(next.node, dist[next.node]));
                }
            }
        }
    }
}
