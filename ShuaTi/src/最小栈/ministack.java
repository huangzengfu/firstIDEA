package 最小栈;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author hf
 * @createtime 2021-05-27-10:26
 **/
public class ministack {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(1);
        minStack.push(2);
        minStack.push(3);


    }
}

class MinStack {

    Stack<Integer> xStack = null;
    Stack<Integer> minStack = null;//辅助栈

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        xStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        xStack.push(val);
        if (minStack.isEmpty()) {
            minStack.push(val);
        } else {
            minStack.push(Math.min(val, minStack.peek()));
        }
    }

    public void pop() {
            xStack.pop();
            minStack.pop();
    }

    public int top() {
        return xStack.peek();

    }

    public int getMin() {
        return minStack.peek();
    }
}

