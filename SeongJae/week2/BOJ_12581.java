import java.util.*;

public class BOJ_12581 {

    public static void bfs(int n, int k, boolean[] visited) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{n, 0});
        int count = 0;
        int deadline = 100001;
        boolean isFindResult = false;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int loc = current[0];
            int time = current[1];
            visited[loc] = true;

            if (loc == k && (!isFindResult || time <= deadline)) {
                count++;
                deadline = time;
                isFindResult = true;
            }

            int[] nextPositions = new int[]{loc - 1, loc + 1, 2 * loc};
            for (int des : nextPositions) {
                if (0 <= des && des <= 100000 && !visited[des] && (!isFindResult || time < deadline)) {
                    queue.add(new int[]{des, time + 1});
                }
            }
        }
        
        System.out.println(deadline);
        System.out.println(count);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        boolean[] visited = new boolean[100001];
        bfs(n, k, visited);
    }
}
