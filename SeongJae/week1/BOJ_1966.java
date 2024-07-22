import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;
class CustomInteger{
    private int num;
    private int important;

    public CustomInteger(int num,int important){
        this.num=num;
        this.important=important;
    }
    public int getNum(){
        return num;
    }
    public int getImportant(){
        return important;
    }
}

public class BOJ_1966{
    public static <E> void rotate(LinkedList<E> list, int k) {
        if (list.isEmpty() || k == 0) {
            return; // 회전할 필요가 없는 경우 또는 빈 리스트인 경우 처리
        }

        int size = list.size();
        k = k % size; // k가 리스트 크기보다 큰 경우 회전 횟수를 리스트 크기로 나눈 나머지로 조정

        // 왼쪽으로 k번 회전
        if (k > 0) {
            for (int i = 0; i < k; i++) {
                E first = list.removeFirst();
                list.addLast(first);
            }
        }
        // 오른쪽으로 회전
        else {
            for (int i = 0; i > k; i--) {
                E last = list.removeLast();
                list.addFirst(last);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int repeatTime=Integer.parseInt(br.readLine());
        for(int test=0;test<repeatTime;test++){
            LinkedList<CustomInteger> linkedList = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n=Integer.parseInt(st.nextToken());
            int m=Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<n;i++){// 배열 입력
                if(i==m){linkedList.add(new CustomInteger(Integer.parseInt(st.nextToken()), 1));}
                else {linkedList.add(new CustomInteger(Integer.parseInt(st.nextToken()), 0));}
            }
            int count=0;
            while(true){
            //배열출력
                int max=0;
                for(CustomInteger customInteger:linkedList){
                    int num=customInteger.getNum();
                    if(max<num){
                        max=num;
                    }
                }
                if(max==linkedList.get(0).getNum()){
                    count++;
                    if(linkedList.get(0).getImportant()==1){
                        pw.println(count);break;
                    }
                    else{linkedList.removeFirst();}
                }
                else{
                    rotate(linkedList, 1);
                }
            }
        }
        pw.flush();
        pw.close();
    }
}