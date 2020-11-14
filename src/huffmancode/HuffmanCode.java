package huffmancode;

import java.util.*;

/**
 * @author hf
 * @createtime 2020-11-13-14:07
 **/
public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        //将原始字符串转为二进制数组
        byte[] contentBytes = content.getBytes();
        System.out.println(Arrays.toString(contentBytes));
        List<Node> nodes = getNodes(contentBytes);
        System.out.println(nodes);

        System.out.println("哈夫曼树~");
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        System.out.println("前序遍历：");
        preOrder(huffmanTreeRoot);

        //测试生成哈夫曼编码
//        getCodes(huffmanTreeRoot, "", stringBuilder);
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        System.out.println("哈夫曼编码表~");
        System.out.println(huffmanCodes);

        byte[] huffmanCodeBytes = zip(contentBytes,huffmanCodes);
        System.out.println("哈夫曼编码压缩后：");
        System.out.println(Arrays.toString(huffmanCodeBytes));
    }

    //创建一个总方法，将子功能封装起来，便于调用
    public static byte[] huffmanZip(byte[] bytes){
        List<Node> nodes = getNodes(bytes);
        //根据创建的节点创建哈夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //根据哈夫曼树生成对应的哈夫曼编码
        Map<Byte,String> huffmanCodes = getCodes(huffmanTreeRoot);
        //根据生成的哈夫曼编码，压缩得到压缩后的哈夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes,huffmanCodes);

        return huffmanCodeBytes;
    }

    //前序遍历
    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("哈夫曼树为空~");
        }
    }

    //将字符串转换为节点集合
    private static List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList<>();
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        //把每个键值对转换成一个Node对象，并加入到nodes中
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //通过list创建哈夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            //获取前两个节点
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //创建新节点
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            //将已经处理的而两个二叉树删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新创建的节点添加到nodes中
            nodes.add(parent);
        }
        //返回nodes最后的节点
        return nodes.get(0);
    }

    //生成哈夫曼树对应的哈夫曼编码
    /*
     * 思路
     * 1、将哈夫曼表存放在Map<Byte,String>形式，如：32->01,97->100等
     * */
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    /*
     * 2、在生成哈夫曼表时，需要拼接路径，定义一个StringBuilder存放某个叶子节点的路径
     * */
    static StringBuilder stringBuilder = new StringBuilder();

    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        //处理root左子树
        getCodes(root.left, "0", stringBuilder);
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /*
     * 功能：将传入的node节点的所有叶子结点的哈夫曼编码得到，并放入到huffmanCodes
     * node:传入结点
     * code:路径编码：左子节点的是0，右子节点是1
     * stringbuilder用于拼接路径
     * */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code加入到stringbuilder2中
        stringBuilder2.append(code);
        if (node != null) {//判断当前节点是否为空
            //判断当前节点是否是非叶子结点
            if (node.data == null) {
                //递归处理
                //向左递归
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else {//说明是个叶子节点
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    //编写方法：将字符串对应的byte[]数组，通过生成赫夫曼编码表，返回一个哈夫曼编码压缩后的byte[]
    /*
    *bytes:原始字符串对应的二进制编码
    * huffmanCodes:生成的哈夫曼编码表Map
    * 返回哈夫曼编码处理后的byte[]
    * */
    private static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){
        //利用huffmanCodes 将bytes 转成对应的哈夫曼字符串
        StringBuilder stringbuilder = new StringBuilder();
        //遍历byte[]
        for(byte b:bytes){
            String strbyte = huffmanCodes.get(b);
            stringbuilder.append(strbyte);
        }

        //将产生的原始字符串的哈夫曼编码二进制串进行处理，转成byte[]
        //统计返回的byte[]的长度
        int len;
        if(stringbuilder.length() % 8 ==0){
            len = stringbuilder.length()/8;
        }else{
            len = stringbuilder.length()/8 +1;
        }

        //创建压缩后的byte[]
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for(int i = 0;i<stringbuilder.length();i+=8){//每八位对应一个byte
            String strByte = "";
            //将strByte转成一个byte，放入到
            if(i+8>stringbuilder.length()){
                strByte = stringbuilder.substring(i);
            }else{
                strByte = stringbuilder.substring(i,i+8);
            }
            huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte,2);
            index++;
        }
        return huffmanCodeBytes;
    }

}

//创建节点，存放数据和权值
class Node implements Comparable<Node> {
    Byte data; //存放数据（字符）本身，比如'a'=>97,' '=>32
    int weight;//权重
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

}
