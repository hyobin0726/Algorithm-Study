import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int maxValue = Integer.MIN_VALUE;
    public static int minValue = Integer.MAX_VALUE;
    public static int[] operator = new int[4];
    public static int N;
    public static int[] number;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        number = new int[N];

        for (int i = 0; i <N; i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }

        backTracking(number[0], 1);

        System.out.println(maxValue);
        System.out.println(minValue);
    }

    public static void backTracking(int num, int index){
        if(index == N){
            minValue = Math.min(minValue, num);
            maxValue = Math.max(maxValue, num);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if(operator[i] > 0){
                operator[i]--;

                switch (i){
                    case 0:
                        backTracking(num + number[index], index + 1);
                        break;
                    case 1:
                        backTracking(num - number[index], index + 1);
                        break;
                    case 2:
                        backTracking(num * number[index], index + 1);
                        break;
                    case 3:
                        backTracking(num / number[index], index + 1);
                        break;
                }
                operator[i]++;
            }
        }
    }
}
