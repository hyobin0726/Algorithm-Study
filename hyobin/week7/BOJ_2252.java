import java.util.*;
import java.io.*;
public class BOJ_2252 {
    //위상정렬
    static int n;
    static int m;
    static ArrayList<Integer>[] graph;
    //진입차수
    static int[] indegree;
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        indegree = new int[n+1];
        for (int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }

        for (int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            //a -> b
            graph[a].add(b);
            //b의 진입차수 증가
            indegree[b]++;

        }
        for(int i=1; i<=n;i++){
            //진입차수가 0인 노드를 큐에 삽입
            if(indegree[i]==0){
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            int temp = q.poll();
            System.out.print(temp + " ");
            for(int i : graph[temp]){
                //진입차수 감소
                indegree[i]--;
                if(indegree[i]==0){
                    q.add(i);
                }
            }
        }
    }

}
