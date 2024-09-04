import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1005 {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int TC;
    static int N; //건물의 개수
    static int K; //건설순서 규칙 개수
    //인접리스트
    static ArrayList<ArrayList<Integer>> adjList;
    static Queue<Integer> queue;
    //건설시간 배열
    static int[] time;
    //특정건물
    static int checkBuildingNum;
    //각 노드의 들어오는 간선의 개수
    static int[] edgeCount;
    //노드 당 건설되려면 걸리는 시간(최종 시간구하기)
    static int[] result;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        //입력
        //테스트 케이스 개수
        TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            adjList = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            for (int i = 0; i < N + 1; i++) {
                adjList.add(new ArrayList<>());
            }

            time = new int[N+1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < N+1; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }

            edgeCount = new int[N+1];
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int num1 = Integer.parseInt(st.nextToken());
                int num2 = Integer.parseInt(st.nextToken());
                //방향있느 그래프
                adjList.get(num1).add(num2);
                //각 노드마다 들어오는 간선의 개수
                edgeCount[num2]++;
            }

            checkBuildingNum = Integer.parseInt(br.readLine());

            result = new int[N + 1];
            queue = new LinkedList<>();
            for (int i = 1; i < N+1; i++) {
                //들어오는 간선의 개수가 0인것, 즉 바로 시작이 가능한 애들
                if(edgeCount[i]==0){
                    queue.offer(i);
                    //자기 건설시간을 먼저 더해줌
                    result[i] = time[i];
                }
            }

            while(!queue.isEmpty()){
                int num = queue.poll();

                //갈 수 있는 노드들
                ArrayList<Integer> numList = adjList.get(num);
                for (int i = 0; i < numList.size(); i++) {
                    int next = numList.get(i);
                    //다음노드의 건설시간과, 현재노드의 건선시간 + 다음 빌딩의 건설시간 더해줘서 max 값 찾기
                    result[next] = Math.max(result[next], result[num] + time[next]);

                    //해당 건물을 짓기전에 지어야하는 건물의 수이므로, 하나 지어졌으니까 마이너스
                    edgeCount[numList.get(i)]--;

                    //이미 이전에 지어져야하는 건물들이 다 지어진 경우이므로 이제 해당 건물 큐에 넣어주기
                    if(edgeCount[numList.get(i)] == 0){
                        queue.offer(numList.get(i));
                    }
                }
            }
            sb.append(result[checkBuildingNum]).append("\n");
        }
        System.out.println(sb);
    }

}
