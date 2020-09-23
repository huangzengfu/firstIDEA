package linkedlist;

/**
 * @author hf
 * @create 2020-09-22 22:38
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args){
        System.out.println("双向链表测试~");
        //先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        HeroNode2 hero5 = new HeroNode2(5, "关胜", "大刀");

        //创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        //删除测试
        doubleLinkedList.del(4);
        System.out.println("删除后的链表：");
        doubleLinkedList.list();

        //按顺序添加测试
        doubleLinkedList.addByOrder(hero5);
        System.out.println("按顺序添加后的链表：");
        doubleLinkedList.list();
    }
}

//创建一个双向链表的类
class DoubleLinkedList{
    //先初始化一个头结点，头结点不能做修改且不存放具体的数据
    private HeroNode2 head = new HeroNode2(0,"","");

    //返回头结点
    public HeroNode2 getHead(){
        return head;
    }

    //遍历双向链表方法
    public void list(){
        //判断链表时是否为空
        if(head.next == null){
            System.out.println("链表为空！");
            return;
        }
        HeroNode2 temp = head.next;
        while(true){
            if(temp == null){
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }

    //在双向链表结尾添加节点
    public void add(HeroNode2 heronode){
        //先定义一个辅助节点
        HeroNode2 temp = head;
        while(true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }//当退出循环时，当前节点就是最后一个
        //添加新节点
        temp.next = heronode;
        heronode.pre = temp;
    }

    //按照顺序添加
    public void addByOrder(HeroNode2 heronode){
        //定义辅助变量循环链表
        HeroNode2 temp = head;
        boolean flag = false;
        while(true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no == heronode.no){
                flag = true;
                break;
            }else if(temp.next.no > heronode.no){
                break;
            }
            temp = temp.next;
        }

        //通过判断flag的值来确定是否已经存在待插入节点
        if(flag){
            System.out.println("待插入节点已存在~");
        }else{
            heronode.pre = temp;
            heronode.next = temp.next;
            if(temp.next != null){
                temp.next.pre = heronode;
                temp.next = heronode;
            }else{
                temp.next = heronode;
            }
        }
    }

    //修改节点内容
    public void update(HeroNode2 newHeroNode){
        if(head.next == null){
            System.out.println("链表为空~");
        }
        HeroNode2 temp = head.next;
        boolean flag = false;//定义一个是否找到节点的标志，默认为false
        while(true){
            if(temp == null){//表示已经遍历到链表的最后
                break;
            }
            if(temp.no == newHeroNode.no){//表示已经找到待修改的节点
                flag = true;
                break;
            }
            temp = temp.next;//后移一维
        }

        //通过判断flag值来确定是否执行修改
        if(flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else{
            //没有找到节点
            System.out.printf("没有找到 编号%d 的节点，不能修改\n", newHeroNode.no);
        }
    }

    //删除节点
    public void del(int no){
        if(head.next == null){
            System.out.println("链表为空，无法删除~");
        }

        HeroNode2 temp = head.next;
        boolean flag = false; //标志是否找到待删除的节点
        while(true) {
            if (temp == null) {//
                break;
            }
            if(temp.no == no){
                //找到待删除的节点，修改flag值
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断flag
        if(flag){
            //找到节点，可以删除
            temp.pre.next = temp.next;
            if(temp.next != null){
                temp.next.pre = temp.pre;
            }
        }else{
            System.out.printf("要删除的节点%d不存在~\n",no);
        }

    }
}


//定义双链表节点
class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 pre;//指向前一个节点，默认为空
    public HeroNode2 next;//指向下一个节点，默认为空

    //构造器
    public  HeroNode2(int no,String name,String nickname){
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
