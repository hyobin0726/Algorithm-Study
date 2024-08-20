import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class SEA_2112 {
 
    /**
     *
     *  1. 테스트 케이스 개수를 입력받는다.
     *  2. 각 테스트 케이스마다,
     *      2-1. 필름의 가로, 세로, 통과 기준을 입력받는다.
     *      2-2. 필름의 정보를 입력받는다.
     *
     *  3. 필름의 각 층을 확인한다.
     *      [현재 Depth의 상태에서 3가지 상태 - 가지를 뻗어나간다.]
     *      3-1. 통과 기준을 만족하는지 확인한다.
     *           3-1-1. 각 열마다 통과기준을 만족하는지 확인한다.
     *                  만족하지 않으면 더 이상 볼 필요가 없다.
     *      3-2. 이미 최소 약품 투약 횟수보다 더 약품 투약 횟수가 많으면 진행할 필요가 없다.
     *      3-3. 필름의 끝에 도달한 경우 더 이상 진행할 수 없다.
     *      3-4. 약품을 투입하지 않고 다음 필름으로 넘어간다.
     *      3-5. A 약품을 투입하고 다음 필름으로 넘어간다.
     *      3-6. B 약품을 투입하고 다음 필름으로 넘어간다.
     *      3-7. 약품을 투입했었다면 다시 되돌려준다.
     *
     *  4. 최소 약품 투약 횟수를 출력한다.
     *
     */
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int TC;
 
    public static void main(String[] args) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
 
        //1. 테스트 케이스 개수를 입력받는다.
        TC = Integer.parseInt(br.readLine().trim());
 
        for (int t = 1; t <= TC; t++) {
            //2. 각 테스트 케이스마다,
            inputCase();
 
            //3. 필름의 각 층을 확인한다.
            checkFilm(0,0);
 
            //4. 최소 약품 투약 횟수를 출력한다.
            sb.append("#").append(t).append(" ").append(minInjectCount).append("\n");
        }
 
        System.out.println(sb);
    }
    static int rowSize;
    static int columnSize;
    static int passSize;
    static int[][] film;
 
    public static void inputCase() throws Exception{
 
        //2-1. 필름의 가로, 세로, 통과 기준을 입력받는다.
        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        columnSize = Integer.parseInt(st.nextToken());
        passSize = Integer.parseInt(st.nextToken());
 
        //2-2. 필름의 정보를 입력받는다.
        film = new int[rowSize][columnSize];
        for (int rowIndex = 0; rowIndex < rowSize; rowIndex++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
                film[rowIndex][columnIndex] = Integer.parseInt(st.nextToken());
            }
        }
 
        minInjectCount = Integer.MAX_VALUE;
    }
 
    static int minInjectCount;
    static final int INJECT_A = 0, INJECT_B = 1;
    public static void checkFilm(int currentRowIndex, int currentInjectionCount){
        //3-1. 통과 기준을 만족하는지 확인한다.
        if(isPass()){
            minInjectCount = Math.min(minInjectCount, currentInjectionCount);
            return;
        }
 
        //3-2. 이미 최소 약품 투약 횟수보다 더 약품 투약 횟수가 많으면 진행할 필요가 없다.
        if(currentInjectionCount > minInjectCount){
            return;
        }
 
        // 3-3. 필름의 끝에 도달한 경우 더 이상 진행할 수 없다.
        if(currentRowIndex == rowSize){
            return;
        }
 
        //3-4. 약품을 투입하지 않고 다음 필름으로 넘어간다.
        checkFilm(currentRowIndex + 1, currentInjectionCount);
 
        //3-5. A 약품을 투입하고 다음 필름으로 넘어간다.
        //원래 상태 저장해야함
        int[] originalFilm = new int[columnSize];
        for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
            originalFilm[columnIndex] = film[currentRowIndex][columnIndex];
        }
        //A 약품 투여
        for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
            film[currentRowIndex][columnIndex] = INJECT_A;
        }
        checkFilm(currentRowIndex + 1, currentInjectionCount+1);
 
        //3-6. B 약품을 투입하고 다음 필름으로 넘어간다.
        //B 약품 투여
        for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
            film[currentRowIndex][columnIndex] = INJECT_B;
        }
        checkFilm(currentRowIndex + 1, currentInjectionCount+1);
 
        //3-7. 약품을 투입했었다면 다시 되돌려준다.
        for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
            film[currentRowIndex][columnIndex] = originalFilm[columnIndex];
        }
    }
 
    public static boolean isPass(){
        //3-1-1. 각 열마다 통과기준을 만족하는지 확인한다.
        //만족하지 않으면 더 이상 볼 필요가 없다.
        for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
            boolean pass = false;
 
            int sameCount = 1;
            for (int rowIndex = 1; rowIndex < rowSize; rowIndex++) {
 
                //이전 필름과 같으면 sameCount++
                if(film[rowIndex][columnIndex] == film[rowIndex - 1][columnIndex]){
                    sameCount++;
                }
                //이전 필름과 다르면 카운트 다시 시작
                else{
                    sameCount = 1;
                }
 
                if(sameCount >= passSize) {
                    pass = true;
                    break;
                }
            }
 
            //각 필름 반복문중에 통과못하는게 있으면 바로 false 반환
            if(!pass){
                return false;
            }
        }
 
        //다 통과하면 true 반환
        return true;
    }
}
