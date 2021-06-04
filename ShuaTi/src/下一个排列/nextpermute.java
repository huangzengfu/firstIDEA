package 下一个排列;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author hf
 * @createtime 2021-04-20-9:23
 * 具体地，我们这样描述该算法，对于长度为n的排列a：
 * <p>
 * 首先从后向前查找第一个顺序对(i,i+1)，满足a[i] < a[i+1]。
 * 这样「较小数」即为a[i]。此时[i+1,n) 必然是下降序列。
 * <p>
 * 如果找到了顺序对，那么在区间[i+1,n)中从后向前查找第一个元素j满足a[i]<a[j]。这样「较大数」即为a[j]。
 * <p>
 * 交换a[i]与a[j]，此时可以证明区间[i+1,n)必为降序。
 * 我们可以直接使用双指针反转区间[i+1,n)使其变为升序，而无需对该区间进行排序。
 **/
public class nextpermute {
    public static void main(String[] args) {
//        Solution solution = new Solution();
        Solution1 solution = new Solution1();
//        int[] nums = {4, 5, 2, 6, 3, 1};
        int[] nums = {3,2, 1};
        solution.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}

class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}

class Solution1 {
    public void nextPermutation(int[] nums) {
        //先找到较小的数smaller
        //{4,5,2,6,3,1}
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        //较小数的下标为smaller,且确定数组在区间[smaller+1,n)为降序，继续寻找较大且靠右的数
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }

        //反转nums区间[samller+1,n)的元素
        reverse(nums, nums.length, i);

    }

    private void reverse(int[] nums, int n, int smaller) {
        int left = smaller + 1;
        int right = n - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    private void swap(int[] nums, int smaller, int bigger) {
        int temp = nums[smaller];
        nums[smaller] = nums[bigger];
        nums[bigger] = temp;
    }
}