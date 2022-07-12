import java.util.*;

public class Main02找单词 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        char[][] twodim = new char[N][N];
        int count = 0;
        while (count < N) {
            String line = scanner.next();
            String replacedLine = line.replace(",", "");
            for (int i = 0; i < N; i++) {
                twodim[count][i] = replacedLine.charAt(i);
            }
            count++;
        }
        String lastLine = scanner.next();

        StringBuilder str = new StringBuilder();
        boolean result = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (lastLine.charAt(0) == twodim[i][j]) {
                    StringBuilder resultStr = new StringBuilder();
                    if (find(twodim, lastLine, i, j, 0, resultStr)) {
                        str = resultStr;
                    }
                }
            }
        }

        System.out.println(str.reverse());

    }

    private static boolean find(char[][] twodim, String lastLine, int i, int j, int indexOfStr, StringBuilder resultStr) {
        if (indexOfStr >= lastLine.length()) {
            return true;
        }
        if (i < 0 || i >= twodim.length || j < 0 || j >= twodim.length || twodim[i][j] != lastLine.charAt(indexOfStr)) {
            return false;
        }
        twodim[i][j] += 300; // 防止该位置被重复判断

        boolean result =
                find(twodim, lastLine, i - 1, j, indexOfStr + 1, resultStr)
                        || find(twodim, lastLine, i + 1, j, indexOfStr + 1, resultStr)
                        || find(twodim, lastLine, i, j - 1, indexOfStr + 1, resultStr)
                        || find(twodim, lastLine, i, j + 1, indexOfStr + 1, resultStr);
        twodim[i][j] -= 300;
        if (result) {
            if (indexOfStr == 0) {
                resultStr.append(j).append(",").append(i);
                System.out.println(j+","+i+"-------------------");
            }
            if (indexOfStr != 0) {
                resultStr.append(j).append(",").append(i).append(",");
                System.out.println(j+","+i+","+"-------------------");
            }
        }
        return result;
    }
}