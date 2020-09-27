package linkedlist;

import java.util.Stack;

/**
 * @author hf
 * @create 2020-09-19 15:39
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //定义一些节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        //定义一个单量表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //单链表添加节点
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero4);
        //测试修改节点
        singleLinkedList.list();
//        HeroNode newHeroNode = new HeroNode(4, "小吴", "智多星~~");
//        singleLinkedList.update(newHeroNode);
//        System.out.println("修改后链表的数据：\n");
//        singleLinkedList.list();

        System.out.println("测试删除：\n");
        singleLinkedList.del(5);
        singleLinkedList.list();

       /* //获取单链表有效节点个数
        System.out.println("\n有效的节点个数="+getLength(singleLinkedList.getHead()));
        //查找单链表中的倒数第k个节点
        HeroNode res = findLastIndexNode(singleLinkedList.getHead(),2);
        System.out.println("res="+res);*/

        /*//单链表反转
        System.out.println("反转前的链表~~");
        singleLinkedList.list();
        System.out.println("反转后的链表~~");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();
        //用栈将单链表逆序输出
        System.out.println("用栈逆序输出：\n");
        reversePrint(singleLinkedList.getHead());*/

    }

    //方法：获取到单链表节点的个数（如果带头节点，需求不统计头节点）
    /*
    * head 链表的头节点
    * 返回的就是链表的有效节点个数
    * */
    public static int getLength(HeroNode head){
        if(head.next == null){
            //说明为空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助的变量
        HeroNode cur = head.next;//cur定义为head.next 就是指没有统计头节点
        while(cur != null){
            length++;
            cur = cur.next;
        }
        return length;

    }
    //查找单链表中的倒数第k个节点【新浪面试题】
    /*
    * 思路：
    * 1、编写一个方法，接收head节点，同时接收一个index
    * 2、index 表示是倒数第index个节点
    * 3、先把链表从头到尾遍历，得到链表的中长度 getLength
    * 4、得到size 后，从链表的第一个开始遍历（size-index）个就可以得到，找不到返回null
    * */
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        //判断如果链表为空，则返回null
        if(head.next == null){
            return null;
        }
        //第一次遍历得到链表的长度
        int size = getLength(head);
        //第二次遍历 size-index 位置，就是倒数的第K个节点
        //先做一个index的校验
        if(index <= 0||index > size){
            return null;
        }
        //定义辅助变量，for循环定位到倒数的index
        HeroNode cur = head.next;
        for(int i=0;i<size-index;i++){
            cur = cur.next;
        }
        return cur;
    }
    //实现单链表的反转
    public static void reverseList(HeroNode head){
        //如果链表为空或者只有一个节点，直接返回
        if(head.next == null||head.next.next==null){
            return;
        }
        HeroNode cur = head.next;//当前链表的第一个节点
        HeroNode reverseHead = new HeroNode(0,"","");//定义一个新的头结点用来存放反转的链表
        HeroNode next = null;//用来存放当前节点的后一个节点，当前节点被操作后已经不再原来的链表

        while (cur != null){
            next = cur.next;
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
        head.next = reverseHead.next;

    }
    //实现单链表的逆序输出（栈）
    public static void reversePrint(HeroNode head){
        if(head.next == null){
            return;//空表，不打印
        }

        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        //将链表的所有节点压入stack中
        while(cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        //将栈中的数据输出
        while(stack.size()>0){
            System.out.println(stack.pop());
        }

    }
}

//定义SingleLinkedList  管理任务
class SingleLinkedList {
    //先初始化一个头节点，头节点不做修改，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //然后添加节点到单向链表
    //思路，当不考虑编号顺序时
    //1、找到当前链表的最后节点
    //2、将最后节点的next 指向 新的节点
    public void add(HeroNode heroNode) {
        //head节点不能修改，因此需要一个辅助变量
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表最后的节点
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后的节点，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了最后的节点
        temp.next = heroNode;
    }

    //第二种添加人物时，根据排名将英雄插入到指定位置
    public void addByOrder(HeroNode heroNode) {
        //头结点不能修改，仍然通过一个辅助指针来找到添加的位置
        //因为是单链表，我们找的temp 是位于添加位置的前一个节点，否则无法插入
        HeroNode temp = head;
        boolean flag = false;// flag标志添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {//说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) {//位置找到，就在temp的后边插入
                break;
            } else if (temp.next.no == heroNode.no) {//说明希望添加的heroNode的编号已经存在

                flag = true; //说明编号已经存在
                break;
            }
            temp = temp.next;
        }
        //是否要添加，先判断flag 的值
        if (flag) {//此时flag为true，说明编号已经存在
            System.out.printf("准备插入的英雄的编号%d已经存在，不能插入\n", heroNode.no);
        } else {
            //插入到链表中，在temp后边
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点的信息，根据编号no来修改，即编号no不能修改
    //1、根据heroNode 的no 来修改信息
    public void update(HeroNode newHeroNode) {
        // 首先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改的节点，根据编号no
        HeroNode temp = head.next;
        boolean flag = false;//flag用来确定节点是否找到，默认为false
        while (true) {
            if (temp == null) { //链表遍历完毕
                break;
            }
            if(temp.no == newHeroNode.no){
                //找到节点
                flag=true;
                break;
            }
            temp = temp.next;
        }

        //根据flag 判断是否找到要修改的节点
        if(flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else{
            //没有找到节点
            System.out.printf("没有找到 编号%d 的节点，不能修改\n", newHeroNode.no);
        }
    }

    //删除结点
    //思路
    //1、头结点不能修改，仍然通过一个辅助指针来找到添加的位置
    //2、说明：我们在比较时，是temp.next.no 和 需要删除的节点的编号no 做比较
    public void del(int no){
        if(head.next == null){
            System.out.println("链表为空~~");
        }
        HeroNode temp = head;
        boolean flag = false; //标志是否找到待删除的节点
        while(true) {
            if (temp.next == null) {//
                break;
            }
            if(temp.next.no == no){
                //找到待删除的节点，修改flag值
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断flag
        if(flag){
            //找到节点，可以删除
            temp.next = temp.next.next;
        }else{
            System.out.printf("要删除的节点%d不存在~\n",no);
        }

    }

    //遍历显示链表
    public void list() {
        //首先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        //head节点不能做修改，需要一个辅助变量来遍历链表
        HeroNode temp = head.next;
        while (true) {
            //判断是否到了链表的末尾
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp后移，否则死循环
            temp = temp.next;
        }
    }
}

//定义一个HeroNode，每个HeroNode 对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; //指向下一个节点

    //    构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;

    }

    //为显示方便，重新tostring
    @Override
    public String toString() {
        return "HeroNode{" + "no=" + no + ", name='" + name + '\'' + ", nickname='" + nickname + '\'' + "}";
    }
}
