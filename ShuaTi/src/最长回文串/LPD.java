package 最长回文串;

/**
 * @author hf
 * @createtime 2021-03-18-9:34
 **/
public class LPD {
    public static void main(String[] args) {
        String s = "ababd";
        String res = longestPalindrome(s);
//        boolean flag = check(s);
        System.out.println(res);
    }

//    public static String longestPalindrome(String str){
//        String maxLen = "";
//        if(str.length()==1){
//            return str;
//        }
//        for(int i =0;i<str.length();i++){
//            for(int j=i+1;j<=str.length();j++){
//                String temp = str.substring(i,j);
//                if(check(temp)){
//                    if(temp.length()>maxLen.length()){
//                        maxLen = temp;
//                    }
//                }
//            }
//            if(maxLen.length()>str.length()-i){
//                break;
//            }
//        }
//        return maxLen;
//
//    }
//    public static boolean check(String str){
//        int halfLen = (str.length()-1)/2;
//        int i = 0;
//        while(i<=halfLen){
//            if(str.charAt(i)!=str.charAt(str.length()-1-i)){
//                return false;
//            }
//            i++;
//        }
//        return true;
//    }

    public static String longestPalindrome(String s) {
        // 特判
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;

        // dp[i][j] 表示 s[i, j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        char[] charArray = s.toCharArray();

        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {//边界条件
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

}
