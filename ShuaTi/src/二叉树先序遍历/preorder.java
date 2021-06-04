package 二叉树先序遍历;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author hf
 * @createtime 2021-05-18-8:54
 **/
public class preorder {
    public static void main(String[] args) {
        TreeNode1 root = new TreeNode1(1);
        TreeNode1 node1 = new TreeNode1(2);
        TreeNode1 node2 = new TreeNode1(3);
        TreeNode1 node3 = new TreeNode1(4);
        root.left = node1;
        root.right = node3;
        node1.left = node2;
        Solution solution = new Solution();
        List<Integer> res = solution.preorderTraversal(root);

        for (Integer num : res) {
            System.out.println(num);
        }
    }
}

class Solution {
    public List<Integer> preorderTraversal(TreeNode1 root) {
        //定义输出结果
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode1> stack = new Stack<>();
        stack.push(root);
        //迭代需要用到循环，由于不知道树的size,这里用while循环
        while (!stack.isEmpty()) {
            root = stack.pop();
            if (root != null) {

                stack.push(root.right);
                res.add(root.val);
                stack.push(root.left);
            }
        }
        return res;

    }

    public List<Integer> inorderTraversal(TreeNode1 root) {
        //定义输出结果
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode1> stack = new Stack<>();
        stack.push(root);
        //迭代需要用到循环，由于不知道树的size,这里用while循环
        while (!stack.isEmpty()) {
            root = stack.pop();
            if(root!=null){
                res.add(root.val);
                stack.push(root.right);
                stack.push(root.left);
            }

        }
        return res;

    }
//    public void recurise(TreeNode1 root,List<Integer> res){
//        if(root==null){
//            return;
//        }
//        res.add(root.val);
//        recurise(root.left,res);
//        recurise(root.right,res);
//    }
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
