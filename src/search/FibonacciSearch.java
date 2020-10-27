package search;

import java.util.Arrays;

/**
 * @author hf
 * @createtime 2020-10-26-20:01
 **/
public class FibonacciSearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int value = fibonacciSearch(arr,0);
        System.out.println("index="+fibonacciSearch(arr,1324));
    }

    /**
     * @return 斐波那契数列
     */
    public static int[] fibonacciSequence(int maxSize) {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 斐波那契查找
     * @param arrays 传入数组
     * @param value 待搜索的值
     * @return 下标
     */
    public static int fibonacciSearch(int[] arrays, int value) {
        int left = 0;
        int right = arrays.length - 1;
        int mid = 0;
        //存放斐波那契数列
        int[] fibArray = fibonacciSequence(10);
        //表示斐波那契分割数值的下标
        int fibIndex = 0;
        //获取到斐波那契分割数值的下标
        while (right > fibArray[fibIndex] - 1) {
            fibIndex++;
        }

        //fibArray[fibIndex]的值可能大于a的长度，因此需要构建一个新数组，不足的部分会使用0填充
        int[] temp = Arrays.copyOf(arrays, fibArray[fibIndex]);

        //将新填充的内容替换为最后的数
        //例：temp = {1,3,4,6,9,11,0,0} => {1,3,4,6,9,11,11,11}
        for (int i = right + 1; i < temp.length; i++) {
            temp[i] = arrays[right];
        }
        //使用while来循环处理，找到value，前提是左指针在右指针前边
        while (left <= right) {
            mid = left + fibArray[fibIndex - 1] - 1;
            //当查找的值小于当前值时应该向数组的前边遍历
            if (value < temp[mid]) {
                right = mid - 1;
                //斐波那契数向前移一位
                fibIndex--;
            }
            //当查找的值小于当前值时应该向数组的后边遍历
            else if (value > temp[mid]) {
                left = mid + 1;
                fibIndex -= 2;
            } else {
                if (mid <= right) {
                    return mid;
                } else {
                    return right;
                }
            }
        }
        return -1;
    }
}
