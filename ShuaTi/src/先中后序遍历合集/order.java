package 先中后序遍历合集;


import java.util.Stack;

/**
 * @author hf
 * @createtime 2021-05-22-9:48
 **/
public class order {
    public static void main(String[] args) {
        TreeNode1 root = new TreeNode1(1);
        TreeNode1 node1 = new TreeNode1(2);
        TreeNode1 node2 = new TreeNode1(3);
        TreeNode1 node3 = new TreeNode1(4);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        preOrder solution = new preOrder();
        solution.preorder(root);
        System.out.println("++++++++++++++++++++++++++");
        solution.inorder(root);
        System.out.println("++++++++++++++++++++++++++");
        solution.postorder(root);
    }
}

class preOrder {
    public void preorder(TreeNode1 root) {
        Stack<TreeNode1> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()||root != null) {
            root = stack.pop();
            if (root != null) {
                System.out.println(root.val);
                //先入栈右子树，确保栈顶是左子树，先处理左子树
                stack.push(root.right);
                stack.push(root.left);
            }
        }
    }

    public void inorder(TreeNode1 root){
        Stack<TreeNode1> stack = new Stack<>();
        while(!stack.isEmpty()||root!=null){
            if(root!=null){
                stack.push(root);
                root = root.left;
            }else{
                root = stack.pop();
                System.out.println(root.val);
                root = root.right;
            }
        }
    }

    public void postorder(TreeNode1 root){
        Stack<TreeNode1> stack = new Stack<>();
        TreeNode1 prev = null;
        while(!stack.isEmpty()||root!=null){
            while(root!=null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(root.right==null||root.right==prev){
                System.out.println(root.val);
                prev = root;
                root = null;
            }else{
                stack.push(root);
                root = root.right;
            }
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