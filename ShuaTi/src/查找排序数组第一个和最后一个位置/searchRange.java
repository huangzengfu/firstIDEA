package 查找排序数组第一个和最后一个位置;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author hf
 * @createtime 2021-04-23-14:28
 **/
public class searchRange {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {5,7,7,8,8,10};
        int target = 8;
        int[] res = solution.searchRange(nums,target);
        System.out.println(Arrays.toString(res));
    }
}

class Solution{
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1,-1};
        int len = nums.length;
        int left = 0;
        int right = len-1;
        int mid = 0;
        while(left<=right){
            mid = (left+right)/2;
            if(nums[mid]==target){
                int temp = mid;
                while(temp>=0&&nums[temp]==target){
                    temp--;
                }
                res[0] = temp+1;
                temp=mid;
                while(temp<len&&nums[temp]==target){
                    temp++;
                }
                res[1]=temp-1;
                return res;
            }else if(nums[mid]>target){
                right=mid-1;
            }else{
                left=mid+1;
            }
        }

        return res;
    }
}
