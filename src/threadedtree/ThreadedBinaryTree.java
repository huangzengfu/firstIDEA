package threadedtree;

/**
 * @author hf
 * @createtime 2020-11-07-14:21
 **/
public class ThreadedBinaryTree {
    public static void main(String[] args) {
        //中序线索化二叉树测试
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "simth");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        threadedTree tree = new threadedTree();
        tree.setRoot(root);
        tree.threadedNodes();

        HeroNode leftNode = node5.getLeft();
        System.out.println(leftNode);
    }
}

class threadedTree {
    private HeroNode root;

    //递归进行线索化时，pre总保留前一个节点
    private HeroNode pre;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void threadedNodes() {
        this.threadedNodes(root);
    }

    //遍历线索二叉树
    public void threadedList() {
        HeroNode node = root;
        while (node != null) {
            //循环找到lefttype =1的节点，随着遍历的变化，lefttype=1的节点是第一个线索化的节点
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            //打印当前节点
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点就继续输出
            while(node.getRightType() == 1){
                //获取到当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的节点
            node = node.getRight();

        }
    }

    //二叉树进行中序线索化
    public void threadedNodes(HeroNode node) {
        //如果node为空，不能线索化
        if (node == null) {
            return;
        }

        //线索化当前节点的左子树
        threadedNodes(node.getLeft());
        //线索化当前节点
        //1、处理当前节点的前驱节点
        if (node.getLeft() == null) {
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //2、处理后继节点
        if (pre != null && pre.getRight() == null) {
            //前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱节点右指针的类型
            pre.setRightType(1);
        }
        //注意！！!每处理完当前节点后，让当前节点的作为下一个节点前驱节点
        pre = node;
        //线索化当前节点的右子树
        threadedNodes(node.getRight());
    }

    //递归删除
    public void delNode(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.out.println("二叉树为空~");
        }
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历~");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历~");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历~");
        }
    }

    //前序查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return this.root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return this.root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return this.root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    //说明：1、如果leftType为0表示指向的是左子树，为1表示指向的是前驱节点
    //2、如果rightType为0表示指向的是右子树，为1表示指向的是后继节点
    private int leftType;
    private int rightType;

    //构造器
    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //递归删除节点
    /*1、如果删除的是叶子节点，直接删除
      2、如果删除的是非叶子节点，还需要删除该节点的子树
    * */
    public void delNode(int no) {
        /*思路*/
        /* 1. 因为我们的二叉树是单向的，所以我们是判断当前结点的子结点是否需要删除结点，而不能去判断 当前这个结点是不是需要删除结点
         * 2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将 this.left = null; 并且就返回 (结束递归删除)
         * 3. 如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将 this.right= null ;并且就返回 (结束递归删除)
         * 4. 如果第 2 和第 3 步没有删除结点，那么我们就需要向左子树进行递归删除 5. 如果第 4 步也没有删除结点，则应当向右子树进行递归删除.
         */
        //2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将 this.left = null; 并且就返回(结束递归删除)
        if (this.left != null && this.left.no == no) {
            if (this.left.left != null) {
                this.left = this.left.left;
            } else {
                this.left = null;
            }
            return;
        }
        if (this.right != null && this.right.no == no) {
            if (this.right.left != null) {
                this.right = this.right.left;
            } else {
                this.right = null;
            }
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }


    //前序遍历
    public void preOrder() {
        System.out.println(this);//先输出当前父节点
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后续遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序查找
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序遍历查找~");
        //比较当前节点
        if (this.no == no) {
            return this;
        }

        HeroNode resNode = null;
        //判断当前节点是否有左节点，若有进行左序递归
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        }

        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }

        return resNode;
    }

    //中序查找
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        if (this.no == no) {
            return this;
        }

        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }

        return resNode;
    }

    //后序查找
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        }

        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        }

        if (this.no == no) {
            return this;
        }
        return resNode;

    }
}
