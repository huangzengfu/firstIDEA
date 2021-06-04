package 中序遍历;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author hf
 * @createtime 2021-03-14-9:41
 **/
public class inorderList {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
        Tree tree = new Tree();
        tree.setRoot(node1);
//        tree.inorder(node1);
        List<Integer> list = tree.infixOrderNotRecur(node1);
        System.out.println(list);

    }
}

class Tree {
    private TreeNode root;

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public void inorder(TreeNode node) {
        if (node == null) {
            return;
        }

        if (node.left != null) {
            inorder(node.left);
        }
        System.out.println(node.val);
        if (node.right != null) {
            inorder(node.right);
        }
    }

    public List<Integer> infixOrderNotRecur(TreeNode node) {
        List<Integer> arrayList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = node;
        if (root != null) {
            while (temp != null||!stack.isEmpty()) {
                while (temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                }
                if (!stack.isEmpty()) {
                    temp = stack.pop();
                    arrayList.add(temp.val);
                    System.out.println(temp.val);
                    temp = temp.right;
                }
            }
        }

        return arrayList;

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
