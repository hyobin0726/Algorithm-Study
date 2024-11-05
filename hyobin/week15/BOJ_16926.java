import java.io.*;
import java.util.*;
public class Main {
    static int n,m,r;
    static int[][] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n= Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r= Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for(int i =0; i<n ;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
             map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    for(int round=0; round <r ; round++) {
        for (int s = 0; s < Math.min(n, m)/2; s++) {
            int temp = map[s][s];
            //위
            for (int i = s; i < m - 1 - s; i++) {
                map[s][i] = map[s][i + 1];
            }
            //오른
            for (int i = s; i < n  - 1- s; i++) {
                map[i][m - 1 - s] = map[i + 1][m - 1 - s];
            }
            //아래
            for (int i = m - 1 - s; i > s; i--) {
                map[n - 1 - s][i] = map[n - 1 - s][i - 1];
            }
            //왼
            for (int i = n - 1 - s; i > s; i--) {
                map[i][s] = map[i - 1][s];
            }

            map[s + 1][s] = temp;
        }
    }
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
