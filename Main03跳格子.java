import java.util.*;
//https://blog.csdn.net/JackSnack/article/details/124554285
public class Main03跳格子 {
    private static final int NOT_VISITED = 0;
    private static final int VISITED = 1;
    private static final int VISIT_FINISHED = 2;
    static int[] visitStatus;                          //节点遍历状态
    static List<List<Integer>> edges;                     //记录每个节点的相邻节点
    static boolean stepAllGrids;                      //是否能跳完所有格子的结果判断
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            int N = sc.nextInt();
            if (N < 1 || N >= 500) {
                System.out.println("error:输入的格子个数应该大于等于1且小于500");
                return;
            }
            edges = new ArrayList<>();
            for (int i = 0; i < N; i++) {     //默认节点在0 ~ N - 1之间
                edges.add(new ArrayList<>());
            }

            stepAllGrids = true;

            while (sc.hasNext()) {
                int a = sc.nextInt();
                if (a == -1) {            //题目缺失输入退出条件，不妨设置输入-1就退出输入
                    break;
                }
                String line = sc.nextLine();
                String[] strs = line.split(" ");
                if (strs.length != 2) {
                    System.out.println("error:输入的格子数组列数不为2");
                    return;
                }
                if (a < 0 || a >= N) {
                    System.out.println("error:输入的左侧格子号码应该大于等于0且小于" + N);
                    return;
                }
                int b = Integer.parseInt(strs[1]);
                if (b < 0 || b >= N) {
                    System.out.println("error:输入的右侧格子号码应该大于等于0且小于" + N);
                    return;
                }
                edges.get(b).add(a);
            }
            if (step(N, edges)) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }
    }
    public static void dfs(int node) {
        visitStatus[node] = VISITED;

        for (int neighbor : edges.get(node)) {        //遍历相邻节点
            if (visitStatus[neighbor] == NOT_VISITED) {
                dfs(neighbor);

                if (!stepAllGrids) {  //如果退出循环时检测到环，直接退出
                    return;
                }
            } else if (visitStatus[neighbor] == VISITED) {   //再一次遍历到此节点，说明存在环，无法完成拓扑排序
                stepAllGrids = false;
                return;
            }
        }

        visitStatus[node] = VISIT_FINISHED;
    }
    public static boolean step(int N, List<List<Integer>> edges) {
        visitStatus = new int[N];     //节点默认为0 ~ N - 1

        for (int i = 0; i < N && stepAllGrids; i++) {   //总是从节点0开始进入
            if (visitStatus[i] == NOT_VISITED) {
                dfs(i);
            }
        }

        return stepAllGrids;
    }





}
