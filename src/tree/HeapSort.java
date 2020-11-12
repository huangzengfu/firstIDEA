package tree;

import java.util.Arrays;

/**
 * @author hf
 * @createtime 2020-11-11-18:50
 **/
public class HeapSort {
    public static void main(String[] args) {
        //将数组进行堆排序
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
    }

    //编写堆排序
    public static void heapSort(int[] arr) {
        //根据无序序列建立一个堆，根据升序或降序需求选择大顶堆或者小顶堆
        for(int i = arr.length/2-1;i>=0;i--){
            adjustHeap(arr,i,arr.length);
        }

        //将堆顶元素和堆底元素交换，将大值“沉”到最下边
        //重新调整结构，使其满足堆定义，然后继续交换堆顶元素和堆底元素，反复执行调整+交换步骤，直到整个序列有序
        for(int j=arr.length-1;j>0;j--){
            int temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,j);
        }
        System.out.println(Arrays.toString(arr));
    }

    //方法：讲一个二叉树，调整为大顶堆
    /*
     * 功能：将以i为非叶子节点的树调整成为大顶堆
     * arr:待调整数组
     * i：表示非叶子节点在数组中的索引
     * length：表示对多少个元素继续进行调整，length是逐渐减少
     * */
    public static void adjustHeap(int[] arr, int i, int length) {

        int temp = arr[i];//取出当前元素的值，保存在临时变量
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k+1]) {
                k++;
            }
            if (arr[k] > temp) {//子节点大于父节点
                arr[i] = arr[k];//把较大的值发到当前节点
                i = k;//i指向k，继续循环比较
            } else {
                break;
            }
        }
        //循环结束后，已经将以i为父节点的树的最大值调整到了最顶部
        arr[i] = temp;//将temp值放到调整后的位置上
    }

}
