package 盛水最多的容器;

/**
 * @author hf
 * @createtime 2021-04-22-19:34
 **/
public class maxArea {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[] nums = {1, 8, 6, 2, 5, 4, 8, 3, 7};
//        int[] nums = {1, 2,1};
        int[] nums = {2, 3, 10, 5, 7, 8, 9};
//        int[] nums = {4,3,2,1,4};


        int res = solution.maxArea(nums);
        System.out.println(res);
    }
}

class Solution {
    public int maxArea(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        Temp leftTemp = new Temp();
        Temp rightTemp = new Temp();

        int max = 0;
        leftTemp.setIndex(0);
        leftTemp.setHeight(nums[leftTemp.Index]);
        rightTemp.setIndex(nums.length - 1);
        ;
        rightTemp.setHeight(nums[rightTemp.Index]);
        max = (rightTemp.getIndex() - leftTemp.getIndex()) * Math.min(rightTemp.getHeight(), leftTemp.getHeight());
        max = calculate(nums, leftTemp, rightTemp, max);
        return max;


    }

    public int calculate(int[] nums, Temp left, Temp right, int max) {
        if (left.getIndex() < right.getIndex()) {
            if(left.getHeight() <= right.getHeight())
                return max;

            if (left.getHeight() > right.getHeight()) {
                while (nums[--right.Index] < right.getHeight()) {
                    right.setIndex(right.Index);
                }
                right.setHeight(nums[right.getIndex()]);
                max = Math.max((right.getIndex() - left.getIndex()) * Math.min(right.getHeight(), left.getHeight()), max);

                calculate(nums, left, right, max);
            } else {
                while (nums[++left.Index] < left.getHeight()) {
                    left.setIndex(left.Index);
                }
                left.setHeight(nums[left.getIndex()]);
                max = Math.max((right.getIndex() - left.getIndex()) * Math.min(right.getHeight(), left.getHeight()), max);
                calculate(nums, left, right, max);
            }
        }
        return 0;
    }
}

class Temp {
    public int Index;
    public int Height;

    public int getIndex() {
        return Index;
    }

    public void setIndex(int index) {
        Index = index;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }


}



