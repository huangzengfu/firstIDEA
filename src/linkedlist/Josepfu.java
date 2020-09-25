package linkedlist;

/**
 * @author hf
 * @create 2020-09-24 23:00
 */
public class Josepfu {
    public static void main(String[] args){

    }
}

//创建一个环形的单项链表
class CircleSingleLinkedList{
    //创建一个first节点，当前没有编号
    private Boy first = new Boy(-1);
    //添加小孩节点，构成环形的链表
    public void addBoy(int nums){
        //nums 做一个数据校验
        if(nums<1){
            System.out.println("nums的值不正确！");
            return;
        }
        Boy curBoy = null;//辅助指针，帮助构建环形链表
        //使用for循环创建环形链表
        for(int i = 1;i<=nums;i++){
            //根据编号创建节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if(i==1){
                first = boy;
                first.setNext(boy);
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
