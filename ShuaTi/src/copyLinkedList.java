import java.util.HashMap;
import java.util.Map;

/**
 * @author hf
 * @createtime 2021-03-13-9:10
 **/
public class copyLinkedList {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node1.random = node3;
        node2.random = node1;
        node3.random = node4;
        node4.random = node2;

    }

    public Node copyRandomList(Node head) {
//        if(head == null) return null;
//        Node cur = head;
//        Map<Node, Node> map = new HashMap<>();
//        // 3. 复制各节点，并建立 “原节点 -> 新节点” 的 Map 映射
//        while(cur != null) {
//            map.put(cur, new Node(cur.val));
//            cur = cur.next;
//        }
//        cur = head;
//        // 4. 构建新链表的 next 和 random 指向
//        while(cur != null) {
//            map.get(cur).next = map.get(cur.next);
//            map.get(cur).random = map.get(cur.random);
//            cur = cur.next;
//        }
//        // 5. 返回新链表的头节点
//        return map.get(head);
        if(head == null) return null;
        Node temp = head;
        HashMap<Node, Node> map = new HashMap<>();
        while(temp!=null){
            map.put(temp,new Node(temp.val));
            temp = temp.next;
        }

        temp = head;
        while(temp!=null){
            map.get(temp).next = map.get(temp.next);
            map.get(temp).random = map.get(temp.random);
            temp = temp.next;
        }
        return map.get(head);
    }

}

//定义一个HeroNode，每个HeroNode 对象就是一个节点
class Node {
    public int val;
    public Node random;
    public Node next; //指向下一个节点

    //    构造器
    public Node(int val) {
        this.val = val;

    }

    //为显示方便，重新tostring
    @Override
    public String toString() {
        return "HeroNode{" + "val=" + val+"}";
    }
}
