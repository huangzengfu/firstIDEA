package sort;

import java.util.Arrays;

/**
 * @author hf
 * @createtime 2020-10-14-8:58
 **/
public class InsertSort {
    public static void main(String[] args){

    }

    //插入排序
    public static void insertSort(int[] arr){

        for(int i = 1;i<arr.length;i++) {
            //定义带插入的数（）
            int insertVal = arr[i];
            int insertIndex = i - 1;//即带插入数前一位的下标

            //while循环找到insertVal的位置
            /*说明
             * 1、insertIndex>=0 确保找insertVal插入位置时数组不越界
             * 2、insertVal < arr[insertIndex] 说明带插入数还没找到位置
             * */
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            //当退出while循环时，找到带插入数的位置，是insertIndex+1
            arr[insertIndex + 1] = insertVal;

            System.out.println("第" + i + "次排序的结果是~");
            System.out.println(Arrays.toString(arr));
        }
    }
}
