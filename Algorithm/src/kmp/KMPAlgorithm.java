package kmp;

import java.util.Arrays;

/**
 * @author hf
 * @createtime 2020-11-24-13:49
 **/
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        int[] next = kmpNext("ABCDABD");
        System.out.println("next = " + Arrays.toString(next));
        int value = kmpSearch(str1,str2,next);
        System.out.println("value = "+value);

    }

    //获取子串的部分匹配值
    public static int[] kmpNext(String dest) {
        //创建一个数组，保存字符串各个长度下的部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;//匹配的字符串长度为1时，部分匹配值为0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //当dest.charAt(i) ！= dest.charAt(j)时，需要从next[j-1]获取新的j
            //直到发现有 dest.charAt(i) == dest.charAt(j) 成立才退出
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            //当dest.charAt(i) == dest.charAt(j)时，匹配值+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    //KMP对应算法
    public static int kmpSearch(String str1, String str2, int[] next) {
        //遍历
        for (int i = 0, j = 0; i < str1.length(); i++) {
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j-1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }
}
