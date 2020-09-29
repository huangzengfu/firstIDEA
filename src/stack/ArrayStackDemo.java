package stack;

import java.util.Scanner;

/**
 * @author hf
 * @createtime 2020-09-29-14:23
 **/
public class ArrayStackDemo {
    private static void main(String[] args){
        //创建一个栈
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;//控制是否退出菜单
        Scanner scanner = new Scanner(System.in);

        while(loop){
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");

            key = scanner.next();
            switch(key){
                case "show":
                    stack.list();
                    break;
                case "exit":
                    loop = false;
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是 %d\n", res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~");
    }
}

class ArrayStack{
    private int maxSize;
    private int[] stack;
    private int top = -1;

    //构造器
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull(){
        return top == maxSize -1;
    }
    //栈空
    public boolean isEmpty(){
        return top == -1;
    }
    //入栈
    public void push(int value){
        if(isFull()){
            throw new RuntimeException("栈满~");
        }
        top++;
        stack[top] = value;
    }
    //出栈
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空，没有数据~");
        }
        int temp = stack[top];
        top--;
        return temp;
    }
    //遍历栈
    public void list(){
        if(isEmpty()){
            throw new RuntimeException("栈空，没有数据~");
        }
        //从栈顶循环出栈
        for(int i = top;i>=0;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}

class LinkedListStack{

}
