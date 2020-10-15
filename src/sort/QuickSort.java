package sort;

/**
 * @author hf
 * @createtime 2020-10-15-21:09
 **/
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;//左下标
        int r = right;//右下标
        int pivot = arr[(left + right) / 2];

        while (l < r) {
            //在pivot左边做到比它大或相等的值
            while (arr[l] < pivot) {
                l++;
            }
            //在pivot右边做到比它小或相等的值
            while (arr[r] > pivot) {
                r++;
            }

            //在交换值之前先判断r和l的值是否相等,若相等，说明此时pivot左边的值都小于等于它、右边的值都大于或等于它
            if (r == l) {
                break;
            }

            int temp = 0;
            temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp;

            //这里判断交换值后arr[r]和arr[l]是否和pivot相等，若相等，对应的下标做修改，否则一直停留在当前下标交换
            if(arr[r] == pivot){
                r--;
            }
            if(arr[l] == pivot){
                l++;
            }

        }
        //如果l==r，l--和r++,否则栈溢出
        if(l==r){
            l-=1;
            r+=1;
        }

        if(left<r){
            quickSort(arr,left,r);
        }
        if(right>l){
            quickSort(arr,l,right);
        }


    }
}
