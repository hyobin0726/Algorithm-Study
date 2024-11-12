import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ _2660 {
    /**
     * BOJ 2660 G5 회장뽑기
     * 풀이: 플로이드 워셜
     */

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N;
    static int[][] adjList;


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        adjList = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(i != j) {
                    adjList[i][j] = 1000000000;
                }
            }
        }

        while(true){
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            if(num1 == -1) break;

            adjList[num1][num2] = 1;
            adjList[num2][num1] = 1;
        }

        for (int k = 1; k < N+1; k++) {
            for (int i = 1; i < N+1; i++) {
                for (int j = 1; j < N+1; j++) {
                    adjList[i][j] = Math.min(adjList[i][j], adjList[i][k] + adjList[k][j]);
                    adjList[j][i] = Math.min(adjList[j][i], adjList[j][k] + adjList[k][i]);
                }
            }
        }
        
        //회장 찾기
        ArrayList<Integer> findPresident = new ArrayList<>();
        int value = Integer.MAX_VALUE;
        for (int i = 1; i < adjList.length; i++) {
            int valueByPerson = 0;
            for (int j = 1; j < adjList[i].length; j++) {
                valueByPerson = Math.max(valueByPerson, adjList[i][j]);
            }
            if(value > valueByPerson){
                value = valueByPerson;
                findPresident.clear();
                findPresident.add(i);
            }else if(value == valueByPerson){
                findPresident.add(i);
            }
        }

        sb.append(value).append(" ").append(findPresident.size()).append("\n");
        for (int i = 0; i < findPresident.size(); i++) {
            sb.append(findPresident.get(i)).append(" ");
        }

        System.out.println(sb);
    }
}
