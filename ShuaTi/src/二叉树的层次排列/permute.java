package 二叉树的层次排列;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author hf
 * @createtime 2021-04-04-9:13
 **/
public class permute {
    public static void main(String[] args) {

    }
}
class solution{
    List<List<Integer>> levels = new ArrayList<List<Integer>>();

    public void helper(TreeNode1 node,int level){
        if(levels.size()==level){
            levels.add(new ArrayList<Integer>());
        }
        levels.get(level).add(node.val);
        if(node.left!=null){
            helper(node.left,level+1);
        }
        if(node.right!=null){
            helper(node.right,level+1);
        }
    }

    public List<List<Integer>> levelOrder(TreeNode1 root) {
//        List<List<Integer>> ret = new ArrayList<List<Integer>>();
//        if (root == null) {
//            return ret;
//        }
//
//        Queue<TreeNode1> queue = new LinkedList<TreeNode1>();
//        queue.offer(root);
//        while (!queue.isEmpty()) {
//            List<Integer> level = new ArrayList<Integer>();
//            int currentLevelSize = queue.size();
//            for (int i = 1; i <= currentLevelSize; ++i) {
//                TreeNode1 node = queue.poll();
//                level.add(node.val);
//                if (node.left != null) {
//                    queue.offer(node.left);
//                }
//                if (node.right != null) {
//                    queue.offer(node.right);
//                }
//            }
//            ret.add(level);
//        }
//
//        return ret;
        if(root==null) return levels;
        helper(root,0);
        return levels;
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
