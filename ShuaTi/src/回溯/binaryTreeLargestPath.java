package 回溯;

import sun.reflect.generics.tree.Tree;


/**
 * @author hf
 * @createtime 2021-01-06-10:28
 **/
public class binaryTreeLargestPath {

    public static  int maxSum =0;
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        TreeNode node5 = new TreeNode(1);
        TreeNode node6 = new TreeNode(8);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;
//        node4.right = node5;
//        node5.right = node6;
        /*求二叉树最大路径和*/
//        maxPathSum(root);
//        System.out.println(maxSum);
        /*求二叉树的最大深度*/
        int  res = maxDepth(root);
        System.out.println(res);
    }
    //求二叉树最大路径和
    public static int maxPathSum(TreeNode root) {
        maxPath(root);
        return maxSum;

    }

    public static int maxPath(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftPath = Math.max(maxPath(node.left), 0);
        int rightPath = Math.max(maxPath(node.left), 0);

        int path = node.val + leftPath + rightPath;

        maxSum = Math.max(maxSum,path);

        return node.val + Math.max(leftPath,rightPath);
    }

    //求二叉树最大深度
    public static int maxDepth(TreeNode root) {
        //叶子节点情况
        if(root.left == null && root.right == null){
            return 1;
        }
        int deepest =0;
        if(root.left == null){
            deepest = maxPath(root.right)+1;
        }else if(root.right == null){
            deepest = maxPath(root.left)+1;
        }else{
            deepest = Math.max(maxDepth(root.left),maxDepth(root.right))+1;
        }
        return deepest;

//        if(root == null){
//            return 0;
//        }
//        int left = maxDepth(root.left);
//        int right = maxDepth(root.right);
//        int maxDept = Math.max(left,right)+1;
//        return maxDept;
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
