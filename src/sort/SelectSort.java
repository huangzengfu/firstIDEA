package sort;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author hf
 * @createtime 2020-10-13-9:56
 **/
public class SelectSort {
    public static void main(String[] args){
        int[] arr = {101,34,119,1};
        selectSort(arr);
    }

    //选择排序
    public static void selectSort(int[] arr){
//        //第一轮排序
//        //原始数组：101,34，119,1
//        //第一轮排序后：1,34，119,101
//        int minIndex = 0;//假定的最小值，后续在循环对比中调整
//        int minValue = arr[minIndex];
//        for(int i = minIndex +1;i<arr.length;i++){
//            if(arr[i] < arr[minIndex]){
//                minIndex = i;
//                minValue = arr[i];
//            }
//        }
//
//        arr[minIndex] = arr[0];
//        arr[0] = minValue;
//
//        System.out.println("第一轮排序~");
//        System.out.println(Arrays.toString(arr));
//
//        //第一轮排序
//        //原始数组：101,34，119,1
//        //第二轮排序后：1,34，119,101
//
//        minIndex = 1;
//        minValue = arr[minIndex];
//        for(int i = minIndex+1;i<arr.length;i++){
//            if(arr[minIndex]>arr[i]){
//                minIndex = i;
//                minValue = arr[i];
//            }
//        }
//
//        arr[minIndex] = arr[1];
//        arr[1] = minValue;
//
//        System.out.println("第二轮排序~");
//        System.out.println(Arrays.toString(arr));

        for (int i =0;i<arr.length-1;i++){
            int minIndex = i;
            int minValue = arr[minIndex];
            for(int j = i+1;j<arr.length;j++){
                if(arr[minIndex]>arr[j]){
                    minIndex = j;
                    minValue = arr[j];
                }
            }

            if(minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = minValue;
            }
            System.out.println("第"+(i+1)+"轮排序~");
            System.out.println(Arrays.toString(arr));
        }

    }
}
