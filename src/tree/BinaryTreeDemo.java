package tree;

/**
 * @author hf
 * @createtime 2020-10-29-20:55
 **/
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //创建一个二叉树
        BinaryTree binaryTree = new BinaryTree();

        //创建需要的节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        //手动创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        //前序遍历
        System.out.println("前序遍历~");
        binaryTree.preOrder();

        //中序遍历
        System.out.println("中序遍历~");
        binaryTree.infixOrder();

        //前序遍历
        System.out.println("后序遍历~");
        binaryTree.postOrder();
    }
}

//构建树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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

    //前序查找
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

    //构造器

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
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
            }else{
                this.right = null;
            }
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no) ;
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
