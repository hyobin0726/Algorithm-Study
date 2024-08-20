import java.util.*;
import java.io.*;
public class BOJ_10799 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String[] str = br.readLine().split("");

        Stack<Character> stack = new Stack<>();
        int answer = 0;

        for (int i=0; i<str.length;i++) {
            if (str[i].equals("(")) {
                stack.push('(');

            }else {
                if (str[i-1].equals("(")) {
                    stack.pop();
                    answer+= stack.size();
                }else {
                    answer+=1;
                    stack.pop();
                }
            }
        }
        System.out.println(answer);

    }
}
