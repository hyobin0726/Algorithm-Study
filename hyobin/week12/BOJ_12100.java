import java.io.*;
import java.util.*;

//n*n map
//위 아래 오른쪽 왼쪽 구현
// 실행 -> 백트래킹 - >.. 반복

public class Main {
    static int n;
    static int[][] map;
    static int max =Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0);
        System.out.println(max);


    }
    static void dfs(int cnt ){
        if(cnt==5){
            m();
            return;
        }
        int[][] temp = new int[n][n];
        for(int i=0; i<n;i++){
            for(int j =0; j<n; j++){
                temp[i][j] = map[i][j];
            }
        }
        for(int d=0;d<4; d++){
            move(d);
            dfs(cnt+1);
            for(int i=0; i<n;i++){
                for(int j =0; j<n; j++){
                    map[i][j] = temp[i][j];
                }
            }
        }

    }
    //max값 찾기
    static void m(){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(map[i][j]>max){
                    max=map[i][j];
                }
            }
        }
    }
    static void move(int d){
        switch (d){
            case 0:
                up();
                break;
            case 1:
                down();
                break;
            case 2:
                left();
                break;
            case 3:
                right();
                break;
        }
    }
    static void up() {
        List<Integer> list;
        for(int i=0; i<n; i++) {
            list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (map[j][i] != 0) {
                    list.add(map[j][i]);
                }
                map[j][i] = 0;
            }
            int cnt = 0;
            for (int j = 0; j < list.size(); j++) {
                if (j<list.size()-1 && list.get(j).equals( list.get(j + 1))) {
                    map[cnt][i] = list.get(j) * 2;
                    j++;

                } else {
                    map[cnt][i] = list.get(j);
            }
                cnt++;
        }

        }
    }
    static void down() {
        List<Integer> list;
        for(int i=0; i<n; i++){
            list = new ArrayList<>();
            for(int j=0; j<n; j++){
                if(map[j][i] !=0){
                    list.add(map[j][i]);
                }
                map[j][i] = 0;
            }
            int cnt = n-1;
            for(int j=list.size()-1; j>=0;j--){
                if(j>0 && list.get(j).equals(list.get(j-1))){
                        map[cnt][i] = list.get(j)*2;
                        j--;
                    }else {
                        map[cnt][i] = list.get(j);
                    }
                cnt--;
                }
        }
    }
    static void left() {
        List<Integer> list;
        for(int i=0; i<n; i++){
            list = new ArrayList<>();
            for(int j=0; j<n; j++){
                if(map[i][j] !=0){
                    list.add(map[i][j]);
                }
                map[i][j] = 0;
            }
            int cnt = 0;
            for(int j=0; j<list.size();j++){
                if(j<list.size()-1 && list.get(j).equals(list.get(j+1))){
                        map[i][cnt] = list.get(j)*2;
                        j++;
                    }else {
                        map[i][cnt] = list.get(j);
                    }
                cnt++;
                }

        }
    }
    static void right() {
        List<Integer> list;
        for(int i=0; i<n; i++){
            list = new ArrayList<>();
            for(int j=0; j<n; j++){
                if(map[i][j] !=0){
                    list.add(map[i][j]);
                }
                map[i][j] = 0;
            }
            int cnt = n-1;
            for(int j=list.size()-1; j>= 0; j--){
                    if(j> 0 && list.get(j).equals(list.get(j-1))){
                        map[i][cnt] = list.get(j)*2;
                        j--;
                    }else {
                        map[i][cnt] = list.get(j);
                    }
                    cnt--;
                }
        }
    }
}
