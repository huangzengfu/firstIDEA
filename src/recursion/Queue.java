package recursion;

/**
 * @author hf
 * @createtime 2020-10-09-14:18
 **/
public class Queue {
    //定义Queue个数
    int max = 8;
    //定义数组array,保存皇后位置的结果，比如array = {0，4,7,5,2，6,1,3}
    int[] array = new int[max];
    public static void main(String[] args){
        
    }

    //放置第n个皇后
    private void check(int n){
        if(n==max){//n=8时，8皇后已经放置完毕
            print();
            return;
        }
        //依次放入皇后，
        if(judge(n)){
            //接着放入n+1个皇后，开始递归
            check(n+1);
        }
        //如果冲突，就继续执行array[n] = i+1;即 将第n个皇后，放置在本行的后一个位置
    }

    //查看当放置第n个皇后，判断当前皇后位置是否与前面摆放的皇后位置冲突
    private boolean judge(int n){//n表示第n个皇后
        for (int i =0;i<n;i++){
            //1、array[i] == array[n] 表示第i个皇后和第n个皇后在一列
            //2、Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示第i个皇后和第n个皇后在同一斜线
            if(array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }
    //定义输出皇后位置结果
    private void print(){
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
