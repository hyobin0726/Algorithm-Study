import java.io.*;
import java.util.*;
public class BOJ_1197 {
    // Kruskal's algorithm 그 자체
    static int v;
    static int e;
    static int[] parents;
    static void make(){
        parents = new int[v+1];
        for(int i=0; i<=v; i++){
            parents[i] = -1;
        }
    }
    static int find(int a){
        if(parents[a]<0) return a;
        return parents[a] = find(parents[a]);

    }
    static boolean union (int a, int b){
        int aroot = find(a);
        int broot = find(b);

        if(aroot == broot) return false;
        parents[aroot] += parents[broot];
        parents[broot] = aroot;
        return true;
    }


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e =  Integer.parseInt(st.nextToken());

        edge[] edges = new edge[e];

        for (int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            edges[i] = new edge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(edges);
        make();
        int cnt =0;
        int cost = 0;
        for(edge i : edges){
            if(union(i.start, i.end)){
                cost+=i.weight;
                ++cnt;
            }

            if(cnt == v-1) break;;
        }
        System.out.println(cost);
    }
    static class edge implements Comparable<edge>{
        int start,end, weight;

        public edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;

        }

        @Override
        public int compareTo(edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

}
