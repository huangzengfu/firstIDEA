package 平衡二叉树;

import javax.swing.tree.TreeNode;

/**
 * @author hf
 * @createtime 2021-03-30-9:45
 **/
public class isSymmetric {
    public static void main(String[] args) {
        TreeNode1 node1 = new TreeNode1(1);
        TreeNode1 node2 = new TreeNode1(2);
        TreeNode1 node3 = new TreeNode1(2);
        TreeNode1 node4 = new TreeNode1(3);
        TreeNode1 node5 = new TreeNode1(4);
        TreeNode1 node6 = new TreeNode1(4);
        TreeNode1 node7 = new TreeNode1(3);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        solution solution = new solution();
        boolean flag = solution.isSymmetric(node1);
        System.out.println(flag);
    }
}

class solution {
    public boolean isSymmetric(TreeNode1 root) {
        return check(root,root);
    }
    public boolean check(TreeNode1 p, TreeNode1 q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
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
