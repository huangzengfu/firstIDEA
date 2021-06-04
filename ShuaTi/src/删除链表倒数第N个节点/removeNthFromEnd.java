package 删除链表倒数第N个节点;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hf
 * @createtime 2021-03-21-9:57
 **/
public class removeNthFromEnd {
    public static void main(String[] args) {
        ListNode1 node5 = new ListNode1(5);
        ListNode1 node4 = new ListNode1(4,node5);
        ListNode1 node3 = new ListNode1(3,node4);
        ListNode1 node2 = new ListNode1(2,node3);
        ListNode1 node1 = new ListNode1(1, node2);
        ListNode1 res = removeNthFromEnd(node1, 2);
        System.out.println(res.val);

    }

    //双重循环
//    public static ListNode1 removeNthFromEnd(ListNode1 head, int n) {
//        if (head == null || (head.next == null && n == 1)) {
//            return null;
//        }
//        ListNode1 temp = head;
//        int count = 0;
//        while (temp != null) {
//            count++;
//            temp = temp.next;
//        }
//        temp = head;
//        if (n == count) {
//            head = head.next;
//        } else {
//            for (int i = 1; i < count - n; i++) {
//                temp = temp.next;
//            }
//            temp.next = temp.next.next;
//        }
//        return head;
//    }
    //索引容器法
//    public static ListNode1 removeNthFromEnd(ListNode1 head, int n){
//        ArrayList<ListNode1> list = new ArrayList<>();
//        ListNode1 temp = head;
//        while(temp!=null){
//            list.add(temp);
//            temp = temp.next;
//        }
//        int len = list.size();
//        if(len==0||(len==1&&n==1)) return null;
//        if(len==n) return list.get(1).next;
//        list.get(len-n-1).next = list.get(len-n).next;
//        return head;
//    }
    //快慢指针
    public static ListNode1 removeNthFromEnd(ListNode1 head, int n){
        ListNode1 slow = head;
        ListNode1 fast = head;
        if(head.next == null) return null;
        for(int i = 0;i<n;i++){
            fast = fast.next;
        }
        if(fast == null) return head.next;
        while(fast.next!=null){
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return head;
    }

}

class ListNode1 {
    int val;
    ListNode1 next;

    ListNode1() {
    }

    ListNode1(int val) {
        this.val = val;
    }

    ListNode1(int val, ListNode1 next) {
        this.val = val;
        this.next = next;
    }
}
