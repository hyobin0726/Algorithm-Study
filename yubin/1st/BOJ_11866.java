package test;

import java.io.*;
import java.util.*;

public class BOJ_11866 {

    public static int N;
    public static int K;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N ; i++) {
            queue.add(i);
        }
        sb.append('<');

        while (queue.size() > 1){
            for (int i = 0; i < K - 1; i++) {
                queue.offer(queue.poll());
            }

            sb.append(queue.poll()).append(", ");
        }

        sb.append(queue.poll()).append('>');
        System.out.println(sb);
    }

}
