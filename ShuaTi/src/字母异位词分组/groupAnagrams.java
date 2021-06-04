package 字母异位词分组;

import java.util.*;

/**
 * @author hf
 * @createtime 2021-05-03-10:42
 **/
public class groupAnagrams {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> res = solution.groupAnagrams(strs);
        for(List<String> re:res){
            System.out.println(re.toString());
        }
    }
}
class Solution {
    /*由于互为字母异位词的两个字符串包含的字母相同，因此对两个字符串分别进行排序之后得到的字符串一定是相同的，
    故可以将排序之后的字符串作为哈希表的键。*/
    public List<List<String>> groupAnagrams(String[] strs) {
        //定义MAp<String,List<String>> ,以排序后相同的字符串为键
        Map<String,List<String>> map = new HashMap<>();
        //将字符串数组每个元素排序
        for(String str:strs){
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key,new ArrayList<String>());
            list.add(str);
            map.put(key,list);
        }
        return new ArrayList<List<String>>(map.values());
    }
}
