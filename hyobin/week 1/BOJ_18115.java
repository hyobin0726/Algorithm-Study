import java.io.*;
import java.util.*;

public class BOJ_18115  {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        String[] input = br.readLine().split(" ");

        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        for (int i = 1; i < n+1; i++) {
            int x = arr[n-i];
            if (x == 1 ){
                deque.offerFirst(i);
            }else if (x ==2){
                int y = deque.removeFirst();
                deque.offerFirst(i);
                deque.offerFirst(y);
            }else if (x == 3){
                deque.offerLast(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (deque.size() != 0) {
            sb.append(deque.removeFirst() + " ");
        }

        // 결과 출력
        System.out.println(sb);
    }
}