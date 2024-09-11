import java.io.*;
import java.util.*;
public class BOJ_1922 {
    // Kruskal's algorithm
    static int n;
    static int m;
    static int[] parents;

    static void make(){
        parents = new int[n+1];
        for (int i=0; i<=n; i++){
            parents[i] = -1;
        }
    }
    static int find (int a){
        if(parents[a]<0) return a;
        return parents[a] = find(parents[a]);
    }
    static boolean union (int a, int b){
        int aroot = find(a);
        int broot = find(b);

        if(aroot==broot) return false;
        parents[aroot] += parents[broot];
        parents[broot] = aroot;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        Edge[] edges = new Edge[m];
        for (int i=0; i<m;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(edges);
        make();

        int cnt = 0;
        int cost = 0;
        for(Edge temp : edges){
            if(union(temp.start, temp.end)){
                cost += temp.weight;
                ++cnt;
            }
            if(cnt == n-1) break;
        }
        System.out.println(cost);
    }
    static class Edge implements Comparable<Edge>{
        int start, end, weight;
        public Edge(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

}
