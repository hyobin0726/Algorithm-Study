import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
public class SEA_5653 {
 
    /**
     *
     *  1. 테스트 케이스 개수를 입력받는다.
     *  2. 각 테스트 케이스 마다,
     *      2-0. 각 테스트 케이스마다 계산하기 위해 살아있는 줄기세포의 수를 초기화한다.
     *           줄기세포를 배양하기 위한 배열을 생성한다. (배열의 크기는 무한대이지만, 대충 넉넉히 잡아도 된다.)
     *           줄기세포를 관리하기 위한 ArrayList를 생성한다.
     *
     *      2-1. 세로 크기(rowSize), 가로 크기(columnSize), 배양 시간(cultivateTime)을 입력받는다.
     *      2-2. 줄기세포의 상태 정보를 입력받는다.
     *          2-2-1. 줄기세포 배양을 위해 가운데에 위치할 수 있도록하자.
     *  3. 줄기세포를 배양시킨다.
     *      3-1. 줄기세포의 배양 시간을 증가시킨다. 이후, 상태 변경.
     *      3-2. 죽었거나, 아직 비활성 줄기세포는 번식할 수 없다.
     *      3-3. 활성 상태 줄기세포라면,
     *          3-3-1. 다음 위치에 줄기 세포가 없다면 번식시킨다. (새로운 줄기세포 추가)
     *          3-3-2. 다음 위치에 죽은 줄기세포가 있다면 번식할 수 없다.
     *          3-3-3. 이미 줄기세포가 존재한다면, 최초 번식된 시간이 같으면서 배양 시간이 더 크면 변경한다.
     *                 덮어씌어지는 경우는 어느 한 쪽이 배양시간이 더 큰 경우.
     *                 --> 다른 한 쪽은 이미 활성이 되어있었을 것임 (isActivated)면 번식 불가.
     *                 이후, 상태 변경.
     *  4. 살아있는(비활성 + 활성) 상태의 줄기세포의 수를 출력한다.
     *
     */
 
    static int tc;
    static BufferedReader br;
    static StringBuilder sb;
 
    public static void main(String[] args) throws Exception {
 
        //1. 테스트 케이스 개수를 입력받는다.
        br = new BufferedReader(new InputStreamReader(System.in));
 
        tc = Integer.parseInt(br.readLine().trim());
        sb = new StringBuilder();
 
        for (int i = 1; i <= tc; i++) {
//            2. 각 테스트 케이스 마다,
//            2-0. 각 테스트 케이스마다 계산하기 위해 살아있는 줄기세포의 수를 초기화한다.        }
 
            inputTestCase();
 
            for (int currentCultivateTime = 1; currentCultivateTime <= cultivateTime ; currentCultivateTime++) {
                cultivate(currentCultivateTime);
            }
 
//           4. 살아있는(비활성 + 활성) 상태의 줄기세포의 수를 출력한다.
            calculateAliveStemCellCount();
            sb.append("#").append(i).append(" ").append(aliveStemCellCount).append("\n");
        }
        System.out.println(sb);
    }
 
    static class StemCell{
        int rowIndex;
        int colIndex;
        int changeStatusTime;  //상태가 변경되는 시간 단위, K
        int status; //줄기세포 현재 상태
        int elapsedTime; //현재 상테에서 배양된 시간
        int initElapsedTime; //최초 배양된 시간
        boolean isActived;
 
        public StemCell(int rowIndex, int colIndex, int changeStatusTime, int status, int elapsedTime, int initElapsedTime) {
            this.rowIndex = rowIndex;
            this.colIndex = colIndex;
            this.changeStatusTime = changeStatusTime;
            this.status = status;
            this.elapsedTime = elapsedTime;
            this.initElapsedTime = initElapsedTime;
            this.isActived = false;
        }
    }
    static int aliveStemCellCount;
    static StemCell[][] stemCellMap;
    static final int MAX_STEMCELL_MAP_SIZE = 500;
    static final int MIDDLE_STEMCELL_MAP_INDEX = MAX_STEMCELL_MAP_SIZE >> 1;
    static final int DEAD = 0, INACTIVE = 1, ACTIVE = 2;
    static ArrayList<StemCell> stemCellList;
    static int rowSize, colSize, cultivateTime;
    static StringTokenizer st;
 
    public static void inputTestCase() throws Exception{
//         * 2-0. 각 테스트 케이스마다 계산하기 위해 살아있는 줄기세포의 수를 초기화한다.
//         *       줄기세포를 배양하기 위한 배열을 생성한다. (배열의 크기는 무한대이지만, 대충 넉넉히 잡아도 된다.)
//         *        줄기세포를 관리하기 위한 ArrayList를 생성한다.
 
//        각 테스트 케이스마다 계산하기 위해 살아있는 줄기세포의 수를 초기화한다.
        aliveStemCellCount = 0;
//        줄기세포를 배양하기 위한 배열을 생성한다. (배열의 크기는 무한대이지만, 대충 넉넉히 잡아도 된다.)
        stemCellMap = new StemCell[MAX_STEMCELL_MAP_SIZE][MAX_STEMCELL_MAP_SIZE];
//        줄기세포를 관리하기 위한 ArrayList를 생성한다.
        stemCellList = new ArrayList<>();
 
//        2-1. 세로 크기(rowSize), 가로 크기(columnSize), 배양 시간(cultivateTime)을 입력받는다.
        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        cultivateTime = Integer.parseInt(st.nextToken());
 
//        2-2. 줄기세포의 상태 정보를 입력받는다.
        for (int rowIndex = 0; rowIndex < rowSize; rowIndex++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int colIndex = 0; colIndex < colSize; colIndex++) {
                //생명력
                int cultivateTime = Integer.parseInt(st.nextToken());
 
                if(cultivateTime == 0){
                    continue;
                }
//               2-2-1. 줄기세포 배양을 위해 가운데에 위치할 수 있도록하자.
                StemCell stemCell = new StemCell(MIDDLE_STEMCELL_MAP_INDEX + rowIndex,
                        MIDDLE_STEMCELL_MAP_INDEX + colIndex,
                        cultivateTime,
                        INACTIVE,
                        0,
                        0
                );
 
                stemCellMap[MIDDLE_STEMCELL_MAP_INDEX + rowIndex][MIDDLE_STEMCELL_MAP_INDEX + colIndex] = stemCell;
                stemCellList.add(stemCell);
            }
        }
 
 
    }
 
    //상태 변경시키기
    public static void changeStatus(StemCell stemCell){
        //이미 죽은 줄기세포는 패스하기
        if(stemCell.status == DEAD){
            return;
        }
 
        //비활성 상태이면서, 경과시간이 배양시간과 동일하면 활성 상태가 된다
        if(stemCell.status == INACTIVE && stemCell.elapsedTime == stemCell.changeStatusTime) {
            stemCell.status = ACTIVE;
            stemCell.elapsedTime = 0;
            stemCell.isActived = true;
            //활성상태이면서 경과 시간이 배양시간과 동일해지면 줄기세포는 죽는다.
        } else if (stemCell.status == ACTIVE && stemCell.elapsedTime == stemCell.changeStatusTime) {
            stemCell.status = DEAD;
        }
    }
 
    static final int[] DELTA_ROW = {-1,1,0,0};
    static final int[] DELTA_COL = {0,0,-1,1};
 
    public static void cultivate(int currentCultivateTime){
        //현재 세포 갯수
        int currentStemCellCount = stemCellList.size();
 
 
        for (int stemCellIndex = 0; stemCellIndex < currentStemCellCount; stemCellIndex++) {
            StemCell stemCell = stemCellList.get(stemCellIndex);
 
            //3-1. 줄기세포의 배양 시간을 증가시킨다. 이후, 상태 변경.
            stemCell.elapsedTime++;
 
//            3-2. 죽었거나, 아직 비활성 줄기세포는 번식할 수 없다.
            if(stemCell.status == DEAD || stemCell.status == INACTIVE){
                changeStatus(stemCell);
                continue;
            }
 
//            3-3. 활성 상태 줄기세포라면,
            for (int direction = 0; direction < 4; direction++) {
                int nextRowIndex = stemCell.rowIndex + DELTA_ROW[direction];
                int nextColIndex = stemCell.colIndex + DELTA_COL[direction];
 
//              //3-3-1. 다음 위치에 줄기 세포가 없다면 번식시킨다. (새로운 줄기세포 추가)
                if(stemCellMap[nextRowIndex][nextColIndex] == null){
                    stemCellMap[nextRowIndex][nextColIndex] = new StemCell(
                            nextRowIndex,
                            nextColIndex,
                            stemCell.changeStatusTime,
                            INACTIVE,
                            0,
                            currentCultivateTime
                    );
                    stemCellList.add(stemCellMap[nextRowIndex][nextColIndex]);
                }
 
                //3-3-2. 다음 위치에 죽은 줄기세포가 있다면 번식할 수 없다.
                else if(stemCellMap[nextRowIndex][nextColIndex].status == DEAD){
                    continue;
                }
 
//                //3-3-3. 이미 줄기세포가 존재한다면, 최초 번식된 시간이 같으면서 배양 시간이 더 크면 변경한다.
                if(stemCell.initElapsedTime == stemCellMap[nextRowIndex][nextColIndex].initElapsedTime
//                      덮어씌어지는 경우는 어느 한 쪽이 배양시간이 더 큰 경우.
                        && stemCell.changeStatusTime > stemCellMap[nextRowIndex][nextColIndex].changeStatusTime
//                        --> 다른 한 쪽은 이미 활성이 되어있었을 것임 (isActivated)면 번식 불가.
                        && !stemCellMap[nextRowIndex][nextColIndex].isActived
                ){
                    stemCellMap[nextRowIndex][nextColIndex].changeStatusTime = stemCell.changeStatusTime;
                    stemCellMap[nextRowIndex][nextColIndex].status = INACTIVE;
                    stemCellMap[nextRowIndex][nextColIndex].elapsedTime = 0;
                    stemCellMap[nextRowIndex][nextColIndex].initElapsedTime = currentCultivateTime;
                }
            }
            changeStatus(stemCell);
        }
    }
 
    public static void calculateAliveStemCellCount(){
        for (StemCell stemCell :
                stemCellList) {
            if (stemCell.status != DEAD){
                aliveStemCellCount++;
            }
        }
    }
}
