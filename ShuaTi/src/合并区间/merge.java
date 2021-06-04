package 合并区间;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hf
 * @createtime 2021-04-25-9:27
 **/
public class merge {
    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        int[][] intervals = {{0, 3}, {0, 2}, {0, 1}, {1, 9}, {2, 5}, {10, 11}, {12, 20}, {19, 20}};
//        int[][] intervals = {{1, 3}, {8, 10}, {2, 6}, {15, 18}};
//        int[][] intervals = {{1, 4}, {0, 1}};
        int[][] res = solution.merge(intervals);
        for (int[] re : res) {
            System.out.println(Arrays.toString(re));
        }
    }
}

class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        if(intervals==null||intervals.length==0) return res.toArray(new int[0][]);
        //对起点终点进行排序
        Arrays.sort(intervals);
        int index =0;
        while(index<intervals.length-1){
            int former0 = intervals[index][0];
            int former1 = intervals[index][1];
            int latter = intervals[index+1][0];
            while(latter<=former1){
                index++;
                former1 = Math.max(former1,intervals[index][1]);
            }
            // 将现在的区间放进res里面
            res.add(new int[]{former0, former1});
            // 接着判断下一个区间
            index++;
        }
        return res.toArray(new int[0][]);
    }
}
class Solution1 {
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        if (intervals.length == 0 || intervals == null) return res.toArray(new int[0][]);
        // 对起点终点进行排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int i = 0;
        while (i < intervals.length) {
            int left = intervals[i][0];
            int right = intervals[i][1];      
            // 如果有重叠，循环判断哪个起点满足条件
            while (i < intervals.length - 1 && intervals[i + 1][0] <= right) {
                i++;
                right = Math.max(right, intervals[i][1]);
            }
            // 将现在的区间放进res里面
            res.add(new int[]{left, right});
            // 接着判断下一个区间
            i++;
        }
        return res.toArray(new int[0][]);
    }
}
