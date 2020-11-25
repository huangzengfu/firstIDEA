package kmp;

/**
 * @author hf
 * @createtime 2020-11-23-20:15
 **/
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "速度 快放假哈速度快 放假后sad";
        String str2 = "度快 放假";
        int index = violenceMatch(str1, str2);
        System.out.println("index = " + index);
    }

    //暴力匹配算法
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int s1len = str1.length();
        int s2len = str2.length();

        int i = 0;
        int j = 0;
        while (i < s1len && j < s2len) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == s2len) {
            return 0;
        } else {
            return -1;
        }
    }
}
