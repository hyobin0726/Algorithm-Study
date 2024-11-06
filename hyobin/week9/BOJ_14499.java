import java.io.*;
import java.util.*;
public class Main {
    static int n;
    static int m;
    static int x;
    static int y;
    static int k;
    static int[][] map;
    static int[] dice;

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x= Integer.parseInt(st.nextToken());
        y= Integer.parseInt(st.nextToken());
        k= Integer.parseInt(st.nextToken());
        map = new int[n][m];
        //맵생성
        for(int i =0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j= 0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //주사위 배열 생성 0은 위를 뜻함
        dice = new int[6];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<k ;i++){
            int order = Integer.parseInt(st.nextToken());
            //하일 경우
            if(order == 4){
              //좌표이동
                if(0<= x+1 && x+1<n){
                    x +=1;
                    //인덱스 이동
                    int temp = dice[0];
                    for(int a=0; a<3; a++){
                        dice[a] = dice[a+1];
                    }
                    dice[3] = temp;
                    System.out.println(dice[0]);
                    if(map[x][y] == 0){
                        map[x][y] = dice[2];
                    }else {
                        dice[2] = map[x][y];
                        map[x][y] = 0;
                    }
                }

            }
            //우 일경우
            if(order ==1){
                if(0<= y+1 && y+1<m){
                    y+=1;
                    int temp = dice[0];
                    dice[0] = dice[5];
                    dice[5] = dice[2];
                    dice[2] =dice[4];
                    dice[4] =temp;
                    System.out.println(dice[0]);
                    if(map[x][y] == 0){
                        map[x][y] = dice[2];
                    }else {
                        dice[2] = map[x][y];
                        map[x][y] = 0;
                    }

                }
            }
            //상일 경우
            if(order == 3){
                //좌표이동
                if(0<= x-1 && x-1<n){
                    x -=1;
                    //인덱스 이동
                    int temp = dice[0];
                    dice[0] = dice[3];
                    dice[3]= dice[2];
                    dice[2] = dice[1];
                    dice[1] = temp;
                    System.out.println(dice[0]);
                    if(map[x][y] == 0){
                        map[x][y] = dice[2];
                    }else {
                        dice[2] = map[x][y];
                        map[x][y] = 0;
                    }
                }

            }
            //좌일경우
            if(order ==2){
                if(0<= y-1 && y-1<m){
                    y-=1;
                    int temp = dice[0];
                    dice[0] = dice[4];
                    dice[4] = dice[2];
                    dice[2] =dice[5];
                    dice[5] =temp;
                    System.out.println(dice[0]);
                    if(map[x][y] == 0){
                        map[x][y] = dice[2];
                    }else {
                        dice[2] = map[x][y];
                        map[x][y] = 0;
                    }

                }
            }
        }
    }
}
