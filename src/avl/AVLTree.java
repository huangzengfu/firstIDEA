package avl;

/**
 * @author hf
 * @createtime 2020-11-18-19:15
 **/
public class AVLTree {
    public static void main(String[] args) {
        int[] arr = {4, 3, 6, 5, 7, 8};
//        int[] arr = {10, 8, 9, 12, 7, 6};
        aTree avlTree = new aTree();
        for (int i : arr) {
            avlTree.add(new Node(i));
        }
        System.out.println("中序遍历：");
        avlTree.infixOrder();
        System.out.println("没有平衡处理前树的高度情况：");
        System.out.println("树的高度：" + avlTree.height());
        System.out.println("左子树的高度：" + avlTree.leftHeight());
        System.out.println("右子树的高度：" + avlTree.rightHeight());
    }
}

class aTree {
    private Node root;

    //添加结点
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root == null) {
            System.out.println("树为空~");
        } else {
            root.infixOrder();
        }
    }

    //获取树的高度
    public int height() {
        if (root == null) {
            return 0;
        } else {
            return root.height();
        }
    }

    //获取树的左子树高度
    public int leftHeight() {
        if (root == null) {
            return 0;
        } else {
            return root.leftHeight();
        }
    }

    //获取树的右子树高度
    public int rightHeight() {
        if (root == null) {
            return 0;
        } else {
            return root.rightHeight();
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
    public int delRightTreeMin(Node node) {
        Node target = node;
        //循环查找子节点就会找到最小值
        if (target.left != null) {
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
                if (targetNode.left != null) {//待删除结点的左子树不为空
                    if (parent.left == targetNode) {
                        parent.left = targetNode.left;
                    } else {
                        parent.right = targetNode.left;
                    }
                } else {//待删除结点的右子树不为空
                    if (parent.left == targetNode) {
                        parent.left = targetNode.right;
                    } else {
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

    //返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        } else {
            return left.height();
        }
    }

    //返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        } else {
            return right.height();
        }
    }

    //返回当前节点的高度，以该节点为根节点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //进行左旋转
    public void leftRotate() {
        //创建根结点，以当前结点的值创建
        Node newNode = new Node(value);
        //新结点的左子树设置为当前节点的左子树
        newNode.left = left;
        //新结点的右子树设置为当前节点的右子树的左子树
        newNode.right = right.left;
        //当前结点的值改为右结点的值
        value = right.value;
        //当前结点的右子树设置为右子树的右子树
        right = right.right;
        //当前结点的左子树设置为新结点
        left = newNode;
    }

    //进行右旋转
    public void rightRotate() {
        //创建新结点
        Node newNode = new Node(value);
        //新结点的右子树设置为当前结点的右子树
        newNode.right = right;
        //新结点的左子树设置为当前结点的左子树的右子树
        newNode.left = left.right;
        //当前节点的值修改为左子树的值
        value = left.value;
        //当前节点的左子树设置为左子树的左子树
        left = left.left;
        //当前结点的右子树设置为新结点
        right = newNode;
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

        //当右子树的高度-左子树高度>1时，进行左旋转，
        if (rightHeight() - leftHeight() > 1) {
            //如果当前节点的右子树的左子树高度大于它右子树的高度
            if (right != null && right.leftHeight() > right.rightHeight()){
                right.rightRotate();
                leftRotate();
            }else{
                leftRotate();
            }
            return;
        }
        //当左子树的高度-右子树高度>1时，进行右旋转，
        if (leftHeight() - rightHeight() > 1) {
            //如果当前节点的左子树的右子树高度大于它左子树的高度
            if (left != null && left.rightHeight() > left.leftHeight()){
                left.leftRotate();
                rightRotate();
            }else{
                rightRotate();
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