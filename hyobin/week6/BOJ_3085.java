import java.io.*;
import java.util.*;
public class BOJ_3085 {
    static int n;
    static int max = Integer.MIN_VALUE;
    static Character[][] candy;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        candy = new Character[n][n];
        for(int i=0; i <n; i++) {
            String str = br.readLine();
            for (int j =0; j<n; j++) {
                candy[i][j] = str.charAt(j);
            }
        }
        for(int i=0; i <n; i++) {
            for (int j =0; j<n; j++) {

                for(int k=0; k<4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (0<= nx && nx<n && 0<= ny && ny <n) {
                        //swap
                        Character temp = candy[i][j];
                        candy[i][j] = candy[nx][ny];
                        candy[nx][ny] = temp;
                        //최대 값 찾기
                        count();
                        //되돌리기
                        temp = candy[i][j];
                        candy[i][j] = candy[nx][ny];
                        candy[nx][ny] = temp;

                    }
                }

            }
        }
        System.out.println(max);


    }
    static void count() {
        //가로 확인
        for(int i=0; i<n; i++) {
            int temp =0;
            Character tc = candy[i][0];
            for(int j=0; j<n;j++) {
                //앞과 같으면 temp++
                if(tc == candy[i][j]) {
                    temp+=1;
                    //max값 갱신
                    if(max<temp) {
                        max = temp;
                    }
                // 다르면 temp =1로 초기화 후 다시 세기
                }else {
                    tc = candy[i][j];
                    temp =1;
                }
            }
        }
        //세로확인
        for(int i=0; i<n; i++) {
            int temp =0;
            Character tc = candy[0][i];
            for(int j=0; j<n;j++) {
                if(tc == candy[j][i]) {
                    temp+=1;
                    if(max<temp) {
                        max = temp;
                    }

                }else {
                    tc = candy[j][i];
                    temp =1;
                }
            }
        }
    }
}
