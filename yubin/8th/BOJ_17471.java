import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_17471 {
    /**
     * 게리맨더링
     *
     * 풀이: 방향이 없는 그래프로 인접리스트연결하고,
     *      부분집합으로 두 집합으로 나눈뒤, DFS로 연결되어 있는지 확인한 후 차이 최솟값 구하기
     *
     * 1. 인풋값 받기(N, population[N], 인접 정보)
     * 2. 인접리스트 연결하기
     * 3. 부분집합으로 두 집합으로 나눠보기
     * 4. DFS로 연결되어 있는지 확인하기
     * 5. 최소값 update
     */
    static int N;
    static int[] population;
    static ArrayList<Integer>[] adj;
    static boolean[] seleted;  //부분집합 두 집합으로 나누기 위한 용도
    static boolean[] visited;  //DFS 방문처리
    static int min = Integer.MAX_VALUE;

    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        //1. 인풋값 받기(N, population[N], 인접 정보)
        N = Integer.parseInt(br.readLine());

        population = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        //2. 인접리스트 연결하기
        adj = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int nums = Integer.parseInt(st.nextToken());
            for (int j = 0; j < nums; j++) {
                int num = Integer.parseInt(st.nextToken())-1;
                adj[i].add(num);
                adj[num].add(i);
            }
        }

        //3. 부분집합으로 두 집합으로 나눠보기
        seleted = new boolean[N];
        subSet(0);

        //min이 그대로 max라면 두 선거구를 나눌 수 있는 방법이 없을때
        if(min == Integer.MAX_VALUE){
            System.out.println("-1");
        }else{
            System.out.println(min);
        }

    }
    static void subSet(int cnt){
        if(cnt == N){
            //두 집합이 모두 연결되어 있는지 확인후 차이값 비교
            if(isConnected(true) && isConnected(false)){
                //최솟값 찾기
                minDiff();
            }
            return;
        }

        //selected값이 true인 경우 첫번째 집합, false인 경우 두번째 집합
        //첫번째 집합에 들어가는 경우
        seleted[cnt] = true;
        subSet(cnt + 1);
        //두번째 집합에 들어가는 경우
        seleted[cnt] = false;
        subSet(cnt + 1);
    }
    static boolean isConnected(boolean flag){
        //해당 요소 첫번재꺼 찾고
        //같은 flag인 애들이 다 연결되어 있는지 찾기
        int start = -1;
        for (int i = 0; i < N; i++) {
            if(seleted[i] == flag){
                start = i;
                break;
            }
        }

        //공집합인 경우, 즉 start가 -1 그대로
        if(start == -1){
            return false;
        }

        visited = new boolean[N];
        //연결되어 있는지 확인하기 위해 DFS사용
        DFS(start, flag);

        //방문이 안된애들이 있는지 체크
        for (int i = 0; i < N; i++) {
            if(seleted[i] == flag && !visited[i]) return false;
        }

        //다 갈 수 있는 경우
        return true;
    }
    static void DFS(int node, boolean flag){
        //방문하는지 체크하기
        visited[node] = true;
        for (int num :
                adj[node]) {
            //같은 집합이고, 방문하지 않았다면 DFS
            if(seleted[num] == flag && !visited[num]){
                DFS(num, flag);
            }
        }
    }
    static void minDiff(){
        int pA = 0;
        int pB = 0;

        for (int i = 0; i < N; i++) {
            if(seleted[i]){
                pA += population[i];
            }else{
                pB += population[i];
            }
        }

        min = Math.min(min, Math.abs(pA - pB));
    }
}
