import java.io.*;
import java.util.*;


public class BOJ_17073 {
    static ArrayList<Integer>[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        arr = new ArrayList[n+1];
        for (int i=0; i<= n; i++) {
            arr[i] = new ArrayList<>();

        }
        for (int i=0; i<n-1; i++) {
            st = new StringTokenizer(br.readLine());
            // 두 정점을 입력받아서 양방향으로 연결
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a].add(b);
            arr[b].add(a);

        }
        int cnt =0;
        for(int i =2; i<n+1; i++) {
            // 연결된 정점이 하나면 리프노드
            if(arr[i].size() == 1) {
                cnt+=1;
            }
        }
        System.out.println((double) w/cnt);

    }
}
