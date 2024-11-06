import java.io.*;
import java.util.*;
public class Main {
    static class date implements Comparable<date>{
        int s,e,interval;

        public date(int s, int e, int interval) {
            this.s = s;
            this.e = e;
            this.interval = interval;
        }

        @Override
        public int compareTo(date o) {
            return o.interval-this.interval;
        }
    }

    static int n;
    static List<date> list;
    static int[] ischeck;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n= Integer.parseInt(br.readLine());
        list = new LinkedList<>();
        int max=0;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int s= Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list.add(new date(s,e,(e-s +1)));
            if(max<e){
                max=e;
            }
        }
        Collections.sort(list);
        ischeck= new int[max+2];

        for(int i=0; i<list.size();i++){
            int start=list.get(i).s;
            int end = list.get(i).e;
            int interval = list.get(i).interval;
            record(start,end);
        }

        int result=0;
        int cnt=0;
        int temp=0;

        for(int j=1;j<=max+1; j++){

            if(ischeck[j] != 0){
                cnt++;
                if(temp<ischeck[j]){
                    temp =ischeck[j];
                }
            }
            else {
                result += (temp*cnt);
                cnt=0;
                temp=0;
            }
        }
        System.out.println(result);



    }

    static void record( int s, int e){
        for(int i=s;i<=e;i++){
            ischeck[i] +=1;
        }
    }

}
