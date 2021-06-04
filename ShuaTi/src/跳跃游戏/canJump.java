package 跳跃游戏;

/**
 * @author hf
 * @createtime 2021-04-26-9:23
 **/
public class canJump {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[] nums = {2, 3, 1, 1, 4};
        int[] nums = {3,2,1,0,4};
        boolean flag = solution.canJump(nums);
        System.out.println(flag);
    }

}

class Solution {
    public boolean canJump(int[] nums) {
        //定义最远到达的距离
        int longestReach = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if(i>longestReach){
                return false;
            }
            longestReach = Math.max(longestReach,i+nums[i]);

        }
        return true;
    }
}
