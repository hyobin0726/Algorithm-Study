import java.io.*;

import java.util.*;

public class BOJ_1966 {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int test = Integer.parseInt(br.readLine());

        for (int t =0; t<test; t++ ) {

            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            LinkedList<int[]> q= new LinkedList<>();

            for (int i =0; i<n; i++) {
                q.offer(new int[] {i,Integer.parseInt(st.nextToken())});
            }

            int ans = 0;

            while (!q.isEmpty()) {

                int[] value = q.poll();

                int idx = value[0];
                int val = value[1];
                boolean isMax = true;

                for (int i=0; i<q.size(); i++) {

                    if (val < q.get(i)[1]) {
                        q.offer(value);
                        isMax = false;
                        break;
                    }
                }
                if (!isMax) {
                    continue;
                }

                ans+=1;
                if(idx == m) {
                    System.out.println(ans);
                    break;
                }
            }
        }
    }
}