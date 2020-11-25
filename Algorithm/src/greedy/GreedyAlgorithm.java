package greedy;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author hf
 * @createtime 2020-11-25-16:25
 **/
public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建广播电视台，放入map，元素如：（K1，<北，上，广，深>）
        HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
        //将各个电视台放入broadcasts
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //将入到map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        //allAreas存放所有地区
        HashSet<String> allAreas = new HashSet<String>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //创建保存选中电台的集合
        ArrayList<String> selects = new ArrayList<String>();

        //定义一个临时集合，存放当前电台覆盖的地区与allAreas中还未覆盖地区的集合的交集
        HashSet<String> tempSet = new HashSet<String>();
        //定义maxKey，如果maxKey不为空就加入到selects中
        String maxKey = null;

        while (allAreas.size() != 0) {//allAreas的size不为0表示还没覆盖完所有的地区
            maxKey = null;
            for (String key : broadcasts.keySet()) {
                tempSet.clear();
                //当前这个key能够覆盖的地区
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //获取areas和allAreas的交集
                tempSet.retainAll(allAreas);
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            if (maxKey != null) {
                selects.add(maxKey);
                //将maxKey指向的覆盖地区从allAreas中删除掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println("selects = "+selects);
    }
}
