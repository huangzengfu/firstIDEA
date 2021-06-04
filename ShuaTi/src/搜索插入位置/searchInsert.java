package 搜索插入位置;

/**
 * @author hf
 * @createtime 2021-03-16-15:14
 **/
public class searchInsert {
    public static void main(String[] args) {
        int[] nums = {1,2,4,4,5};
        int res = searchInsert(nums,3);
        System.out.println(res);

    }
    public static int searchInsert(int[] nums, int target) {

        int len = nums.length;
        if (nums[len - 1] < target)
        {
            return len;
        }

        int left = 0;
        int right = len-1;
        int mid;
        while(left <right)
        {
            mid = (left+right)/2;
            if(target>nums[mid]){
                left = mid+1;
            }else{
                right = mid;
            }
        }
        return left;
    }
}
