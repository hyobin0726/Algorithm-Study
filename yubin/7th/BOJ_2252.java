import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2252 {
    /**
     * 1. 위상정렬로 풀기
     * 2. 인접리스트 ,큐 사용
     * 3. 작은 거는 count 세기
     * 4. 큐에다가 count == 0인거 넣기
     * 5. 큐 while문 돌면서 꺼내고 그 애의 리스트들고와서 count--해주기
     *      5.1 만약 count == 0 이면 출력 해주기
     */

    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;

    static ArrayList<ArrayList<Integer>> adjList;
    static Queue<Integer> queue;
    static int[] edgeCount;
    static int N;  //총 몇명
    static int M;  //키를 비교한 회수

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        //입력받기(N,M)
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList<>();
        for (int i = 0; i < N+1; i++) {
            adjList.add(new ArrayList<>());
        }
        edgeCount = new int[N+1];

        //키 비교
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            adjList.get(num1).add(num2);
            edgeCount[num2]++;
        }

        //4. 큐에다가 count == 0인거 넣기
        queue = new LinkedList<>();
        for (int i = 1; i < N+1; i++) {
            if(edgeCount[i] == 0){
                queue.offer(i);
            }
        }

        //5. 큐 while문 돌면서 꺼내고 그 애의 리스트들고와서 count--해주기
        while (!queue.isEmpty()){
            int num = queue.poll();
            sb.append(num).append(" ");

            ArrayList<Integer> numList = adjList.get(num);
            for (int i = 0; i < numList.size(); i++) {
                int tmp = numList.get(i);
                edgeCount[tmp]--;

                if(edgeCount[tmp] == 0){
                    queue.offer(tmp);
                }
            }
        }
        System.out.println(sb);
    }
}
