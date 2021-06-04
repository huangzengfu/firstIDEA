import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author hf
 * @createtime 2021-03-10-8:34
 **/
public class myhashmap1 {
    public static void main(String[] args) {

    }

}
class Pair<U,V>{
    public U first;
    public V second;

    public Pair(U fisrt,V second){
        this.first = fisrt;
        this.second = second;
    }
}


class Bucket{
    private List<Pair<Integer,Integer>> bucket;

    public Bucket(){
        this.bucket = new LinkedList<Pair<Integer,Integer>>();
    }

    public Integer get(Integer key){
        for(Pair<Integer,Integer> pair:this.bucket){
            if(pair.first.equals(key)){
                return pair.second;
            }
        }
        return -1;
    }

    public void update(Integer key,Integer value){
        boolean found = false;
        for(Pair<Integer,Integer> pair:this.bucket){
            if(pair.first.equals(key)){
                pair.second = value;
                found = true;
            }
        }
        if(!found){
            this.bucket.add(new Pair<Integer,Integer>(key,value));
        }
    }

    public void remove(Integer key){
        this.bucket.removeIf(pair -> pair.first.equals(key));
    }
}

class myHashMap{
    private int key_space;
    private List<Bucket> hash_tables;

    public myHashMap(){
        this.key_space = 2069;
        this.hash_tables = new ArrayList<Bucket>();
        for(int i=0;i<this.key_space;++i){
            this.hash_tables.add(new Bucket());
        }
    }

    public void update(Integer key,Integer value){
        int hash_key = key % this.key_space;
        this.hash_tables.get(hash_key).update(key,value);
    }

    public Integer get(Integer key){
        int hash_key = key % this.key_space;
        return this.hash_tables.get(key).get(key);
    }

    public void remove(Integer key){
        int hash_key = key % this.key_space;
        this.hash_tables.get(key).remove(key);
    }
}