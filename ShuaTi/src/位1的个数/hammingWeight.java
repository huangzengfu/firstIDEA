package 位1的个数;

import java.util.List;

/**
 * @author hf
 * @createtime 2021-03-22-8:14
 **/
public class hammingWeight {
    public static void main(String[] args) {
        int n = 00000000000000000000000000001011;
        int res = hammingWeight(n);
        System.out.println(res);
//        System.out.println(1<<2);
    }
//    public static int hammingWeight(int n) {
//        String n_str = Integer.toBinaryString(n);
//        char[] arr = n_str.toCharArray();
//        int count = 0;
//        for(int i = 0;i<arr.length;i++){
//            if(arr[i]=='1')
//                count++;
//        }
//        return count;
//    }
    public static int hammingWeight(int n) {
        int res = 0;
        for(int i =0;i<32;i++){
            if((n&(i<<i))!=0){
                res++;
            }
        }
        return res;
    }
}
