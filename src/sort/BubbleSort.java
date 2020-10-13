package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * @author hf
 * @createtime 2020-10-13-8:22
 **/
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for(int i = 0;i<arr.length;i++){
            arr[i] = (int)(Math.random()*800000);
        }
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date date1 = new Date();
        String date1str = simple.format(date1);
        System.out.println("排序前的时间是"+date1str);
        bubblesort(arr);
        Date date2 = new Date();
        String date2str = simple.format(date2);
        System.out.println("排序后的时间是"+date2str);
    }

    public static void bubblesort(int[] arr) {
        int temp = 0;
        boolean flag = false;
        for (int j = 0; j < arr.length - 1; j++) {
            for (int i = 0; i < arr.length - 1 -j; i++) {
                if (arr[i] > arr[i + 1]) {
                    flag = true;
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
            if(flag){
                flag = false;
            }else{
                break;
            }
//            System.out.printf("第%d次排序结果是：\n", j+1);
//            System.out.println(Arrays.toString(arr));
        }
    }
}
