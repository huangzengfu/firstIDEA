package linkedlist;

/**
 * @author hf
 * @createtime 2020-09-23-13:35
 **/
public class DoubleLinkedListDemo {
    public static void main(String[] args){
        System.out.println("双向链表测试~");

        //创建几个节点
        HeroNode3 hero1 = new HeroNode3(1, "宋江", "及时雨");
        HeroNode3 hero2 = new HeroNode3(2, "卢俊义", "玉麒麟");
        HeroNode3 hero3 = new HeroNode3(3, "吴用", "智多星");
        HeroNode3 hero4 = new HeroNode3(4, "林冲", "豹子头");

        //定义一个双链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        //添加节点
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.list();

        //更新节点
//        HeroNode3 newHero = new HeroNode3(4, "林冲", "豹子头~~");
//        doubleLinkedList.update(newHero);
        //删除节点
        System.out.println("删除测试~");
        doubleLinkedList.del(5);
        doubleLinkedList.list();
        //按顺序添加节点
//        System.out.println("按顺序添加");
//        doubleLinkedList.addByOrder(hero4);
        //显示一波
//        doubleLinkedList.list();
    }
}

//定义双向链表
//class DoubleLinkedList{
//    //先初始化一个头节点，不能做修改且不保存具体值
//    HeroNode3 head = new HeroNode3(0,"","");
//
//    //显示双向链表
//    public void list(){
//        if(head.next == null){
//            System.out.println("链表为空，不能打印！");
//        }
////        定义辅助变量循环链表
//        HeroNode3 temp = head.next;
//        while(true){
//            if(temp == null){
//                break;
//            }
//            System.out.println(temp);
//            temp = temp.next;
//        }
//    }
//    //在双向链表后加入节点
//    public void add(HeroNode3 heronode){
//        //定义辅助变量来遍历双链表
//        HeroNode3 temp = head;
//        while(true){
//            if(temp.next == null){
//                break;
//            }
//            temp = temp.next;
//        }//当退出循环时，temp就为最后一个元素
//        temp.next = heronode;
//        heronode.pre = temp;
//    }
//    //按照顺序添加
//    public void addByOrder(HeroNode3 heronode){
//        HeroNode3 temp = head;
//        boolean flag = false;// flag标志添加的编号是否存在，默认为false
//        while(true){
//            if(temp.next == null){
//                break;
//            }
//            if(temp.next.no == heronode.no){
//                flag = true;
//                break;
//            }else if(temp.next.no > heronode.no){
//                break;
//            }
//            temp = temp.next;
//        }
//        if(flag){
//            System.out.println("该节点已存在~");
//        }else{
//            heronode.next = temp.next;
//            heronode.pre = temp;
//            if(temp.next != null){
//                temp.next.pre = heronode;
//                temp.next = heronode;
//            }else{
//                temp.next = heronode;
//            }
//
//        }
//    }
//    //更新链表节点
//    public void update(HeroNode3 newHeroNode){
//        if(head.next == null){
//            System.out.println("链表为空，无法修改~");
//        }
//        HeroNode3 temp = head.next;
//        boolean flag =false;
//        while(true){
//            if(temp == null){
//                break;//找到了列表尾部还没找到
//            }
//            if(temp.no == newHeroNode.no){
//                flag = true;
//                break;
//            }
//            temp = temp.next;
//        }
//
//        if(flag){
//            temp.name = newHeroNode.name;
//            temp.nickname = newHeroNode.nickname;
//        }else{
//            System.out.println("没有找到待修改的节点~");
//        }
//    }
//    //删除双向链表的节点
//    public void del(int no){
//        if(head.next == null){
//            System.out.println("链表为空，无法删除~");
//        }
//        HeroNode3 temp = head.next;
//        boolean flag = false;
//        while(true){
//            if(temp == null){
//                break;
//            }
//            if(temp.no == no){
//                flag= true;
//                break;
//            }
//            temp = temp.next;
//        }
//        if(flag){
//            temp.pre.next = temp.next;
//            //判断当前节点是否是最后一个节点，若为最后一个节点则不执行
//            if(temp.next!=null){
//                temp.next.pre = temp.pre;
//            }
//        }else{
//            System.out.printf("要删除的%d 节点不存在",no);
//        }
//    }
//}
//定义双向链表
class DoubleLinkedList{
    //定义头节点
    HeroNode3 head = new HeroNode3(0,"","");
    //遍历双向链表
    public void list(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空~");
            return;
        }
        //定义辅助指针，帮助遍历
        HeroNode3 temp = head.next;
        while(true){
            System.out.println(temp);
            if(temp.next == null){
                break;//遍历完毕
            }

            temp = temp.next;
        }
    }
    //在链表最后添加节点
    public void add(HeroNode3 heronode){
        //定义辅助变量，循环找到最后的节点
        HeroNode3 temp = head;
        while(true){
            if(temp.next == null){
                break;//找到最后的节点
            }
            temp = temp.next;
        }
        //结束循环时，temp指向的是链表最后的节点
        temp.next = heronode;
        heronode.pre = temp;
    }
    //按照节点编号顺序添加
    public void addByOrder(HeroNode3 heronode){
        //定义辅助节点，用于循环查找带插入节点的前一个节点
        HeroNode3 temp = head;
        boolean flag = false;//用于标记带插入节点是否存在，默认为false
        while(true){
            if(temp.next == null){
                break;//查找到最后一个节点，退出循环
            }
            if(temp.next.no > heronode.no){
                break;
            }
            if(temp.next.no == heronode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //退出循环时，temp此时指向的是带插入节点的前一个节点
        if(flag){
            System.out.println("带插入节点已存在~");
        }
        else{
            //先处理带插入节点本身
            heronode.pre = temp;
            heronode.next = temp.next;
            //在处理temp.next,判断是否为空
            if(temp.next == null){
                temp.next = heronode;
            }else{
                temp.next.pre = heronode;
                temp.next = heronode;
            }
        }
    }
    //更新节点信息
    public void update(HeroNode3 heronode){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空~");
            return;
        }
        HeroNode3 temp = head.next;
        boolean flag = false;//标记是否找到待修改节点，默认为false
        while (true){
            if(temp == null)
            {
                break;
            }
            if(temp.no == heronode.no)
            {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.name = heronode.name;
            temp.nickname = heronode.nickname;
        }else{
            System.out.println("未找到待修改节点~");
        }

    }
    public void del(int no){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空~");
            return;
        }
        //定义辅助节点帮助查找待删除节点
        HeroNode3 temp = head.next;
        boolean flag = false;
        while(true){
            if(temp == null){
                break;
            }
            if(temp.no  == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //退出循环时，temp指向的是待删除的节点
        if(flag){
            temp.pre.next = temp.next;
            if(temp.next != null){
                temp.next.pre = temp.pre;
            }
        }else{
            System.out.println("未找到待删除的节点~");
        }
    }
}

//定义双向链表的节点
class HeroNode3{
    public int no;
    public String name;
    public String nickname;
    public HeroNode3 pre;//指向前一个节点
    public HeroNode3 next;//指向后一个节点

    //节点构造器
    public HeroNode3(int no,String name,String nickname){
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode3{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
