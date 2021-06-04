package 组合总和;

import java.util.*;

/**
 * @author hf
 * @createtime 2021-03-25-8:22
 * 题目描述：给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 **/
public class combinationSum {
    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        int target = 7;
        Solution solution = new Solution();
        List<List<Integer>> res = new ArrayList<>();
        res=solution.combinationSum(candidates,target);
        for(List<Integer> elem:res){
            System.out.println(elem);
        }
    }
}

//class Solution {
//    public List<List<Integer>> combinationSum(int[] candidates, int target) {
//        List<List<Integer>> arrayLists = new ArrayList<>();
//
//        int len = candidates.length;
//        int index = 0;
//
//        while (index < len) {
//            int times = 0;
//            ArrayList<Integer> arrayList = new ArrayList<>();
//            //如果数组中存在可整除target元素
//            if (target % candidates[index] == 0) {
//                times = target / candidates[index];
//                while (times > 0) {
//                    arrayList.add(candidates[index]);
//                    times--;
//                }
//                arrayLists.add(arrayList);
//            } else {
//                //若当前元素不能整除target，计算其他元素能否整除（target-当前元素）
//                for (int candidate : candidates) {
//                    if ((target - candidates[index]) % candidate == 0) {
//                        times = (target - candidates[index]) / candidate;
//                        arrayList.add(candidates[index]);
//                        while (times > 0) {
//                            arrayList.add(candidate);
//                            times--;
//                        }
//                        arrayLists.add(arrayList);
//                    }
//                }
//            }
//            index++;
//        }
//        return arrayLists;
//    }
//}
 class Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        Deque<Integer> path = new ArrayDeque<>();
        dfs(candidates, 0, len, target, path, res);
        return res;
    }

    /**
     * @param candidates 候选数组
     * @param begin      搜索起点
     * @param len        冗余变量，是 candidates 里的属性，可以不传
     * @param target     每减去一个元素，目标值变小
     * @param path       从根结点到叶子结点的路径，是一个栈
     * @param res        结果集列表
     */
    private void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
        // target 为负数和 0 的时候不再产生新的孩子结点
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 重点理解这里从 begin 开始搜索的语意
        for (int i = begin; i < len; i++) {
            path.addLast(candidates[i]);

            // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
            dfs(candidates, i, len, target - candidates[i], path, res);

            // 状态重置
            path.removeLast();
        }
    }
}
