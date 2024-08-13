import java.io.*;
import java.util.*;

public class BOJ_17070 {

    static int N;
    static int[][] arr;
    static int result = 0;
    
    static Queue<Node> queue = new LinkedList<>();    

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        
        BFS(new Node(0,1,1));

        System.out.println(result);

    }

    static void BFS(Node n){
        queue.add(n);
        
        while(!queue.isEmpty()){
            Node now = queue.poll();

            if((now.x == N - 1) && (now.y == N - 1)){
                result++;
            }
            
            if(now.m == 1 || now.m == 3){  //가로로 놓을 경우
                if(now.y + 1 < N &&  arr[now.x][now.y + 1] != 1){  //이전게 가로, 대각선인 경우
                    queue.offer(new Node(now.x, now.y + 1, 1));
                }
            }

            if(now.m == 2 || now.m == 3 ){  //세로로 놓을 경우
                if(now.x + 1 < N && arr[now.x + 1][now.y] != 1){  //이전게 가로, 대각선인 경우
                    queue.offer(new Node(now.x + 1, now.y, 2));
                }
            }

            if(now.x + 1 < N && now.y + 1 < N  && arr[now.x + 1][now.y + 1] != 1
                    && arr[now.x+1][now.y] != 1 && arr[now.x][now.y + 1] != 1){  //이전게 가로, 대각선인 경우
                queue.offer(new Node(now.x + 1, now.y + 1, 3));
            }
            
        }
    }

}

class Node{
    int x;
    int y;
    int m;
    
    Node(int x, int y, int m){
        this.x = x;
        this.y = y;
        this.m = m;
    }
}
