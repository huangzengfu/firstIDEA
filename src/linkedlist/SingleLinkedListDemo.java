package linkedlist;

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
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);
        //显示单链表
        singleLinkedList.list();
    }
}

//定义SingleLinkedList  管理任务
class SingleLinkedList {
    //先初始化一个头节点，头节点不做修改，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

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
        return "HeroNode{" + "no=" + no + ", name='" + name + '\'' + ", nickname='" + nickname + '\''+"}";
    }
}
