package 两数之和;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hf
 * @createtime 2021-03-22-8:46
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
 **/
public class twoSum {
    public static void main(String[] args) {
        int[] arr = {2,7,11,15};
        int[] res = twoSum(arr,9);
        System.out.println(Arrays.toString(res));

    }
//    public static int[] twoSum(int[] nums, int target){
//        ArrayList<Integer> arrayList = new ArrayList<>();
//        for(int i = 0;i<nums.length;i++){
//            for(int j=i+1;j<nums.length;j++){
//                if(nums[i]+nums[j] == target)
//                    return new int[]{i,j};
//            }
//        }
//        return new int[0];
//    }
    public static int[] twoSum(int[] nums, int target){
        Map<Integer, Integer> hashtable = new HashMap<>();
        for(int i = 0;i<nums.length;i++){
            if(hashtable.containsKey(target-nums[i])){
                return new int[]{hashtable.get(target-nums[i]),i};
            }
            hashtable.put(nums[i],i);
        }
        return new int[]{0};
    }
}
