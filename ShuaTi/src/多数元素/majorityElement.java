package 多数元素;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hf
 * @createtime 2021-05-28-21:36
 **/
public class majorityElement {
    public static void main(String[] args) {

    }
}

class Solution{
    public int majorityElement(int[] nums) {

        Map<Integer,Integer> numCount = new HashMap<>();
        for (int num : nums) {
            if (numCount.containsKey(num)) {
                numCount.put(num, numCount.get(num) + 1);
            } else {
                numCount.put(num, 1);
            }
        }
        Map.Entry<Integer,Integer> res = null;
        for(Map.Entry<Integer,Integer> elem:numCount.entrySet()){
            if(res==null||res.getValue()<elem.getValue())
                res = elem;
        }

        return res.getKey();

    }
}
