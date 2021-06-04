package 子集;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hf
 * @createtime 2021-04-20-19:29
 * 记原序列中元素的总数为 nn。原序列中的每个数字 ai
 * 的状态可能有两种，即「在子集中」和「不在子集中」。我们用1表示「在子集中」，0表示不在子集中，那么每一个子集可以对应一个长度为n的0/1序列，第i位表示 a_ia
 * i是否在子集中。
 **/
public class subsets {
    public static void main(String[] args) {
//        Solution1 solution = new Solution1();
//        Solution solution = new Solution();
        Solution2 solution = new Solution2();
        int[] nums = {1, 2, 3};
        List<List<Integer>> res = solution.subsets(nums);
        for (List<Integer> list : res) {
            System.out.println(list);
        }

    }
}

class Solution {
    List<Integer> t = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        int a = 1 << n;
        for (int mask = 0; mask < a; ++mask) {
            t.clear();
            for (int i = 0; i < n; ++i) {
                int b = 1 << i;
                if ((mask & b) != 0) {
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<Integer>(t));
        }
        return ans;
    }
}

class Solution1 {
    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        int a = 1 << n;
        for (int mask = 0; mask < a; ++mask) {
            t.clear();
            for (int i = 0; i < n; ++i) {
                if ((mask & 1 << i) != 0) {
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<Integer>(t));
        }

        return ans;
    }
}

class Solution2 {
    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs(0, nums);
        return ans;
    }

    public void dfs(int cur, int[] nums) {
        if (cur == nums.length) {
            ans.add(new ArrayList<Integer>(t));
            return;
        }
        t.add(nums[cur]);
        dfs(cur + 1, nums);
        t.remove(t.size() - 1);
        dfs(cur + 1, nums);
    }
}
