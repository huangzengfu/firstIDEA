package 回溯;

import com.sun.org.apache.bcel.internal.generic.SWAP;

/**
 * @author hf
 * @createtime 2021-01-04-14:32
 **/
public class mergeList {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        //合并两个链表
//        ListNode node = mergeTwoLists(node1,node4);
//        while(node!=null){
//            System.out.println(node.val);
//            node = node.next;
//        }

        //

        ListNode res = swapPairs(node1);
        while(res!=null){
            System.out.println(res.val);
            res = res.next;
        }

    }

    /*将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        if(l1.val<l2.val){
            l1.next = mergeTwoLists(l2,l1.next);
            return l1;
        }else{
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }

    }

    public static ListNode swapPairs(ListNode head){
        if(head == null|| head.next == null)
            return head;
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}