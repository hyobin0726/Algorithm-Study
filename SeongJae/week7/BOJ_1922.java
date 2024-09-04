import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1922 {
        static int[] parents;
        public static void main(String[] args) throws  IOException{
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            int n=Integer.parseInt(br.readLine());
            int m=Integer.parseInt(br.readLine());
            parents=new int [n];
            for (int i=0;i<n;i++){
                parents[i]=-1;
            }
            int[][] arr=new int[m][3];
            for (int i=0;i<m;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                arr[i][0]=Integer.parseInt(st.nextToken());
                arr[i][1]=Integer.parseInt(st.nextToken());
                arr[i][2]=Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr,(o1,o2)->{
                return o1[2]-o2[2];
            });
            int count=1;
            int result=0;
            for(int i=0;i<m;i++){
                if(union(arr[i][0]-1, arr[i][1]-1)){
                        count++;
                        result+=arr[i][2];
                }
                if(count==n){
                        System.out.println(result);
                        break;
                }
            }
        }

        public static int findSet(int a){
                if(parents[a]<0){
                        return a;
                }
                return parents[a]=findSet(parents[a]);
        }

        public static boolean union(int a,int b){
                int aRoot=findSet(a);
                int bRoot=findSet(b);
                if(aRoot==bRoot){
                        return  false;
                }
                parents[aRoot]+=parents[bRoot];
                parents[bRoot]=aRoot;
                return true;
        }
}
