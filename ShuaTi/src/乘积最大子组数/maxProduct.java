package 乘积最大子组数;

/**
 * @author hf
 * @createtime 2021-05-23-10:06
 **/
public class maxProduct {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2,3,-2,4};
//        int[] nums = {-2};
        int res = solution.maxProduct1(nums);
        System.out.println(res);
    }
}
class Solution{
    public int maxProduct(int[] nums) {
        int max =nums[0];
        int len = nums.length;

        for(int i =0;i<len;i++){
            max = Math.max(max,nums[i]);
            for(int j =i+1;j<len;j++){
                nums[i]*=nums[j];
                max = Math.max(max,nums[i]);
            }
        }
        return max;
    }

    public int maxProduct1(int[] nums){
        int len = nums.length;
        int[] maxF = new int[len];
        int[] minF = new int[len];
        System.arraycopy(nums,0,maxF,0,len);
        System.arraycopy(nums,0,minF,0,len);
        for(int i =1;i<len;i++){
            maxF[i] = Math.max(maxF[i-1]*nums[i],Math.max(minF[i-1]*nums[i],nums[i]));
            minF[i] = Math.min(maxF[i-1]*nums[i],Math.min(minF[i-1]*nums[i],nums[i]));
        }
        int max = maxF[0];
        for(int i =1;i<len;i++){
            max = Math.max(max,maxF[i]);
        }

        return max;

    }

}
