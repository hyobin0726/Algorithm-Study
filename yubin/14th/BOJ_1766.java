import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1766 {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N, M;
    static int[] edgeCount;
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  //문제의 수
        M = Integer.parseInt(st.nextToken());  //정보

        edgeCount = new int[N+1];
        graph = new ArrayList<>();
        for (int i = 0; i <= N ; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph.get(start).add(end);
            edgeCount[end]++;
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o1 - o2);

        for (int i = 1; i <= N; i++) {
            if(edgeCount[i] == 0) priorityQueue.add(i);
        }

        while(!priorityQueue.isEmpty()){
            int node = priorityQueue.poll();
            sb.append(node).append(" ");

            ArrayList<Integer> list = graph.get(node);
            for (int i = 0; i < list.size(); i++) {
                int num = list.get(i);
                edgeCount[num]--;
                if(edgeCount[num] == 0) priorityQueue.add(num);
            }
        }

        System.out.println(sb);
    }
}

