package 搜索螺旋排序数据;

/**
 * @author hf
 * @createtime 2021-03-17-8:28
 **/
public class searchTurnArray1 {
    public static void main(String[] args) {
//        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int[] nums = {2, 4, 5, 6, 7, 0, 1};
        int res = search(nums, 0);
        System.out.println(res);

    }

    //    public static int search(int[] nums, int target) {
//        int i=1;
//        int loc =0;
//        while(i<nums.length){
//            if(nums[i]<nums[i-1]){
//                loc = i;
//                break;
//            }
//            i++;
//        }
//        return binarySearch(nums,loc,target);
//    }
//
//    public static int binarySearch(int[] nums,int loc,int target){
//        //从0到loc-1的查找
//        boolean flag = false;
//        int left =0;
//        int right = loc-1;
//        while(left<right){
//            int mid = (left+right)/2;
//            if(nums[mid]<target)
//                left = mid+1;
//            else{
//                right = mid;
//            }
//        }
//        if(nums[left]==target){
//            return left;
//        }
//        //从loc到len-1的查找
//        if(!flag){
//            left = loc;
//            right = nums.length-1;
//            while(left<right){
//                int mid = (left+right)/2;
//                if(nums[mid]<target)
//                    left = mid+1;
//                else{
//                    right = mid;
//                }
//            }
//        }
//        if(nums[left]==target){
//            return left;
//        }else{
//            return -1;
//        }
//
//
//    }
    public static int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

}
