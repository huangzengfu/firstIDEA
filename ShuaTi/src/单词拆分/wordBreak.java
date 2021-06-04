package 单词拆分;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author hf
 * @createtime 2021-05-11-9:27
 **/
public class wordBreak {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "applepenapple";
        List<String> wordDict = new ArrayList<String>() {
            {
                add("apple");
                add("pen");
            }
        };
        boolean flag = solution.wordBreak(s,wordDict);
        System.out.println(flag);
    }
}

//public class Solution {
//    public boolean wordBreak(String s, List<String> wordDict) {
//        Set<String> wordDictSet = new HashSet(wordDict);
//        boolean[] dp = new boolean[s.length() + 1];
//        dp[0] = true;
//        for (int i = 1; i <= s.length(); i++) {
//            for (int j = 0; j < i; j++) {
//                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
//                    dp[i] = true;
//                    break;
//                }
//            }
//        }
//        return dp[s.length()];
//    }
//}

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for(int i = 1;i<= s.length();i++){
            for(int j = 0;j<i;j++){
                if(dp[j]&&wordDictSet.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
