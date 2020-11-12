package huffmantree;

import java.util.*;

/**
 * @author hf
 * @createtime 2020-11-12-10:38
 **/
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,4,1};
        Node root = createHuffmanTree(arr);
        preOrder(root);
    }

    //哈夫曼树签署遍历
    public static void preOrder(Node root){
        if(root!=null){
            root.preOrder();
        }else{
            System.out.println("哈夫曼树为空~");
        }
    }
    //创建哈夫曼树
    public static Node createHuffmanTree(int[] arr){
        //遍历数组，将每个元素构建成节点，放到arrayl中
        List<Node> nodes = new ArrayList<Node>();
        for(int value:arr){
            nodes.add(new Node(value));
        }

        Collections.sort(nodes);
        System.out.println(nodes);

        while(nodes.size() >1){
            //取出集合中最小的两个元素
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            //以原来集合中的最小两个节点创建一个新节点
            Node parent = new Node(leftNode.value +rightNode.value);
            //parent的子节点是原来最小的两个节点
            parent.left = leftNode;
            parent.right = rightNode;
            //将新节点加入集合中
            nodes.add(parent);
            //去掉原来最小的两个元素
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            Collections.sort(nodes);
        }

        return nodes.get(0);

    }
}

//创建节点
class Node implements Comparable<Node>{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }
    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node node) {
        return this.value - node.value;
    }
}
