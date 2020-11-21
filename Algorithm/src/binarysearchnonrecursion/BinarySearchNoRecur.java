package binarysearchnonrecursion;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author hf
 * @createtime 2020-11-21-15:24
 **/
public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {1,3,8,8,8,10,11,67,100};
        ArrayList<Integer> arrayList = BinarySearch(arr,8);
        System.out.println("arrayList = "+arrayList);
    }

    public static ArrayList<Integer> BinarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (arr[mid] == target) {
                ArrayList<Integer> arrayList = new ArrayList<Integer>();
                int temp = mid -1;
                while(temp>0 && arr[temp] == target){
                    arrayList.add(temp);
                    temp--;
                }
                arrayList.add(mid);
                temp = mid+1;
                while(temp<arr.length && arr[temp] == target){
                    arrayList.add(temp);
                    temp++;
                }

                return arrayList;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return new ArrayList<Integer>();
    }
}
