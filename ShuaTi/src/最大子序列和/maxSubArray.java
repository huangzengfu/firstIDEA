package 最大子序列和;

import java.util.Arrays;

/**
 * @author hf
 * @createtime 2021-04-03-11:07
 **/
public class maxSubArray {
    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        solution solution = new solution();
        int res = solution.maxSubArray(nums);
        System.out.println(res);
    }
}
class solution{
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        if(len==0) return 0;
        if(len==1) return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        //设dp[i]为到第i个下标最大的值，动态转移公式为：dp[i] = max(dp[i-1]+nums[i],nums[i])
        for(int i =1;i<len;i++){
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max= Math.max(max, dp[i]);
        }
        return max;
    }
}
