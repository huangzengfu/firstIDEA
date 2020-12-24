package 回溯;

import java.util.ArrayList;

/**
 * @author hf
 * @createtime 2020-12-24-10:58
 **/
public class kChildString {

    public static void main(String[] args) {
        String str = "ababbc";
        int k = 2;
        longestSubstring(str,k);
    }

    public static int longestSubstring(String s, int k) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        ArrayList<Integer> split = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 'a'] < k) {
                split.add(i);
            }
        }
        if (split.isEmpty()) {
            return s.length();
        }
        int ans = 0, pre = 0;
        split.add(s.length());
        for (Integer i : split) {
//            ans = i > pre ? Math.max(ans, longestSubstring(s.substring(pre, i), k)) : ans;
            if (i > pre && longestSubstring(s.substring(pre, i), k) > ans) {
                ans = Math.max(ans, longestSubstring(s.substring(pre, i), k));
            }
            pre = i + 1;
        }
        return ans;
    }
}
