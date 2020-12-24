package stackdefine;

import java.util.Stack;

/**
 * @author hf
 * @createtime 2020-12-20-14:27
 **/
public class stackDefine {
    Stack<Integer> stack = new Stack<Integer>();
    Stack<Integer> stackhandler = new Stack<Integer>();
    public void push(int node) {
        stack.push(node);
        if(stackhandler.empty()){
            stackhandler.push(node);
        }else{
            if(node <= stackhandler.peek()){
                stackhandler.push(node);
            }
        }
    }

    public void pop() {
        if(stack.empty()){
            return;
        }
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return stackhandler.peek();
    }
}
