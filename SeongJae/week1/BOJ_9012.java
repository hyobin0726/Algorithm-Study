import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int TC=0; TC < T; TC++) {
            String s = br.readLine();
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
                //
                if (s.charAt(i) == '(') {
                    count += 1;
                } else {
                    count -= 1;
                }
                if (count < 0) {
                    break;
                }
            }
            if (count == 0) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
    }
}