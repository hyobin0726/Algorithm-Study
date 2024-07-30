import java.util.*;
import java.io.*;

public class BOJ_2961 {
    static int n;
    static List<int[]> arr = new ArrayList<>();
    static List<int[]> temp = new ArrayList<>();
    static List<Integer> result = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n= Integer.parseInt(br.readLine());

        for(int i =0 ; i<n; i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            int x =Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr.add(new int[] {x,y});
        }
        for (int i=1; i< n+1;i++){
            dfs(0,i);
        }
        System.out.println(Collections.min(result));
    }
    static void dfs(int start,int count){
        if (count == temp.size()){
            taste(temp);
            return;
        }
        for (int i = start; i<arr.size();i++){
            if (!temp.contains(arr.get(i))){
                temp.add(arr.get(i));
                dfs(i+1,count);
                temp.remove(arr.get(i));
            }
        }

    }
    static void taste(List<int[]> taste){
        int x =1;
        int y =0;

        for (int[] t : taste){
            x*=t[0];
            y+=t[1];
        }
        result.add(Math.abs(x-y));

    }


}
