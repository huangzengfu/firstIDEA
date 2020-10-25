package search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hf
 * @createtime 2020-10-25-19:11
 **/

//二分查找的前提是 该数组是有序的
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000,1000,1000,1000, 1234};
        int returnvalue = binarySearch(arr, 0, arr.length - 1, 0);
        ArrayList<Integer> resIndexList = binarySearch_List(arr, 0, arr.length - 1, 1000);
//        System.out.println("returnvalue="+returnvalue);
        System.out.println("resIndexList = " + resIndexList);
    }

    //二分查找递归算法
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        //当left>right时，说明遍历整个数组，没有找到值
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (midVal < findVal) {
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (midVal > findVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }


    }

    //二分查找递归算法-有重复值得返回list
    public static ArrayList<Integer> binarySearch_List(int[] arr, int left, int right, int findVal) {
        //当left>right时，说明遍历整个数组，没有找到值
        if (left > right) {
            return new ArrayList<Integer>();
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (midVal < findVal) {
            return binarySearch_List(arr, mid + 1, right, findVal);
        } else if (midVal > findVal) {
            return binarySearch_List(arr, left, mid - 1, findVal);
        } else {
            //思路分析
            //1、在找到mid值之后不要立即返回
            //2、向mid索引值的左边扫描，将所有满足findValue元素的下标加入到ArrayList
            //3、向mid索引值的右边扫描，将所有满足findValue元素的下标加入到ArrayList
            //4、返回ArrayList
            ArrayList<Integer> resIndexList = new ArrayList<Integer>();
            //向mid左边扫描
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {//退出条件
                    break;
                }
                resIndexList.add(temp);
                temp--;
            }

            resIndexList.add(mid);

            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                resIndexList.add(temp);
                temp++;
            }

            return resIndexList;
        }


    }
}
