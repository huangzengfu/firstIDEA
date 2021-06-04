package 相交链表;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author hf
 * @createtime 2021-05-28-20:16
 **/
public class getIntersectionNode {
    public static void main(String[] args) {
        ListNode headA = new ListNode(4);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(8);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        headA.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        ListNode headB = new ListNode(5);
        ListNode node5 = new ListNode(0);
        ListNode node6 = new ListNode(2);
        ListNode node7 = new ListNode(8);
        ListNode node8 = new ListNode(4);
        ListNode node9 = new ListNode(5);
        headB.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;
        Solution solution = new Solution();
        ListNode res = solution.getIntersectionNode2(headA, headB);
        System.out.println(res.val);
    }
}

class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        ListNode tempA = headA;
        ListNode tempB = null;
        while (tempA != null) {
            tempB = headB;
            while (tempB != null) {
                if (tempA == tempB) return tempA;
                tempB = tempB.next;
            }
            tempA = tempA.next;
        }
        return null;
    }

    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        ListNode A = headA, B = headB;
        while (A != B) {
            A = (A != null) ? A.next : headB;
            B = (B != null) ? B.next : headA;
        }
        return A;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;

        ListNode curNode = headA;
        Set<ListNode> set = new HashSet<>();

        while (curNode != null) {
            set.add(curNode);
            curNode = curNode.next;
        }
        curNode = headB;
        while (curNode != null) {
            if (set.contains(curNode))
                return curNode;
            curNode = curNode.next;
        }

        return null;
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
