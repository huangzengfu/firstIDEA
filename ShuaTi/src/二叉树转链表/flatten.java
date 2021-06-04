package 二叉树转链表;

import javax.swing.tree.TreeNode;
import java.util.*;

/**
 * @author hf
 * @createtime 2021-04-26-14:32
 **/
public class flatten {
    public static void main(String[] args) {
        TreeNode1 root = new TreeNode1(1);
        TreeNode1 node1 = new TreeNode1(2);
        TreeNode1 node2 = new TreeNode1(3);
        TreeNode1 node3 = new TreeNode1(4);
        TreeNode1 node4 = new TreeNode1(5);
        TreeNode1 node5 = new TreeNode1(6);
        root.left = node1;
        root.right = node4;
        node1.left = node2;
        node1.right = node3;
        node4.right = node5;
    }
}

class Solution {
//    public void flatten(TreeNode1 root) {
//        List<TreeNode1> list = new ArrayList<>();
//        preorderTravesal(root,list);
//        int size = list.size();
//        for(int i = 0;i<size;i++){
//            TreeNode1 prev = list.get(i),curr= list.get(i+1);
//            prev.left = null;
//            prev.right = curr;
//        }
//
//    }
//    public void preorderTravesal(TreeNode1 root,List<TreeNode1> list){
//        if(root!=null){
//            list.add(root);
//            preorderTravesal(root.left,list);
//            preorderTravesal(root.right,list);
//        }
//    }

    public void flatten(TreeNode1 root) {
        List<TreeNode1> list = new ArrayList<>();
        Deque<TreeNode1> stack = new LinkedList<>();

        TreeNode1 currNode = root;
        while(currNode!=null||!stack.isEmpty())
        {
            while(currNode!=null){
                list.add(currNode);
                stack.push(currNode);
                currNode = currNode.left;
            }
            currNode = stack.pop();
            currNode = currNode.right;
        }



        int size = list.size();
        for (int i = 0; i < size; i++) {
            TreeNode1 prev = list.get(i), curr = list.get(i + 1);
            prev.left = null;
            prev.right = curr;
        }

    }
}

class TreeNode1 {
    int val;
    TreeNode1 left;
    TreeNode1 right;

    TreeNode1() {
    }

    TreeNode1(int val) {
        this.val = val;
    }

    TreeNode1(int val, TreeNode1 left, TreeNode1 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
