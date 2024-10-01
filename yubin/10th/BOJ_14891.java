import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14891 {
    /**
     * 톱니바퀴
     * 풀이: 구현
     *
     * 1. 인풋케이스 받는다.(톱니바퀴 정보 int[4][8], 회전 횟수 K, 회전 정보 int[K][2](번호 -1로 저장하기))
     * 2. 톱니바퀴 1개 회전 함수(톱니바퀴 번호, 회전 방향)`
     *      2.1 회전 방향이 반시계(-1)이면,
     *              2.1.1 first = arr[번호][0] 저장
     *              2.1.2 1부터 시작해서 7까지 뒤에꺼 앞에 저장
     *              2.1.3 arr[번호][7] = fisrt 저장
     *      2.2 회전 방향이 시계(1)이면,
     *              2.2.1 end = arr[번호][7] 저장
     *              2.2.2 0부터 6까지 for 반복문 돌면서 앞에꺼 뒤에 저장
     *              2.2.3 arr[번호][0] = end 저장
     * 3. 회전 함수(톱니바퀴 번호 - num, 턴 방향 - direction)
     *      3.0 leftNum = num, rightNum = num
     *      3.1 왼쪽확인(while(true))
     *          3.1.0 만약 leftnum == 0인 경우 break
     *          3.1.1 arr[leftnum][6] != arr[leftnum-1][2]라면, leftnum--;
     *          3.1.2 같지 않다면 break
     *      3.2 오른쪽확인(while(true))
     *          3.2.0 만약 rightnum == 3인 경우 break
     *          3.2.1 arr[right][2] != arr[rightnum+1][6]라면, rightnum--;
     *          3.2.2 같지 않다면 break
     *      3.3 num 번호 회전시키기
     *      3.4 leftnum < num이라면, leftnum까지 회전시키기(회전방향 고려)
     *      3.5 rightnum > num이라면, rightnum까지 회전시키기(회전방향 고려)
     * 4. K번 회전 시키기
     * 5. 네 톱니바퀴 점수의 합 출력(0번이 1인것, 1,2,4,8)
     */

    static BufferedReader br;
    static StringTokenizer st;
    static int[][] arr;
    static int K;
    static int[][] turnInfo;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        //1. 인풋케이스 받는다.(톱니바퀴 정보 int[4][8], 회전 횟수 K, 회전 정보 int[K][2](번호 -1로 저장하기))
        arr = new int[4][8];
        for (int i = 0; i < 4; i++) {
            String[] tmp = br.readLine().split("");
            for (int j = 0; j < 8; j++) {
                arr[i][j] = Integer.parseInt(tmp[j]);
            }
        }

        K = Integer.parseInt(br.readLine());
        turnInfo = new int[K][2];  //0: 톱니바퀴 번호, 1: 회전 방향
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            turnInfo[i][0] = Integer.parseInt(st.nextToken()) - 1;
            turnInfo[i][1] = Integer.parseInt(st.nextToken());
        }

        //4. K번 회전 시키기
        for (int i = 0; i < K; i++) {
            turnFunc(turnInfo[i][0], turnInfo[i][1]);
        }

        //5. 네 톱니바퀴 점수의 합 출력(0번이 1인것, 1,2,4,8)
        int result = 0;
        for (int i = 0; i < 4; i++) {
            if(arr[i][0] == 1){
                result += (int) Math.pow(2, i);
            }
        }
        System.out.println(result);
    }

    //2. 톱니바퀴 1개 회전 함수(톱니바퀴 번호, 회전 방향)
    static void turnEach(int num, int direction){
        //2.1 회전 방향이 반시계(-1)이면,
        if(direction == -1){
            //2.1.1 first = arr[번호][0] 저장
            int first = arr[num][0];
            //2.1.2 1부터 시작해서 7까지 뒤에꺼 앞에 저장
            for (int i = 1; i <= 7; i++) {
                arr[num][i-1] = arr[num][i];
            }
            //2.1.3 arr[번호][7] = fisrt 저장
            arr[num][7] = first;
        }else{
            //2.2 회전 방향이 시계(1)이면,
            //2.2.1 end = arr[번호][7] 저장
            int end = arr[num][7];
            //2.2.2 6부터 0까지 for 반복문 돌면서 앞에꺼 뒤에 저장
            for (int i = 6; i >= 0; i--) {
                arr[num][i+1] = arr[num][i];
            }
            //2.2.3 arr[번호][0] = end 저장
            arr[num][0] = end;
        }
    }

    //3. 회전 함수(톱니바퀴 번호 - num, 턴 방향 - direction)
    static void turnFunc(int num, int direction){
        //3.0 왼쪽가능번호, 오른쪽가능번호= K 로 저장
        int leftNum =  num;
        int rightNum = num;

        //3.1 왼쪽확인(while(true))
        while(true){
            //3.1.0 만약 leftnum == 0인 경우 break
            if(leftNum == 0) break;
            //3.1.1 arr[leftnum][6] != arr[leftnum-1][2]라면, leftnum--;
            if(arr[leftNum][6] != arr[leftNum-1][2]) leftNum--;
            //3.1.2 같지 않다면 break
            else break;
        }

        //3.2 오른쪽확인(while(true))
        while(true){
            //3.2.0 만약 rightnum == 3인 경우 break
            if(rightNum == 3) break;
            //3.2.1 arr[right][2] != arr[rightnum+1][6]라면, rightnum--;
            if(arr[rightNum][2] != arr[rightNum+1][6]) rightNum++;
            //3.2.2 같지 않다면 break
            else break;
        }

        //3.3 num 번호 회전시키기
        turnEach(num, direction);
        //3.4 leftnum < num이라면, leftnum까지 회전시키기(회전방향 고려)
        int leftDirection = direction;
        if(leftNum < num){
            for (int i = num-1; i >= leftNum; i--) {
                leftDirection = leftDirection == -1 ? 1 : -1;
                turnEach(i, leftDirection);
            }
        }

        //3.5 rightnum > num이라면, rightnum까지 회전시키기(회전방향 고려)
        int rightDirection = direction;
        if(rightNum > num){
            for (int i = num + 1; i <= rightNum; i++) {
                rightDirection = rightDirection == -1 ? 1 : -1;
                 turnEach(i, rightDirection);
            }
        }
    }
}
