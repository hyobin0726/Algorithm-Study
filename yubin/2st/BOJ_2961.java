import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int[] sour;
    public static int[] bitter;
    public static int minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        sour = new int[N];
        bitter = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            sour[i] = Integer.parseInt(st.nextToken());
            bitter[i] = Integer.parseInt(st.nextToken());
        }

        backTracking(1,0,0,0);
        System.out.println(minDiff);

    }

    public static void backTracking(int s,int b, int level, int selectedCount){
        if(level == N){
           if(selectedCount != 0 && Math.abs(s-b) < minDiff){
               minDiff = Math.abs(s-b);
           }
            return;
        }

        backTracking(s*sour[level], b+bitter[level], level+1, selectedCount+1);
        backTracking(s,b,level+1, selectedCount);

    }
}