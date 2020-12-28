package 回溯;

import java.util.ArrayList;

/**
 * @author hf
 * @createtime 2020-12-24-10:58
 **/
/*
 * 先用hash表统计s中每个字符出现的次数，显然如果字符c出现的次数小于k，c必然不在最长子串里面，
 * 根据这个特性可以将原始s分割成多个子串递归地求解问题，我们用一个split数组依次来存放每个分割点的索引，
 * 对每个分割区间同样求解该问题(多路的分治问题)，并取结果的最大值保存在变量ans中，此处有一个小trick（如果当前求解的子串长度比已存在的ans还要小，
 * 则没有必要求解该区间，这样可以减少不必要的计算），最后递归的结束点就是当前求解的字符串s符合最长子串的要求。
 * */
public class kChildString {

    public static void main(String[] args) {
//        String str = "ababbcddee";
        String str = "ababbcdefff";
        int k = 2;
        int res = longestSubstring(str, k);
        System.out.println("最大子串长度为：" + res);
    }

    //    public static int longestSubstring(String s, int k) {
//        int[] count = new int[26];
//        //统计字符串中各个字符出现次数
//        for (int i = 0; i < s.length(); i++) {
//            count[s.charAt(i) - 'a']++;
//        }
//        ArrayList<Integer> split = new ArrayList<>();
//        for (int i = 0; i < s.length(); i++) {
//            if (count[s.charAt(i) - 'a'] < k) {
//                split.add(i);
//            }
//        }
//        if (split.isEmpty()) {
//            return s.length();
//        }
//        int ans = 0, pre = 0;
//        String str_split = "";
//        split.add(s.length());
//        for (Integer i : split) {
////            ans = i > pre ? Math.max(ans, longestSubstring(s.substring(pre, i), k)) : ans;
//            str_split = s.substring(pre, i);//substring截头不截尾
//            if (i > pre && longestSubstring(str_split, k) > ans) {
//                ans = Math.max(ans, longestSubstring(s.substring(pre, i), k));
//            }
//            pre = i + 1;
//        }
//        return ans;
//    }
    public static int longestSubstring(String s, int k) {
        int[] count = new int[26];
        //循环统计字符串中每个字符出现的次数
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        ArrayList<Integer> split = new ArrayList<Integer>();
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 'a'] < k) {
                split.add(i);
            }
        }
        if (split.isEmpty()) {
            return s.length();
        }
        split.add(s.length());
        int maxLen = 0, pre = 0;
        String split_str = "";
        for (Integer i : split) {
            split_str = s.substring(pre, i);
            if (i > pre && longestSubstring(split_str, k) > maxLen) {
                maxLen = longestSubstring(split_str, k);
            }
            pre = i + 1;
        }
        return maxLen;
    }
}
