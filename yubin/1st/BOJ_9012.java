import java.io.*;
import java.util.*;

public class BOJ_9012 {

    static int N;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            Stack<String> stack = new Stack<>();
            String[] str = br.readLine().split("");
            int cnt = str.length;
            for (int j = 0; j < cnt; j++) {
                if(stack.isEmpty()){
                    stack.push(str[j]);
                } else if (str[j].equals("(")) {
                    stack.push("(");
                } else if (str[j].equals(")")) {
                    if(stack.lastElement().equals("(")){
                        stack.pop();

                    }else{
                        stack.push("(");
                    }
                }
            }
            if(stack.isEmpty()) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
        }
        System.out.println(sb);
    }


}