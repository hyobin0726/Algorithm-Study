package BOJ.이분탐색;
import java.io.*;
public class 정수제곱근 {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        long start=0;
        long end = n;
        long mid ;
        long answer=0;
        while (start<=end){
            mid = (start+end)/2;

            if(n == Math.pow(mid, 2)){
                System.out.println(mid);
                return;
            }
            else if(n < Math.pow(mid, 2) ){
                answer =mid;
                end =mid-1;
            }else {
                start = mid+1;
            }
        }
        System.out.println(answer);
    }

}
