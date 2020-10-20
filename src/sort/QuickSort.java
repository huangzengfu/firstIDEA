package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author hf
 * @createtime 2020-10-15-21:09
 **/
public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = new int[8];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (int) (Math.random() * 80000);
//        }
        int[] arr = {3, 2,3, 6, 5, 3,8, 4, 1};
        System.out.println("排序前的arr"+Arrays.toString(arr));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");
        Date date1 = new Date();
        String date1str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是" + date1str);
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序后的arr"+Arrays.toString(arr));
        Date date2 = new Date();
        String date2str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是" + date2str);
    }

    //韩顺平版
//    public static void quickSort(int[] arr, int left, int right) {
//        int l = left;//左下标
//        int r = right;//右下标
//        int pivot = arr[(left + right) / 2];
//
//        while (l < r) {
//            //在pivot左边做到比它大或相等的值
//            while (arr[l] < pivot) {
//                l++;
//            }
//            //在pivot右边做到比它小或相等的值
//            while (arr[r] > pivot) {
//                r--;
//            }
//
//            //在交换值之前先判断r和l的值是否相等,若相等，说明此时pivot左边的值都小于等于它、右边的值都大于或等于它
//            if (r == l) {
//                break;
//            }
//
//            int temp = 0;
//            temp = arr[r];
//            arr[r] = arr[l];
//            arr[l] = temp;
//
//            //这里判断交换值后arr[r]和arr[l]是否和pivot相等，若相等，对应的下标做修改，否则一直停留在当前下标交换
//            if(arr[l] == pivot){
//                r--;
//            }
//            if(arr[r] == pivot){
//                l++;
//            }
//
//        }
//        //如果l==r，l--和r++,否则栈溢出
//        if(l==r){
//            l+=1;
//            r-=1;
//        }
//
//        if(left<r){
//            quickSort(arr,left,r);
//        }
//        if(right>l){
//            quickSort(arr,l,right);
//        }
//
//
//    }

    /*国外版*/
    //交换值
    public static void swapReferences(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    //获取枢纽值
    public static int pivot(int[] arr, int left, int right) {
        int center = (left + right) / 2;
        //比较arr[left]、arr[center]、arr[right]，取中间数作为枢纽值，赋值给arr[center]
        if (Integer.compare(arr[center], arr[left]) < 0) {
            swapReferences(arr, center, left);
        }
        if (Integer.compare(arr[right], arr[left]) < 0) {
            swapReferences(arr, right, left);
        }
        if (Integer.compare(arr[right], arr[center]) < 0) {
            swapReferences(arr, right, center);
        }
        //这里将枢纽值赋到数组最后，因为arr[right]经过之前的换位已经是大值，所有枢纽值要换位到arr[right-1]
        swapReferences(arr, center, right - 1);
        return arr[right - 1];
    }

    public static void quickSort(int[] arr, int left, int right) {
        int pivot = pivot(arr, left, right);
        int i = left;
        int j = right - 1;
        while (i<j) {
            while (Integer.compare(arr[++i], pivot) < 0) {
            }
            while (Integer.compare(arr[--j], pivot) > 0) {
            }
            if (i < j) {
                swapReferences(arr, i, j);
            } else {
                break;
            }
        }

        //将枢纽值和当前i位置的值换位
        if(i!=right-1)
            swapReferences(arr, i, right - 1);

        if(left<i-1)
            quickSort(arr, left, i - 1);
        if(i+1<right)
            quickSort(arr, i + 1, right);

    }
}
