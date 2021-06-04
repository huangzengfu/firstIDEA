package 二叉树后续遍历;

import sun.reflect.generics.tree.Tree;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author hf
 * @createtime 2021-05-20-9:19
 **/
public class postorder {
    public static void main(String[] args) {

    }
}

class Solution {
    public List<Integer> postorderTraversal(TreeNode1 root) {
        if (root != null) {

            List<Integer> res = new ArrayList<>();
            Stack<TreeNode1> stack = new Stack<>();
            TreeNode1 prev = null;
            while (!stack.isEmpty() || root != null) {
                //先while循环找到树的最左叶子节点
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                //弹出栈顶元素进行处理
                root = stack.pop();
                //判断栈顶元素是否有右子树或者右子树是否访问过
                if (root.right == null || root.right == prev) {
                    res.add(root.val);
                    prev = root;
                    root = null;
                } else {
                    stack.push(root);
                    root = root.right;
                }
            }
            return res;
        }
        return null;
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
