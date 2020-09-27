package linkedlist;

/**
 * @author hf
 * @create 2020-09-24 23:00
 */
public class Josepfu {
    public static void main(String[] args){
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();
    }
}

//创建一个环形的单项链表
class CircleSingleLinkedList{
    //创建一个first节点，当前没有编号
    private Boy first = null;
    //添加小孩节点，构成环形的链表
    public void addBoy(int nums){
        //nums 做一个数据校验
        if(nums < 1){
            System.out.println("nums至少为1~");
            return;
        }
        //辅助指针，帮助构建环形链表
        Boy curBoy = null;
        //使用for循环创建环形链表
        for(int i = 1;i<=nums;i++){
            Boy boy = new Boy(i);
            //判断是否是第一个节点
            if(i == 1){
                first = boy;
                first.setNext(first);
                curBoy = first;
            }else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }

    }

    //遍历当前链表
    public void showBoy(){
        //判断链表是否为空
        if(first == null){
            System.out.println("没有任何小孩~~");
            return;
        }
        //因为first不能动，因此仍需要定义一个辅助指针遍历
        Boy curBoy = first;
        while(true){
            System.out.printf("小孩的编号%d\n",curBoy.getNo());
            if(curBoy.getNext() == first)
            {//说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    //根据用户输入，计算小孩出圈的顺序
    /*
    * startNo表示从第几个小孩开始
    * countNum 表示数几下
    * nums 表示最初有多少个小孩在圈内
    * */
    public void countBoy(int startNo,int countNum,int nums)
    {
        // 先对数据进行校验
        if(first == null||startNo<1||startNo>nums)
        {
            System.out.println("参数输入有误，请重新输入~");
            return;
        }
        //创建辅助指针，帮助表还出圈
        Boy helper=first;//先把helper定义为起点，然后遍历后赋值为起点前的数据
        //创建的指针helper，事先应该指向环形链表的最后节点
        while(true) {
            if (helper.getNext() == first)
            {
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first 和 helper 移动startNo -1 下让first指向开始的小孩
        for(int j=0;j<startNo -1;j++)
        {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数是，让first 和 helper 指针同时移动 countNum-1下，然后出圈
        //这是一个循环操作，知道最后只要一个小孩
        while(true){
            if(helper == first){//说明圈中只有一个小孩
                break;
            }

            //让first 和 helper 指针同时移动 countNum-1下，然后出圈
            for(int j= 0;j<countNum -1;j++)
            {
                first = first.getNext();
                helper = helper.getNext();
            }
            //退出循环时first 指向的节点，就是要出圈的小孩的节点
            System.out.printf("小孩%d出圈\n",first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩的编号是%d\n",helper.getNo());
    }
}

//创建一个boy类，表示一个节点
class Boy{
    private int no;//编号
    private Boy next;//指向下一个节点，默认为null
    //构造器
    public Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
