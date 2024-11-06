import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16236 {

    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;

    static int N;
    static int[][] arr;
    //이동 횟수
    static int totalCnt = 0;
    //상어 크기
    static int sharkSize = 2;
    //먹은 물고기 개수
    static int eatSize = 0;
    //BFS queue
    static Queue<Node> queue;
    //상어 위치
    static int sharkX;
    static int sharkY;
    //방문 표시
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        //배열 만드면서 상어 위치 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 9){
                    sharkX = i;
                    sharkY = j;
                    arr[i][j] = 0;
                }
            }
        }

        while(true){

            Node node = BFS();
            //더 이상 먹을 물고기가 없을 때
            if(node == null){
                break;
            }

            //상어 위치와 이동횟수 업데이트
            arr[sharkX][sharkY] = 0;
            sharkX = node.x;
            sharkY = node.y;
            totalCnt += node.distance;
            arr[sharkX][sharkY] = 0;


            eatSize++;
            //지금까지 먹은 물고기 개수가 상어크기와 같으면 크기 크게 만들고, 먹은 횟수 초기화
            if(eatSize == sharkSize){
                sharkSize++;
                eatSize = 0;
            }
        }

        System.out.println(totalCnt);
    }

    static int[] dx = {-1,0,0,1};
    static int[] dy = {0,-1,1,0};
    static Node BFS(){
        //같은 거리더라도 위쪽, 왼쪽에 인거 골라야하기때문에 우선순위 큐
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        visited = new boolean[N][N];
        queue = new LinkedList<>();
        queue.offer(new Node(sharkX, sharkY, 0));

        int minDistance = Integer.MAX_VALUE;

        while(!queue.isEmpty()){
            Node node = queue.poll();

            //최소거리보다 더 길면 더 이상 구할 필요 없다.
            if(node.distance >  minDistance){
                break;
            }
            //4방 탐색
            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                //경계 넘어가면
                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                //상어 크기보다 더 클 때
                if(arr[nx][ny] > sharkSize) continue;
                //방문했던 곳이면
                if(visited[nx][ny]) continue;

                //방문 처리
                visited[nx][ny] = true;

                queue.offer(new Node(nx, ny, node.distance + 1));

                //물고기가 있는 곳이고 상어 크기보다 작을때이므로 충족된 것
                if(arr[nx][ny] > 0 && arr[nx][ny] < sharkSize){
                    //우선순위 큐에 넣어서 바로 정렬
                     priorityQueue.offer(new Node(nx, ny, node.distance  + 1));
                     //최소거리 update
                     minDistance = node.distance + 1;
                }
            }
        }
        //비었으면 null, 아니면 우선순위 큐에서 첫 번째 값을 반환하고 제거
        return priorityQueue.isEmpty() ? null : priorityQueue.poll();
    }
    static class Node implements Comparable<Node>{
        int x;
        int y;
        int distance;
        Node(int x, int y, int distance){
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            if(this.distance == o.distance){
                if(this.x == o.x){
                    return this.y - o.y;
                }
                return this.x - o.x;
            }
            return this.distance - o.distance;
        }
    }

}
