package search;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author hf
 * @createtime 2020-10-26-14:34
 **/
public class insertValueSearch {
    public static void main(String[] args){
//        int[] arr = new int[100];
//        for(int i =0;i<arr.length;i++){
//            arr[i] = i+1;
//        }
        int[] arr = {1, 8, 10, 89, 1000,1000,1000,1000, 1234};
        ArrayList<Integer>  indexResList = insertvalueSearch(arr,0,arr.length-1,1000);
        System.out.println(indexResList);
    }

    //插值排序，待查找的数组是有序的
    public static ArrayList<Integer> insertvalueSearch(int[] arr,int left,int right,int findVal){
        if(left>right || findVal<arr[0]||findVal>arr[arr.length-1]){
            return new ArrayList<Integer>();
        }
        ArrayList<Integer> indexResList = new ArrayList<Integer>();

        int mid = left + (right - left)*(findVal-arr[left])/(arr[right] - arr[left]);
        int midValue = arr[mid];

        if(findVal>midValue){
            return insertvalueSearch(arr,mid+1,right,findVal);
        }else if(findVal<midValue){
            return insertvalueSearch(arr,left,mid-1,findVal);
        }else{

            int temp = mid -1;
            while(true){
                if(temp<0 || arr[temp] != findVal)
                    break;
                indexResList.add(temp);
                temp--;
            }

            indexResList.add(mid);
            temp = mid+1;
            while(true){
                if(temp>arr.length-1 || arr[temp] != findVal)
                    break;
                indexResList.add(temp);
                temp++;
            }

            return indexResList;

        }
    }
}
