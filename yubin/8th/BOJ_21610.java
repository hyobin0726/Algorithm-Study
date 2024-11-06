import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_21610 {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;

    static int N,M;
    static int[][] arr;
    static int[][] cmds;

    static int[] dx = {-1,-1,1,1};
    static int[] dy = {-1,1,-1,1};

    static boolean[][] check;

    static ArrayList<Node> nodes;
    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  //배열 크기
        M = Integer.parseInt(st.nextToken());  //명령

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cmds = new int[M][2]; //첫번째: 방향, 두번째: 몇칸 이동
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            cmds[i][0] = Integer.parseInt(st.nextToken());
            cmds[i][1] = Integer.parseInt(st.nextToken());
        }

        nodes = new ArrayList<>();
        nodes.add(new Node(N-1,0));
        nodes.add(new Node(N-1,1));
        nodes.add(new Node(N-2,0));
        nodes.add(new Node(N-2,1));

        for (int c = 0; c < cmds.length; c++) {
            moveCloud(cmds[c][0], cmds[c][1]);
            check = new boolean[N][N];

            for (int i = 0; i < nodes.size(); i++) {
                Node node = nodes.get(i);
                arr[node.x][node.y]++;
            }
            for (int i = 0; i < nodes.size(); i++) {
                Node node = nodes.get(i);
                int sum = 0;
                check[node.x][node.y] = true;
                for (int j = 0; j < 4; j++) {
                    int nx = node.x + dx[j];
                    int ny = node.y + dy[j];

                    if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    if(arr[nx][ny] == 0) continue;
                    sum++;
                }

                arr[node.x][node.y] += sum;
            }

            nodes.clear();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(check[i][j]) continue;
                    if(arr[i][j] >= 2) {
                        arr[i][j] -= 2;
                        nodes.add(new Node(i,j));
                    }
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += arr[i][j];
            }
        }
        System.out.println(sum);

    }
    static class Node{
        int x, y;

        public Node(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }

    }
    static void moveCloud(int direction, int num) {
        int dx = 0;
        int dy = 0;
        switch(direction) {
            case 1: //왼쪽
                dx = 0;
                dy = -1;
                break;
            case 2: //왼쪽위 대각선
                dx = -1;
                dy = -1;
                break;
            case 3: //위로
                dx = -1;
                dy = 0;
                break;
            case 4: //오른쪽 위 대각선
                dx = -1;
                dy = 1;
                break;
            case 5: //오른쪽
                dx = 0;
                dy = 1;
                break;
            case 6: //오른쪽 아래 대각선
                dx = 1;
                dy = 1;
                break;
            case 7: //아래쪽
                dx = 1;
                dy = 0;
                break;
            case 8:  //왼쪽 아래 대각선
                dx = 1;
                dy = -1;
                break;
        }



        for (int i = 0; i < num; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                int x = nodes.get(j).x;
                int y = nodes.get(j).y;
                if(dx < 0) {
                    if(x + dx == -1) {
                        nodes.get(j).x = N-1;
                    }else {
                        nodes.get(j).x--;
                    }
                }else if(dx > 0){
                    if(x + dx == N) {
                        nodes.get(j).x = 0;
                    }else {
                        nodes.get(j).x++;
                    }
                }

                if(dy < 0) {
                    if(y + dy == -1) {
                        nodes.get(j).y = N-1;
                    }else {
                        nodes.get(j).y--;
                    }
                }else if(dy > 0){
                    if(y + dy == N) {
                        nodes.get(j).y = 0;
                    }else {
                        nodes.get(j).y++;
                    }
                }
            }
        }
    }
}
