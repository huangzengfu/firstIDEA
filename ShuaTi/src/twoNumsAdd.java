

/**
 * @author hf
 * @createtime 2021-03-11-8:39
 **/
public class twoNumsAdd {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(3, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(7, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(9, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(2, "林冲", "豹子头");
//        HeroNode hero5 = new HeroNode(9, "林冲", "豹子头");
//        HeroNode hero6 = new HeroNode(9, "林冲", "豹子头");
//        HeroNode hero7 = new HeroNode(9, "林冲", "豹子头");
//        HeroNode hero8 = new HeroNode(9, "林冲", "豹子头");
//        HeroNode hero9 = new HeroNode(9, "林冲", "豹子头");
//        HeroNode hero10 = new HeroNode(9, "林冲", "豹子头");
//        HeroNode hero11 = new HeroNode(9, "林冲", "豹子头");

        SingleLinkedList list1 = new SingleLinkedList();
        SingleLinkedList list2 = new SingleLinkedList();

        list1.add(hero1);
        list1.add(hero2);
//        list1.add(hero3);
//        list1.add(hero4);
//        list1.add(hero5);
//        list1.add(hero6);
//        list1.add(hero3);
        list2.add(hero3);
        list2.add(hero4);
//        list2.add(hero10);
//        list2.add(hero11);

        HeroNode node = addTwoNumbers(hero1,hero3);
        while(node!=null){
            System.out.println(node);
            node = node.next;
        }


    }

    public static HeroNode addTwoNumbers(HeroNode l1, HeroNode l2) {
        int addFactor = 0;
        int addRes = 0;
        HeroNode node = new HeroNode(0);
        HeroNode temp = node;
        while(l1 != null&&l2 != null){
            addRes= (l1.no +l2.no+addFactor)%10;
            temp.next = new HeroNode(addRes);
            temp = temp.next;
            addFactor = (l1.no +l2.no+addFactor)/10;
            l1=l1.next;l2=l2.next;
        }
        while(l1!=null){
            addRes= (l1.no +addFactor)%10;
            temp.next = new HeroNode(addRes);
            addFactor = (l1.no +addFactor)/10;
            l1 = l1.next;temp = temp.next;
        }
        while(l2!=null){
            addRes= (l2.no +addFactor)%10;
            temp.next = new HeroNode(addRes);
            addFactor = (l2.no +addFactor)/10;
            l2 = l2.next;temp = temp.next;
        }
        if(addFactor!=0){
            temp.next = new HeroNode(addFactor);
        }
//        if(l2!=null){
//            temp.next = new HeroNode(addFactor+l2.no);
//            while(l2.next!=null){
//                temp.next = l2.next;
//                l2.next = l2.next.next;
//            }
//        }
        return node.next;
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
    public HeroNode(int no){
        this.no = no;
    }

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