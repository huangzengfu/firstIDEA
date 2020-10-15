package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author hf
 * @createtime 2020-10-15-10:26
 **/
public class ShellSort {
    public static void main(String[] args) {
//        int[] arr = {8,9,1,7,2,3,5,4,6,0};
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");
        Date date1 = new Date();
        String date1str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是" + date1str);
//        shellSort1(arr);
        shellSort2(arr);
        Date date2 = new Date();
        String date2str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是" + date2str);
    }

    //希尔排序-换位法
    public static void shellSort1(int[] arr) {
        /*//希尔排序的逐步过程
        //第一轮排序，将n个数组分成n/2组
        int temp = 0;
        for(int i = 5;i<arr.length;i++){
            for(int j = i-5;j>=0;j-=5){
                //如果当前元素大于加上步长的元素，则交换
                if(arr[j]>arr[j+5]){
                    temp = arr[j+5];
                    arr[j+5] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        System.out.println("希尔排序1轮后结果="+ Arrays.toString(arr));

        //第一轮排序，将n个数组分成n/2/2组
        for(int i = 2;i<arr.length;i++){
            for(int j = i-2;j>=0;j-=2){
                //如果当前元素大于加上步长的元素，则交换
                if(arr[j]>arr[j+2]){
                    temp = arr[j+2];
                    arr[j+2] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        System.out.println("希尔排序2轮后结果="+ Arrays.toString(arr));

        //第一轮排序，将n个数组分成n/2/2/2组
        for(int i = 1;i<arr.length;i++){
            for(int j = i-1;j>=0;j-=1){
                //如果当前元素大于加上步长的元素，则交换
                if(arr[j]>arr[j+1]){
                    temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        System.out.println("希尔排序3轮后结果="+ Arrays.toString(arr));*/
        //根据逐步排序的过程，统计规律
        int temp = 0;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            count++;
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }

                }
            }


        }
//        System.out.println("希尔排序换位法结果" + Arrays.toString(arr));

    }

    //希尔排序-移位法
    public static void shellSort2(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                while (j - gap >= 0 && temp < arr[j - gap]) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
        }
//        System.out.println("希尔移位法排序结果"+ Arrays.toString(arr));
    }

}
