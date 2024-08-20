import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
public class Solution {
 
    static class BC{
        int x;
        int y;
        int c;
        int p;
    }
 
    static int M;
    static int A;
    static BC[] bc;
    static int[] move_a;
    static int[] move_b;
    static List<Integer> accessible_a; //접속할 수 있는 무선충전기의 번호
    static List<Integer> accessible_b;
 
    static int xa, ya, xb, yb;
 
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
 
    public static void main(String[] args) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
 
        int TC = Integer.parseInt(br.readLine());
        for (int i = 1; i <= TC; i++) {
            st = new StringTokenizer(br.readLine().trim());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
 
            move_a = new int[M+1];  //맨앞에 움직이않을때를 추가
            move_b = new int[M+1];
 
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 1; j <= M; j++) {
                move_a[j] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 1; j <= M; j++) {
                move_b[j] = Integer.parseInt(st.nextToken());
            }
 
            bc = new BC[A];
            //무선 충전기 정보 받기
 
            for (int j = 0; j < A; j++) {
                st = new StringTokenizer(br.readLine().trim());
                BC tmp = new BC();
                tmp.x = Integer.parseInt(st.nextToken());
                tmp.y = Integer.parseInt(st.nextToken());
                tmp.c = Integer.parseInt(st.nextToken());
                tmp.p = Integer.parseInt(st.nextToken());
                bc[j] = tmp;
            }
 
            //사용자 초기 위치 설정
            xa = 1;
            ya = 1;
            xb = 10;
            yb = 10;
 
            int total_sum = 0;
 
            for (int j = 0; j < M+1; j++) {
                //각 사용자 위치 계산
                update_a_position(move_a[j]);
                update_b_position(move_b[j]);
                 
                //각 사용자가 접속 가능한 bc 찾기
                accessible_a = find_accessible_by_a();
                accessible_b = find_accessible_by_b();
                 
                //각 사용자에게 bc 선택(모든 경우의 수, 반복문, 매번 갯수 세기)
                int max = 0;
                if(accessible_a.isEmpty()){
                    for (int b :
                            accessible_b) {
                        if(max < bc[b].p){
                            max = bc[b].p;
                        }
                    }
                }
                 
                if(accessible_b.isEmpty()){
                    for (int a :
                            accessible_a) {
                        if(max < bc[a].p){
                            max = bc[a].p;
                        }
                    }
                }
 
                for (int a :
                        accessible_a) {
                    for (int b :
                            accessible_b) {
                        int sum = 0;
                        sum += bc[a].p;
                        sum += bc[b].p;
                        if(a == b) sum /= 2;
                        if(sum > max) max = sum;
                     }
                }
 
                total_sum += max;
                 
            }
 
            System.out.println("#" + i + " " + total_sum);
        }
    }
 
    public static void update_a_position(int dir){
        switch (dir){
            case 0:
                break;
            case 1:
                ya -= 1;
                break;
            case 2:
                xa += 1;
                break;
            case 3:
                ya += 1;
                break;
            case 4:
                xa -= 1;
                break;
        }
    }
    public static void update_b_position(int dir){
        switch (dir){
            case 0:
                break;
            case 1:
                yb -= 1;
                break;
            case 2:
                xb += 1;
                break;
            case 3:
                yb += 1;
                break;
            case 4:
                xb -= 1;
                break;
        }
 
    }
    //사용자 현재 위치와 모든 bc 위치 거리를 계산해서
    //만약 c안에 들어오면
    //해당 bc idx 번호를 리스트에 추가
    public static List<Integer> find_accessible_by_a(){
        List<Integer> accessible = new ArrayList<>();
 
        for (int i = 0; i < A; i++) {
            BC temp = bc[i];
             
            int d = Math.abs(xa - temp.x) + Math.abs(ya - temp.y);
            if(d <= temp.c){
                accessible.add(i);
            }
        }
        return accessible;
    }
 
    public static List<Integer> find_accessible_by_b(){
        List<Integer> accessible = new ArrayList<>();
 
        for (int i = 0; i < A; i++) {
            BC temp = bc[i];
 
            int d = Math.abs(xb - temp.x) + Math.abs(yb - temp.y);
            if(d <= temp.c){
                accessible.add(i);
            }
        }
        return accessible;
    }
}
