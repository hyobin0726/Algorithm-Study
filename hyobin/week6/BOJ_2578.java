import java.io.*;
import java.util.*;
public class BOJ_2578 {
    static int[][] binggo;
    static int[][] speak;
    static boolean[][] visited;
    static int total;
    static boolean left;
    static boolean right;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        binggo = new int[5][5];
        speak = new int[5][5];
        visited= new boolean[5][5];
        total=0;
        for(int i =0; i<5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j =0; j<5; j++) {
                binggo[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i =0; i<5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j =0; j<5; j++) {
                speak[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean isfind= false;
        for(int i =0; i<5; i++) {
            for(int j =0; j<5; j++) {
                num(speak[i][j]);
                cnt+=1;
                if(total >=3) {
                    System.out.println(cnt);
                    isfind = true;
                    break;
                }
            }
            if(isfind) {
                break;
            }
        }

    }

    static void num(int number) {
        int x=0;
        int y=0;
        for(int i =0; i<5; i++) {
            for(int j =0; j<5; j++) {
                //사회자가 부른 수 방문
                if(number == binggo[i][j]) {
                    x=i;
                    y=j;
                    visited[i][j] = true;
                    break;
                }

            }
        }

        //가로 확인
        int row=0;
        for (int i=0; i<5; i++) {
            if(visited[x][i]) {
                row+=1;
            }
        }
        //방문한 가로가 5개면
        if(row==5) {
            total+=1;
        }
        //세로확인
        int column=0;
        for (int i=0; i<5; i++) {
            if(visited[i][y]) {
                column+=1;
            }
        }
        //방문한 세로가 5개면
        if(column==5) {
            total+=1;
        }
        //대각선 확인
        if(!left) {
            if(visited[0][0] && visited[1][1] && visited[2][2] && visited[3][3] && visited[4][4]) {
                total+=1;
                left=true;
            }

        }
        if(!right) {
            if(visited[0][4] && visited[1][3] && visited[2][2] && visited[3][1] && visited[4][0]) {
                total+=1;
                right = true;
            }

        }

    }


}
