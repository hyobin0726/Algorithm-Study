import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_ID = 100005;
    static final int COLOR_MAX = 5;

    static class Node {
        int id, color, lastUpdate, maxDepth, parentId;
        ArrayList<Integer> childIds = new ArrayList<>();
    }

    // 점수 조회 명령 구현
    static class ColorCount {
        int[] cnt = new int[COLOR_MAX + 1];

        ColorCount add(ColorCount obj) {
            ColorCount res = new ColorCount();
            for (int i = 1; i <= COLOR_MAX; i++) {
                res.cnt[i] = this.cnt[i] + obj.cnt[i];
            }
            return res;
        }

        int score() {
            int result = 0;
            for (int i = 1; i <= COLOR_MAX; i++) {
                if (this.cnt[i] > 0) result++;
            }
            return result * result;
        }
    }

    static Node[] nodes = new Node[MAX_ID];
    static boolean[] isRoot = new boolean[MAX_ID];

    static {
        for (int i = 0; i < MAX_ID; i++) {
            nodes[i] = new Node();
        }
    }

    // 해당 node가 자식노드를 가질 수 있는지 확인
    static boolean canMakeChild(Node curr, int needDepth) {
        if (curr.id == 0) return true;
        if (curr.maxDepth <= needDepth) return false;
        return canMakeChild(nodes[curr.parentId], needDepth + 1);
    }

    // curr 노드의 색깔 정보와 해당 색깔이 설정된 시간을 return
    static int[] getColor(Node curr) {
        if (curr.id == 0) return new int[] { 0, 0 };
        int[] info = getColor(nodes[curr.parentId]);
        if (info[1] > curr.lastUpdate) return info;
        else return new int[] { curr.color, curr.lastUpdate };
    }

    static Object[] getBeauty(Node curr, int color, int lastUpdate) {
        // root에서부터 내려온 색 정보보다 현재 노드의 색정보가 최신이라면 갱신
        if (lastUpdate < curr.lastUpdate) {
            lastUpdate = curr.lastUpdate;
            color = curr.color;
        }
        int result = 0;
        ColorCount colorCount = new ColorCount();
        colorCount.cnt[color] = 1;
        for (int childId : curr.childIds) {
            Node child = nodes[childId];
            Object[] subResult = getBeauty(child, color, lastUpdate);
            colorCount = colorCount.add((ColorCount) subResult[1]);
            result += (Integer) subResult[0];
        }
        result += colorCount.score();
        return new Object[] { result, colorCount };
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int Q = Integer.parseInt(br.readLine());
        for (int i = 1; i <= Q; i++) {
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            if (T == 100) {
                int mId = Integer.parseInt(st.nextToken());
                int pId = Integer.parseInt(st.nextToken());
                int color = Integer.parseInt(st.nextToken());
                int maxDepth = Integer.parseInt(st.nextToken());

                if (pId == -1) isRoot[mId] = true;

                if (isRoot[mId] || canMakeChild(nodes[pId], 1)) {
                    nodes[mId].id = mId;
                    nodes[mId].color = color;
                    nodes[mId].maxDepth = maxDepth;
                    nodes[mId].parentId = isRoot[mId] ? 0 : pId;
                    nodes[mId].lastUpdate = i;
                    if (!isRoot[mId]) {
                        nodes[pId].childIds.add(mId);
                    }
                }
            } else if (T == 200) {
                int mId = Integer.parseInt(st.nextToken());
                int color = Integer.parseInt(st.nextToken());

                // 시간복잡도를 위하여 색깔 변화 명령에 대해 모두 갱신하는 것이 아닌, 조회 명령에서 lazy한 계산 형태
                nodes[mId].color = color;
                nodes[mId].lastUpdate = i;
            } else if (T == 300) {
                int mId = Integer.parseInt(st.nextToken());
                System.out.println(getColor(nodes[mId])[0]);
            } else if (T == 400) {
                int beauty = 0;
                for (int idx = 1; idx < MAX_ID; idx++) {
                    if (isRoot[idx]) {
                        beauty += (Integer) getBeauty(nodes[idx], nodes[idx].color, nodes[idx].lastUpdate)[0];
                    }
                }
                System.out.println(beauty);
            }
        }
    }
}
