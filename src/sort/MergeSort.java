package sort;

import java.util.Arrays;

/**
 * @author hf
 * @createtime 2020-10-20-18:36
 **/
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));
    }

    //分+合方法
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, mid, right, temp);
        }
    }

    //合并方法
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;//初始化i，左边有序列表的初始索引
        int j = mid + 1;//初始化j，右边有序列表的初始索引
        int t = 0;//指向temp数组的当前索引

        //实现思路
        //(一)：先把左右两边的有序列表按照规则填充到temp数组中，直到有一边的有序序列处理完为止
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                //如果左边的有序序列的当前元素，小于等于右边的有序序列的当前元素，即将左边的当前元素填充到temp中
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        //（二）：把剩余数据依次填充到temp数组中
        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }

        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }

        //（三）：将temp数组中的元素拷贝到arr中
        t = 0;
        int tempLeft = left;
        while(tempLeft<=right){
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }

    }

}
