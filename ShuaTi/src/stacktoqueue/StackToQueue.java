package stacktoqueue;

import java.util.Stack;

/**
 * @author hf
 * @createtime 2020-12-16-10:45
 **/
//用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
public class StackToQueue {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        Integer temp = null;
        if (stack2.empty()) {
            while (!stack1.empty()) {
                temp = stack1.pop();
                stack2.push(temp);
            }
        }
        return stack2.pop();

    }
}
