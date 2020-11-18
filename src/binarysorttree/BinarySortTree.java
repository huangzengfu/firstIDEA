package binarysorttree;

/**
 * @author hf
 * @createtime 2020-11-17-14:14
 **/
public class BinarySortTree {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        bSortTree bSortTree = new bSortTree();
        for (int i : arr) {
            bSortTree.add(new Node(i));
        }

        System.out.println("输出中序遍历~");
        bSortTree.infixOrder();

        bSortTree.delNode(7);

        System.out.println("输出中序遍历~");
        bSortTree.infixOrder();

    }
}

class bSortTree {
    private Node root;

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }

    }

    public void infixOrder() {
        if (root == null) {
            System.out.println("树为空~");
        } else {
            root.infixOrder();
        }
    }

    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    //方法：传入一个结点node，返回以node为根节点的二叉排序树的最小值并删除最小节点
    public int delRightTreeMin(Node node){
        Node target = node;
        //循环查找子节点就会找到最小值
        if(target.left!=null){
            target = target.left;
        }
        //删除最小结点，并返回该节点的值
        delNode(target.value);
        return target.value;
    }

    //删除结点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //先去找要删除的结点
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            //二叉树只要一个结点，并且还不是目标结点
            if (root.left == null && root.right == null) {
                root = null;//存疑
                return;
            }
            //去找targetNode的父级结点
            Node parent = searchParent(value);

            //如果要删除的结点是叶子结点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode是parent的左子结点还是右子结点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {//删除有两个子树的节点
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            } else {//删除只有一个子树的节点
                if(targetNode.left!=null){//待删除结点的左子树不为空
                    if(parent.left == targetNode){
                        parent.left = targetNode.left;
                    }else{
                        parent.right = targetNode.left;
                    }
                }else{//待删除结点的右子树不为空
                    if(parent.left == targetNode){
                        parent.left = targetNode.right;
                    }else{
                        parent.right = targetNode.right;
                    }

                }

            }
        }

    }

}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //遍历方式添加
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (this.value > node.value) {
            if (this.left != null) {
                this.left.add(node);
            } else {
                this.left = node;
            }
        } else {
            if (this.right != null) {
                this.right.add(node);
            } else {
                this.right = node;
            }
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this.value);
        if (this.right != null) {
            this.right.infixOrder();
        }

    }

    //查找待删除的目标结点
    public Node search(int value) {
        if (this.value == value) {
            return this;
        } else if (this.value > value) {
            if (this.left == null) {
                return null;
            } else {
                return this.left.search(value);
            }
        } else {
            if (this.right == null) {
                return null;
            } else {
                return this.right.search(value);
            }
        }
    }

    //查找到待删除节点的父节点
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (this.left != null && this.value > value) {
                return this.left.searchParent(value);
            } else if (this.right != null && this.value <= value) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
