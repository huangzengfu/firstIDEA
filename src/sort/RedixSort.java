package sort;

import java.util.Arrays;

/**
 * @author hf
 * @createtime 2020-10-22-9:54
 **/
public class RedixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
    }

    //基数排序
    public static void radixSort(int[] arr) {

        //找到数组中最大的值
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        int maxLength = (max + "").length();

        //定义一个二维数组表示10个桶，每个桶即一个数组，数组的长度是初始数组的长度
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中实际存放了多少数据，定义一个一位数组来记录每个桶中存放的数据的个数
        int[] bucketElementCounts = new int[10];

        for (int i = 0,n=1; i < maxLength; i++,n*=10) {
            for (int j = 0; j < arr.length; j++) {
                //取出待排序数组每个元素的个位
                int digitOfElement = arr[j] /n % 10;
                //放入到对应的桶中,bucket[digitOfElement]是所在桶的位置，bucketElementCounts[digitOfElement]是桶中数据的位置
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }

            //按照桶的顺序，依次取出桶中数据，放到初始arr中
            int index = 0;
            //遍历每个桶，取出数据
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //桶中有数据，则添加
                if (bucketElementCounts[k] != 0) {
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }

                bucketElementCounts[k] = 0;
            }

            System.out.println("第"+i +"轮基数排序结果：" + Arrays.toString(arr));
        }

//        //第一轮（针对每个数的各位进行排序）
//        for (int j = 0; j < arr.length; j++) {
//            //取出待排序数组每个元素的个位
//            int digitOfElement = arr[j] % 10;
//            //放入到对应的桶中,bucket[digitOfElement]是所在桶的位置，bucketElementCounts[digitOfElement]是桶中数据的位置
//            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
//            bucketElementCounts[digitOfElement]++;
//        }
//
//        //按照桶的顺序，依次取出桶中数据，放到初始arr中
//        int index = 0;
//        //遍历每个桶，取出数据
//        for (int k = 0; k < bucketElementCounts.length; k++) {
//            //桶中有数据，则添加
//            if (bucketElementCounts[k] != 0) {
//                for (int l = 0; l < bucketElementCounts[k]; l++) {
//                    arr[index] = bucket[k][l];
//                    index++;
//                }
//            }
//
//            bucketElementCounts[k] = 0;
//        }
//
//        System.out.println("第一轮基数排序结果：" + Arrays.toString(arr));
//
//
//        //第二轮（针对每个数的十位进行排序）
//        for (int j = 0; j < arr.length; j++) {
//            //取出待排序数组每个元素的个位
//            int digitOfElement = arr[j] / 10 % 10;
//            //放入到对应的桶中,bucket[digitOfElement]是所在桶的位置，bucketElementCounts[digitOfElement]是桶中数据的位置
//            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
//            bucketElementCounts[digitOfElement]++;
//        }
//
//        //按照桶的顺序，依次取出桶中数据，放到初始arr中
//        index = 0;
//        //遍历每个桶，取出数据
//        for (int k = 0; k < bucketElementCounts.length; k++) {
//            //桶中有数据，则添加
//            if (bucketElementCounts[k] != 0) {
//                for (int l = 0; l < bucketElementCounts[k]; l++) {
//                    arr[index] = bucket[k][l];
//                    index++;
//                }
//            }
//
//            bucketElementCounts[k] = 0;
//        }
//
//        System.out.println("第二轮基数排序结果：" + Arrays.toString(arr));
//
//
//        //第三轮（针对每个数的百位进行排序）
//        for (int j = 0; j < arr.length; j++) {
//            //取出待排序数组每个元素的个位
//            int digitOfElement = arr[j] / 100 % 10;
//            //放入到对应的桶中,bucket[digitOfElement]是所在桶的位置，bucketElementCounts[digitOfElement]是桶中数据的位置
//            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
//            bucketElementCounts[digitOfElement]++;
//        }
//
//        //按照桶的顺序，依次取出桶中数据，放到初始arr中
//        index = 0;
//        //遍历每个桶，取出数据
//        for (int k = 0; k < bucketElementCounts.length; k++) {
//            //桶中有数据，则添加
//            if (bucketElementCounts[k] != 0) {
//                for (int l = 0; l < bucketElementCounts[k]; l++) {
//                    arr[index] = bucket[k][l];
//                    index++;
//                }
//            }
//
//            bucketElementCounts[k] = 0;
//        }
//
//        System.out.println("第三轮基数排序结果：" + Arrays.toString(arr));
    }
}
