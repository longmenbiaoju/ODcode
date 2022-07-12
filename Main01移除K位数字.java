import java.math.BigDecimal;
import java.util.*;

public class Main01移除K位数字 {

    private static int n;
    private static char[] chars;
    private static TreeSet<BigDecimal> b=new TreeSet<BigDecimal>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.next();
        n = sc.nextInt();
        chars = line.toCharArray();
        int[] is = new int[chars.length - n];
        dfs(is,0,0);
        BigDecimal first = b.first();
        System.out.println(first.toString());
    }

    private static void dfs(int[] is, int index, int start) {
        if (index==is.length){
            chuli(is);
        }else {
            for (int i = start; i < chars.length; i++) {
                is[index]=i;
                dfs(is,index+1,i+1);
            }
        }
    }
    private static void chuli(int[] is) {
        StringBuilder sb = new StringBuilder();
        for (int s:is){
            sb.append(chars[s]);
        }
        b.add(new BigDecimal(sb.toString()));
    }
}
