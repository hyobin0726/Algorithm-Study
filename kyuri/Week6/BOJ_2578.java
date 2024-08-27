import java.io.*;
import java.util.*;

/*
  ans : 정답 저장
  cnt : 빙고 줄 저장
  delete 함수 : 언급된 수 -1로 변경
  bingoCheck 함수 : row 가로 줄, col 세로 중, xy 오른쪽 아래 대각선, yx 왼쪽 아래 대각선 -> 합이 -5라면 빙고
*/
public class Main {
    static int [][] board = new int[5][5];
    static int ans, cnt;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 5; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 5; j++) {
                ans++;
                delete(Integer.parseInt(st.nextToken()));
                bingoCheck();
                if(cnt >= 3) {
                    System.out.println(ans);
                    return;
                }
            }
        }
    }

    public static void delete(int n){
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(board[i][j] == n) {
                    board[i][j] = -1;
                }
            }
        }
    }

    public static void bingoCheck() {
        cnt = 0;
        int row, col;
        int xy = 0, yx = 0;
        int idx = 0;
        for(int i = 0; i < 5; i++) {
            row = 0; col = 0;
            for(int j = 0; j < 5; j++) {
                row += board[i][j];
                col += board[j][i];
                if(i == j) xy += board[i][j];

                if(i == idx && j == 4 - idx) {
                    yx += board[i][j];
                    idx++;
                }
            }

            if(row == -5) cnt++;
            if(col == -5) cnt++;
            if(xy == -5) cnt++;
            if(yx == -5) cnt++;
        }
    }
}
