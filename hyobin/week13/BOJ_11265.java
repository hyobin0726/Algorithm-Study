import java.util.*;
import java.io.*;
public class Main {
    static int n,m;
    static int[][] time;
    static int[][] mintime;
    static class d {
        int end, cost;

        public d(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        time=new int[n+1][n+1];
        mintime = new int[n+1][n+1];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=n;j++){
                time[i][j]= Integer.parseInt(st.nextToken());
            }
        }

        ArrayList<ArrayList<d>> de =new ArrayList<>();
        for(int i=0; i<=n;i++){
            de.add(new ArrayList<>());
        }
        for(int i=1; i<=n;i++){
            for(int j =1; j<=n;j++){
                if(i==j) continue;
                de.get(i).add(new d(j,time[i][j]));
            }
        }
//        System.out.println(de.get(1));

        for(int i=1; i<=n;i++){
            boolean[] visited = new boolean[n+1];
            for(int j=1;j<=n;j++){
                mintime[i][j] = Integer.MAX_VALUE;
            }
            mintime[i][i] = 0;

            for(int j=1;j<=n;j++){
                int nowtime = Integer.MAX_VALUE;
                int idx =0;

                for(int k=1;k<=n;k++){
                    if(!visited[k] && mintime[i][k]<nowtime){
                        nowtime= mintime[i][k];
                        idx=k;
                    }
                }
                visited[idx] = true;

                for(int k=0; k< de.get(idx).size();k++){
                    d adNode = de.get(idx).get(k);

                    if(mintime[i][adNode.end]> mintime[i][idx] + adNode.cost ){
                        mintime[i][adNode.end]= mintime[i][idx] + adNode.cost;
                    }
                }
            }
        }


        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(mintime[s][e]<=c){
                System.out.println("Enjoy other party");
            }else {
                System.out.println("Stay here");
            }

        }


    }
}
