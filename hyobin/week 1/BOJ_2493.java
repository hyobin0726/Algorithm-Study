import java.io.*;

import java.util.*;

public class BOJ_2493 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Stack<int[]> stack = new Stack<>();

        int n = Integer.parseInt(br.readLine());
        int[] input = new int[n];
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<n; i++) {
            input[i]=(Integer.parseInt(st.nextToken()));
        }

        for (int i=0; i<n; i++) {
            while(!stack.isEmpty()){
                if(stack.peek()[1]>input[i]){
                    System.out.print((stack.peek()[0])+" ");
                    break;
                }
                stack.pop();
            }
            if (stack.isEmpty()){
                System.out.print("0 ");
            }
            stack.push(new int[] {i+1, input[i]});
        }
    }
}