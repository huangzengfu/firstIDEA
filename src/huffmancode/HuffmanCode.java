package huffmancode;

import java.io.*;
import java.util.*;

/**
 * @author hf
 * @createtime 2020-11-13-14:07
 **/
public class HuffmanCode {
    public static void main(String[] args) {
        /*String content = "i like like like java do you like a java";
        //将原始字符串转为二进制数组
        byte[] contentBytes = content.getBytes();
        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
        System.out.println("压缩后的字节数组" + Arrays.toString(huffmanCodeBytes));

        //解码
        byte[] huffmanDeodeBytes = decode(huffmanCodes, huffmanCodeBytes);
        System.out.println("解压后数组" + new String(huffmanDeodeBytes));*/

        //测试文件压缩
        /*String srcFile = "C:\\Users\\30509\\Desktop\\sql.txt";
        String dstFile = "C:\\Users\\30509\\Desktop\\sql.zip";
        zipFile(srcFile,dstFile);
        System.out.println("压缩成功~");*/
        //测试文件解压
        String srcFile = "C:\\Users\\30509\\Desktop\\sql.zip";
        String dstFile = "C:\\Users\\30509\\Desktop\\src2.txt";
        unZipFile(srcFile,dstFile);
        System.out.println("解压成功~");

        /*System.out.println(Arrays.toString(contentBytes));
        List<Node> nodes = getNodes(contentBytes);
        System.out.println(nodes);

        System.out.println("哈夫曼树~");
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        System.out.println("前序遍历：");
        preOrder(huffmanTreeRoot);

        //测试生成哈夫曼编码
        //getCodes(huffmanTreeRoot, "", stringBuilder);
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        System.out.println("哈夫曼编码表~");
        System.out.println(huffmanCodes);

        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
        System.out.println("哈夫曼编码压缩后：");
        System.out.println(Arrays.toString(huffmanCodeBytes));*/
    }

    //创建一个总方法，将子功能封装起来，便于调用
    public static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        //根据创建的节点创建哈夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //根据哈夫曼树生成对应的哈夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //根据生成的哈夫曼编码，压缩得到压缩后的哈夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);

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
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //利用huffmanCodes 将bytes 转成对应的哈夫曼字符串
        StringBuilder stringbuilder = new StringBuilder();
        //遍历byte[]
        for (byte b : bytes) {
            String strbyte = huffmanCodes.get(b);
            stringbuilder.append(strbyte);
        }

        //将产生的原始字符串的哈夫曼编码二进制串进行处理，转成byte[]
        //统计返回的byte[]的长度
        int len;
        if (stringbuilder.length() % 8 == 0) {
            len = stringbuilder.length() / 8;
        } else {
            len = stringbuilder.length() / 8 + 1;
        }

        //创建压缩后的byte[]
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringbuilder.length(); i += 8) {//每八位对应一个byte
            String strByte = "";
            //将strByte转成一个byte，放入到
            if (i + 8 > stringbuilder.length()) {
                strByte = stringbuilder.substring(i);
            } else {
                strByte = stringbuilder.substring(i, i + 8);
            }
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    /*
     *将一个byte转成二进制字符串
     * b:是传入的byte
     * flag:标志是否需要补高位，如果是true需要补位，否则不补
     * */
    public static String byteToBitString(boolean flag, byte b) {
        //使用变量保存b
        int temp = b;
        //如果是正数需要补高位
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);//返回的是temp 对应的二进制补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    //编写方法，进行哈夫曼解码
    /*
     * huffmanCodes：哈夫曼编码表
     * huffmanBytes：待解码的字节数组
     * */
    public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //1、先得到huffmanBytes对应的二进制字符串，形如10100101...
        StringBuilder stringBuilder = new StringBuilder();
        //2、将字节数组转为二进制字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, huffmanBytes[i]));
        }

        //2、将字符串按照指定的哈夫曼编码进行解码
        //把哈夫曼编码表进行换位，反转查询 a->100,100->a
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        //创建一个集合，存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;//说明还没匹配到，继续往后截取
                } else {
                    flag = false;//哈夫曼编码匹配到
                }

            }
            list.add(b);
            i += count;
        }
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    //编写方法，对一个文件进行压缩
    /*
    * srcFile:希望进行压缩的文件路径
    * dstFile:输出的文件路径
    * */
    public static void zipFile(String srcFile,String dstFile){
        //创建输出流
        FileInputStream is = null;
        FileOutputStream os = null;
        ObjectOutputStream oos = null;
        try{
            //创建文件的输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小相等的byte[]
            byte[] b = new byte[is.available()];
            is.read(b);

            byte[] huffmanBytes = huffmanZip(b);

            //创建文件输出流，存放压缩后的文件
            os = new FileOutputStream(dstFile);

            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把哈夫曼编码后的字节数组写入到压缩文件
            oos.writeObject(huffmanBytes);
            //这里我们以对象流的形式写入哈夫曼编码，为了在回复源文件的时候使用
            //将哈夫曼编码写入哈夫曼编码
            oos.writeObject(huffmanCodes);

        }catch(Exception e){
            System.out.println(e.getMessage());

        }finally {
            try{
                is.close();
                os.close();
                oos.close();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }

        }
    }

    //编写方法，对压缩文件进行解压
    public static void unZipFile(String srcFile,String dstFile){
        //定义一个文件输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream  ois = null;
        //定义文件的输出流
        OutputStream os = null;
        try{
            //创建文件输入流
            is = new FileInputStream(srcFile);
            //创建一个和is相关的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组的huffmanBytes
            byte[] huffmanBytes = (byte[])ois.readObject();
            //读取哈夫曼编码表
            Map<Byte,String> huffmanCodes = (Map<Byte,String>)ois.readObject();

            //解码
            byte[] bytes = decode(huffmanCodes,huffmanBytes);
            //将bytes数组写入到目标文件中
            os = new FileOutputStream(dstFile);
            //写入数组到dstFile文件
            os.write(bytes);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            try{
                is.close();
                ois.close();
                os.close();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

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
