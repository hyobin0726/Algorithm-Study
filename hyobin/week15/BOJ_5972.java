package BOJ.다익스트라;

import java.io.*;
import java.util.*;
public class 택배배송 {
    static int n,m;
    static class Node implements Comparable<Node>{
        int end, cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return cost-o.cost;
        }
    }
    static ArrayList<ArrayList<Node>> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n= Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        for(int i=0; i<=n; i++){
            list.add(new ArrayList<>());
        }
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int s= Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.get(s).add(new Node(e,c));
            list.get(e).add(new Node(s,c));
        }

        int[] mincost = new int[n+1];
        boolean[] visited = new boolean[n+1];
        for(int i=1; i<=n;i++){
            mincost[i] = Integer.MAX_VALUE;
        }
        mincost[1] =0;
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(1,0));

        while (!q.isEmpty()){
            Node nowNode = q.poll();
            int now = nowNode.end;

            if(visited[now]) continue;
            visited[now] = true;

            for(int i=0; i<list.get(now).size();i++){
                Node adNode = list.get(now).get(i);
                if(mincost[adNode.end] > mincost[now] + adNode.cost){
                    mincost[adNode.end] = mincost[now] + adNode.cost;
                    q.add(new Node(adNode.end, mincost[adNode.end]));
                }
            }

        }
//        System.out.println(Arrays.toString(mincost));

        System.out.println(mincost[n]);
    }
}
