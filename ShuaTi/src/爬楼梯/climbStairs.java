package 爬楼梯;

import javax.sound.midi.Soundbank;
import java.util.zip.DeflaterOutputStream;

/**
 * @author hf
 * @createtime 2021-04-01-9:03
 **/
public class climbStairs {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int res = solution.climbStairs1(10);
        System.out.println(res);
    }
}
class Solution {
    public int climbStairs(int n) {
        int[] dp=new int[n];
        dp[0]=1;
        dp[1]=2;
        for(int i=2;i<n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n-1];
    }
    public int climbStairs1(int n) {
        if(n==0) return 1;
        if(n==1) return 2;
        return climbStairs(n-1)+climbStairs(n-2);
    }
}
