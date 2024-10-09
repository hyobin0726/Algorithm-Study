import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_13913 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N, K;
    static int[] parent = new int[100001];
    static int[] time = new int[100001];

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs();

        Stack<Integer> stack = new Stack<>();
        stack.push(K);
        int index = K;

        while (index != N) {
            stack.push(parent[index]);
            index = parent[index];
        }

        while (index != N) {
            stack.push(parent[index]);
            index = parent[index];
        }

        // 최종 출력
        sb.append(time[K] - 1 + "\n");
        while (!stack.isEmpty()) {
            sb.append(stack.pop() + " ");
        }

        System.out.println(sb.toString());
    }
    static void bfs(){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(N);
        time[N] = 1;

        while(!queue.isEmpty()){
            int now = queue.poll();

            if(now == K) return;

            for (int i = 0; i < 3; i++) {
                int next;

                if(i == 0) next = now - 1;
                else if (i == 1) next = now + 1;
                else next = now * 2;

                if(next < 0 || next > 100000) continue;

                if(time[next] == 0){
                    queue.offer(next);
                    time[next] = time[now] + 1;
                    parent[next] = now;
                }

            }
        }
    }
}
