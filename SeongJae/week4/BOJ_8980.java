import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_8980{
    public static void main(String[] args) throws  IOException{

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int c=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(br.readLine());
        List<int[]> arr = new ArrayList<>();
        for (int idx = 0; idx < m; idx++) {
            st=new StringTokenizer(br.readLine());
            int start=Integer.parseInt(st.nextToken());
            int end=Integer.parseInt(st.nextToken());
            int weight=Integer.parseInt(st.nextToken());
            arr.add(new int[]{start,end,weight});
            
        }
        arr.sort((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });
        int result=0;
        int arrVisitIndex=0;
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        for (int villageNum = 1; villageNum <= n; villageNum++) {
            // 3. 목적지에 도착한 물건 처리
            while (!queue.isEmpty() && queue.peek()[0] == villageNum) {
                result += queue.poll()[1];
            }
            
            // 2. 물건을 담는다
            while (arrVisitIndex < arr.size() && arr.get(arrVisitIndex)[0] == villageNum) {
                queue.addLast(new int[]{arr.get(arrVisitIndex)[1], arr.get(arrVisitIndex)[2]});
                arrVisitIndex++;
            }

            // 큐를 목적지 기준으로 정렬
            List<int[]> tmp = new ArrayList<>(queue);
            tmp.sort(Comparator.comparingInt(a -> a[0]));
            queue.clear();

            int deliverCost = 0;
            // 무게 제한에 맞게 물건을 다시 큐에 담는다
            for (int[] item : tmp) {
                int dest = item[0];
                int weight = item[1];
                
                if (deliverCost + weight >= c) {
                    queue.addLast(new int[]{dest, c - deliverCost});
                    break;
                } else {
                    queue.addLast(new int[]{dest, weight});
                    deliverCost += weight;
                }
            }
        }

        System.out.println(result);
    }
}