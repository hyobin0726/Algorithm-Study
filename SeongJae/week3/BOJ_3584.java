import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_3584 {
    public static void main(String[] args) throws NumberFormatException, IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int T = Integer.parseInt(br.readLine());
        
        for (int TC = 0; TC < T; TC++) {
            int n = Integer.parseInt(br.readLine());
            int[] graph = new int[n + 1];
            
            for (int i = 0; i < n - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[b] = a;
            }
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            ArrayList<Integer> p1dst = new ArrayList<>();
            ArrayList<Integer> p2dst = new ArrayList<>();
            
            int dst = p1;
            while (dst != 0) {
                p1dst.add(dst);
                dst = graph[dst];
            }
            
            dst = p2;
            while (dst != 0) {
                p2dst.add(dst);
                dst = graph[dst];
            }
            
            Collections.reverse(p1dst);
            Collections.reverse(p2dst);
            
            int count = 0;
            for (int i = 0; i < Math.min(p1dst.size(), p2dst.size()); i++) {
                if (p1dst.get(i).equals(p2dst.get(i))) {
                    count++;
                } else {
                    break;
                }
            }
            
            System.out.println(p1dst.get(count - 1));
        }
    }
}
