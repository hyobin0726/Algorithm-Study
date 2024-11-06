import java.io.*;
import java.util.*;
public class Main {
    static int n,m,r;
    static int[] item;
    static class Node implements Comparable<Node>{
        int end, dis;

        public Node(int end, int dis) {
            this.end = end;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o) {
            return dis-o.dis;
        }
    }
    static ArrayList<ArrayList<Node>> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n= Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        r=Integer.parseInt(st.nextToken());

        item=new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++){
            item[i] =Integer.parseInt(st.nextToken());
        }
        list = new ArrayList<>();
        for(int i=0; i<=n;i++){
            list.add(new ArrayList<>());
        }
        for(int i=0;i<r;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            list.get(s).add(new Node(e,d));
            list.get(e).add(new Node(s,d));

        }
        int[][] mintime = new int[n+1][n+1];
        for(int i =1; i<=n;i++){
            boolean[] visited = new boolean[n+1];
            for(int j=1;j<=n;j++){
                mintime[i][j] = Integer.MAX_VALUE;
            }
            mintime[i][i] = 0;
            PriorityQueue<Node> q = new PriorityQueue<>();
            q.add(new Node(i,0));

            while (!q.isEmpty()){
                Node nowNode = q.poll();
                int now = nowNode.end;

                if(visited[now] == true) continue;
                visited[now] = true;

                for(int k=0; k<list.get(now).size(); k++){
                    Node adNode = list.get(now).get(k);

                    if(mintime[i][adNode.end] > mintime[i][now] + adNode.dis){
                        mintime[i][adNode.end]= mintime[i][now] + adNode.dis;
                        q.add(new Node(adNode.end, mintime[i][adNode.end]));
                    }
                }
            }
        }
    
        int max=0;
        for(int i=1; i<=n; i++){
            int temp =0;
            for(int j=1; j<=n; j++) {
            if(mintime[i][j]<=m){
                temp+= item[j];
                }
            }
            if(max<temp){
                max=temp;
            }

        }
        System.out.println(max);

    }
}
