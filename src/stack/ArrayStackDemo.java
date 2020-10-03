package stack;

import java.io.LineNumberReader;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * @author hf
 * @createtime 2020-09-29-14:23
 **/
public class ArrayStackDemo {
    public static void main(String[] args) {
        /*数组模拟栈*/
        //创建一个栈
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;//控制是否退出菜单
        Scanner scanner = new Scanner(System.in);
        String a = scanner.nextLine();
        System.out.println(a);

//        while (loop) {
//            System.out.println("show: 表示显示栈");
//            System.out.println("exit: 退出程序");
//            System.out.println("push: 表示添加数据到栈(入栈)");
//            System.out.println("pop: 表示从栈取出数据(出栈)");
//            System.out.println("请输入你的选择");
//
//            key = scanner.next();
//            switch (key) {
//                case "show":
//                    stack.list();
//                    break;
//                case "exit":
//                    loop = false;
//                    break;
//                case "push":
//                    System.out.println("请输入一个数");
//                    int value = scanner.nextInt();
//                    stack.push(value);
//                    break;
//                case "pop":
//                    try {
//                        int res = stack.pop();
//                        System.out.printf("出栈的数据是 %d\n", res);
//                    } catch (Exception e) {
//                        System.out.println(e.getMessage());
//                    }
//                    break;
//                default:
//                    break;
//            }
//        }
//        System.out.println("程序退出~");
       /*链表模拟栈*/
//        LinkedListStack stack = new LinkedListStack();

    }
}

class ArrayStack {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    //构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            throw new RuntimeException("栈满~");
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有数据~");
        }
        int temp = stack[top];
        top--;
        return temp;
    }

    //遍历栈
    public void list() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有数据~");
        }
        //从栈顶循环出栈
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
}

//class LinkedListStack {
//    //定义一个头节点
//    LinkedListStackNode head = new LinkedListStackNode(0);
//
//    public LinkedListStackNode getHead() {
//        return head;
//    }
//
//    //入栈
//    public void push(LinkedListStackNode node) {
//        //头节点不能做修改，定义辅助指针
////        LinkedListStackNode temp = head;
//        node.next = head.next;
//        head.next = node;
//    }
//
//    //出栈
//    public int pop() {
//        //判断栈是否为空
//        if (head.next == null) {
//            throw new RuntimeException("栈空，没有数据~");
//        }
//        int num = head.next.num;
//        head.next = head.next.next;
//        return num;
//
//
//    }
//
//    //遍历栈
//    public void list() {
//    //判断栈是否为空
//        if (head.next == null) {
//            throw new RuntimeException("栈空，没有数据~");
//        }
//        int num = head.next.num;
//        head.next = head.next.next;
//        System.out.printf("栈输出数据为%d\n",num);
//    }
//
//
//}

//class LinkedListStackNode {
//    public int num;
//    public LinkedListStackNode next;
//
//    //构造器
//    public LinkedListStackNode(int num) {
//        this.num = num;
//    }
//
//
//}
