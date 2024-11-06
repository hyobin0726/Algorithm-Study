import java.util.*;
import java.io.*;

public class Main {
    static int L, C;
    static String[] arr;
    static boolean[] visit;
    static String mo_li = "aeiou";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new String[C];
        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken();
        }
        Arrays.sort(arr);

        visit = new boolean[C];
        password(new ArrayList<>(), 0, 0, 0);
    }

    public static void password(ArrayList<String> li, int mo, int za, int idx) {
        if (li.size() == L) {
            if (mo >= 1 && za >= 2) {
                for (int i = 0; i < L; i++) {
                    System.out.print(li.get(i));
                }
                System.out.println();
            }
            return;
        }

        for (int i = idx; i < C; i++) {
            if (!visit[i]) {
                visit[i] = true;
                li.add(arr[i]);
                if (mo_li.contains(arr[i])) {
                    password(li, mo + 1, za, i + 1);
                } else {
                    password(li, mo, za + 1, i + 1);
                }
                visit[i] = false;
                li.remove(li.size() - 1);
            }
        }
    }
}
