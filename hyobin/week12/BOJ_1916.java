import java.io.*;
import java.util.*;
public class Main {
    static int V,E;
    static int s,e;
    static  class Node{
        int end , cost;
        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        V= Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());


        ArrayList<ArrayList<Node>> adlist = new ArrayList<>();
        for(int i=0; i<V+1; i++){
            adlist.add(new ArrayList<>());
        }
        for(int i=0; i<E;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v= Integer.parseInt(st.nextToken());
            int w= Integer.parseInt(st.nextToken());
            adlist.get(u).add(new Node(v,w));
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[V+1];
        int[] mindistance = new int[V+1];
        for(int i =1; i<V+1; i++){
            mindistance[i] = Integer.MAX_VALUE;
        }
        mindistance[s] = 0;
//다익스트라
        for(int i=0; i<V; i++){
            int nowDisttance = Integer.MAX_VALUE;
            int idx = 0;
            for(int j =1; j<V+1; j++){
                if(!visited[j] && mindistance[j] <nowDisttance ){
                    nowDisttance = mindistance[j];
                    idx=j;
                }
            }
            visited[idx] = true;

            for(int j=0; j<adlist.get(idx).size(); j++){
                Node adNode = adlist.get(idx).get(j);

                if(mindistance[adNode.end] > mindistance[idx] + adNode.cost){
                    mindistance[adNode.end] = mindistance[idx] + adNode.cost;
                }
            }
        }

        //최종 출력
        System.out.println(mindistance[e]);

    }
}
