import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_21608 {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;
    static int N;
    static int[][] likeNum;
    static int[][] resultArr;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static class Node implements Comparable<Node>{
        int x, y;
        int likeNum;
        int emptyNum;
        Node(int x, int y, int likeNum, int emptyNum){
            this.x = x;
            this.y = y;
            this.likeNum = likeNum;
            this.emptyNum = emptyNum;
        }

        //3번 기준 정렬시키기
        @Override
        public int compareTo(Node o) {
            if(o.x == this.x){
                return this.y - o.y;
            }
            return this.x - o.x;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        likeNum = new int[N*N + 1][4];
        for (int i = 0; i < N * N; i++) {

        }
        resultArr = new int[N+1][N+1];

        for (int i = 1; i <= N*N; i++) {
            st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
                likeNum[student][j] = Integer.parseInt(st.nextToken());
            }
            ArrayList<Node> arrayList1 = new ArrayList<>();

            //1.비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
            int max = Integer.MIN_VALUE;
            //배열 돌기
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= N; k++) {
                    //비어있지 않으면 넘어가기
                    if (resultArr[j][k] != 0) continue;

                    int sum = 0;  //좋아하는 학생수
                    int sum2 = 0;  //빈 칸 수
                    for (int l = 0; l < 4; l++) {
                        int nx = j + dx[l];
                        int ny = k + dy[l];
                        if (nx < 1 || ny < 1 || nx > N || ny > N) continue;
                        for (int m = 0; m < 4; m++) {
                            if (resultArr[nx][ny] == likeNum[student][m]) sum++;
                        }
                        if (resultArr[nx][ny] == 0) sum2++;
                    }

                    if (max < sum) {
                        arrayList1.clear();
                        arrayList1.add(new Node(j, k, sum, sum2));
                        max = sum;
                    } else if (max == sum) {
                        arrayList1.add(new Node(j, k, sum, sum2));
                    }
                }
            }

            if (arrayList1.size() == 1) {
                Node node = arrayList1.get(0);
                resultArr[node.x][node.y] = student;
            } else if (arrayList1.size() > 1) {
                //2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
                int emptyMax = Integer.MIN_VALUE;
                ArrayList<Node> arrayList2 = new ArrayList<>();
                for (int j = 0; j < arrayList1.size(); j++) {
                    Node node = arrayList1.get(j);
                    if (node.emptyNum > emptyMax) {
                        arrayList2.clear();
                        arrayList2.add(node);
                        emptyMax = node.emptyNum;
                    } else if (node.emptyNum == emptyMax) {
                        arrayList2.add(node);
                    }
                }
                if (arrayList2.size() == 1) {
                    Node node = arrayList2.get(0);
                    resultArr[node.x][node.y] = student;
                } else if (arrayList2.size() > 1) {
                    //3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
                    Collections.sort(arrayList2);
                    Node node = arrayList2.get(0);
                    resultArr[node.x][node.y] = student;
                }
            }
        }
        int result = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int sum = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(nx < 0 || ny < 0 || nx > N || ny > N) continue;
                    for (int m = 0; m < 4; m++) {
                        if(resultArr[nx][ny] == likeNum[resultArr[i][j]][m]) sum++;
                    }
                }
                if(sum > 0){
                    result += Math.pow(10, sum-1);

                }
            }
        }
        System.out.println(result);
    }
}
