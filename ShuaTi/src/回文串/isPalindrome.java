package 回文串;

/**
 * @author hf
 * @createtime 2021-03-24-9:51
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 * <p>
 * 首先，我们应该处理一些临界情况。所有负数都不可能是回文，例如：-123 不是回文，因为 - 不等于 3。
 * 所以我们可以对所有负数返回 false。除了 0 以外，所有个位是 0 的数字不可能是回文，因为最高位不等于 0。
 * 所以我们可以对所有大于 0 且个位是 0 的数字返回 false。
 * <p>
 * 现在，让我们来考虑如何反转后半部分的数字。
 * <p>
 * 对于数字 1221，如果执行 1221 % 10，我们将得到最后一位数字 1，要得到倒数第二位数字，我们可以先通过除以 10 把最后一位数字从 1221 中移除，
 * 1221 / 10 = 122，再求出上一步结果除以 10 的余数，122 % 10 = 2，就可以得到倒数第二位数字。如果我们把最后一位数字乘以 10，再加上倒数第二位数字，1 * 10 + 2 = 12，就得到了我们想要的反转后的数字。如果继续这个过程，我们将得到更多位数的反转数字。
 * <p>
 * 现在的问题是，我们如何知道反转数字的位数已经达到原始数字位数的一半？
 * <p>
 * 由于整个过程我们不断将原始数字除以 10，然后给反转后的数字乘上 10，所以，当原始数字小于或等于反转后的数字时，就意味着我们已经处理了一半位数的数字了。
 **/
public class isPalindrome {
    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean flag=solution.isPalindrome(1221);
        System.out.println(flag);
    }
}

class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;
        int reverseNum = 0;
        while (x > reverseNum) {
            reverseNum = reverseNum * 10 + x % 10;
            x/=10;
        }
        return x==reverseNum||x==reverseNum/10;


    }
}
