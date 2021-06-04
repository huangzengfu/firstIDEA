package 从前序与中序遍历序列构造二叉树;

import javax.swing.tree.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hf
 * @createtime 2021-05-08-15:21
 **/
public class buildTree {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode1 resNode = solution.buildTree(preorder,inorder);
        TreeNode1 temp = resNode;
        preorder(temp);

    }

    public static void preorder(TreeNode1 node){
        System.out.println(node.val);
        if(node.left!=null){
            preorder(node.left);
        }
        if(node.right!=null){
            preorder(node.right);
        }
    }
}

class Solution {

    public Map<Integer,Integer> indexMap = new HashMap<>();

    public TreeNode1 myBuildTree(int[] preorder,int[] inorder,int preorder_left,int preorder_right,
                                 int inorder_left,int inorder_right)
    {
        if (preorder_left > preorder_right) {
            return null;
        }

        //前序遍历的第一个节点就是根节点
        int preorder_root = preorder_left;
        //用indexMap找出中序遍历的根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);

        //先建立根节点
        TreeNode1 root = new TreeNode1(preorder[preorder_left]);
        //得到左子树的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        root.left = myBuildTree(preorder,inorder,preorder_left+1,preorder_left+size_left_subtree,inorder_left,inorder_root-1);
        root.right = myBuildTree(preorder,inorder,preorder_left+size_left_subtree+1,preorder_right,inorder_root+1,inorder_right);

        return root;
    }

    public TreeNode1 buildTree(int[] preorder, int[] inorder) {
        int n = inorder.length;
        for(int i = 0;i<n;i++){
            indexMap.put(inorder[i],i);
        }
        return myBuildTree(preorder,inorder,0,n-1,0,n-1);
    }
}

class TreeNode1 {
    int val;
    TreeNode1 left;
    TreeNode1 right;
    TreeNode1() {}
    TreeNode1(int val) { this.val = val; }
    TreeNode1(int val, TreeNode1 left, TreeNode1 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
