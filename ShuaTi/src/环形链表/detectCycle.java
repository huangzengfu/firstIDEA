package 环形链表;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author hf
 * @createtime 2021-05-13-9:44
 **/
public class detectCycle {
    public static void main(String[] args) {
        solution solution = new solution();
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        node1.next=node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        ListNode resNode = solution.detectCycle1(node1);
        if(resNode!=null){
            System.out.println(resNode.val);
        }else{
            System.out.println("null");
        }


    }
}

class solution{
    //哈希表方法
    public ListNode detectCycle(ListNode head) {
        if(head == null||head.next == null){
            return null;
        }
        HashSet<ListNode> historyTrack = new HashSet<>();
        ListNode temp = head;
        while(temp!=null){
            if(historyTrack.contains(temp)){
                return temp;
            }
            historyTrack.add(temp);
            temp = temp.next;
        }
        return null;
    }
    //快慢指针
    public ListNode detectCycle1(ListNode head) {
        ListNode slow = head,fast = head;
        while(true){
            if(fast==null||fast.next==null) return null;
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                break;
            }
        }
        ListNode ptr = head;
        while(slow!=ptr){
            slow = slow.next;
            ptr = ptr.next;
        }
        return ptr;
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}
