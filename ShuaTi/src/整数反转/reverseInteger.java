package 整数反转;

/**
 * @author hf
 * @createtime 2021-03-24-8:51
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * <p>
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 **/
public class reverseInteger {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int res = solution.reverse1(1534236449);
        System.out.println(res);
    }
}

class Solution {
    public int reverse(int x) {
        String str = Integer.toString(x);
        char[] arr = str.toCharArray();
        int start = 0;
        int sign = 1;
        int res = 0;
        if (arr[start] == '-') {
            start++;
            sign = -1;
        }
        for (int i = arr.length - 1; i >= start; i--) {
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && (arr[i] - '0') > Integer.MAX_VALUE % 10)) {
                return 0;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && (arr[i] - '0') > -(Integer.MIN_VALUE % 10))) {
                return 0;
            }
            res = res * 10 + sign * (arr[i] - '0');
        }

        return res;

    }

    public int reverse1(int x) {
        int res = 0;
        while (x != 0) {
            int pop = x % 10;
            x = x / 10;
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && pop > Integer.MAX_VALUE % 10))
                return 0;
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && pop < Integer.MIN_VALUE % 10))
                return 0;
            res = res*10+pop;

        }
        return res;
    }
}
