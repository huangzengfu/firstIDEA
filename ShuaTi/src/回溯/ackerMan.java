package 回溯;

import java.util.Stack;

/**
 * @author hf
 * @createtime 2021-01-02-14:49
 **/
public class ackerMan {
    public static void main(String[] args) {
        long res = solve(1,1);
        System.out.println(res);
    }

    //思路：使用栈来模拟递归函数。有点难理解
    public static long solve(long m,long n){
        Stack<Ack> stack = new Stack<>();
        stack.push(new Ack(m,n));   //放入要求解的ack
        //res用来记录ack(0,n)的值
        //如果res大于0，则说明当前的函数层层调用(push)已经到底了，开始pop了。
        long res = -1;
        while(!stack.empty()){
            Ack ack = stack.peek(); //对栈顶的ack进行分析
            if(ack.m == 0){         //m为0,则求解出ack(0,n)的结果赋给res，并移出stack
                res = ack.n+1;
                stack.pop();

            }else if(ack.n == 0 && ack.m > 0){
                if(res < 0) {
                    stack.push(new Ack(ack.m-1,1)); //res小于0，Ackermann(m,0) = Ackermann(m-1,1);
                }
                else {
                    stack.pop();    //res大于0，则说明已经计算过了，可以pop
                }
            }else{
                if(ack.data < 0){   //还没有赋值，只有维
                    if(res < 0){
                        stack.push(new Ack(ack.m,ack.n-1)); //计算Ackermann(m,n)所需要的Ackermann(m,n-1)
                    }else {
                        ack.data = res; //设置data是为了判断是否被计算
                        res = -1;       //重置为1
                        stack.push(new Ack(ack.m-1,ack.data));//Ackermann(m,n) = Ackermann(m-1,Ackermann(m,n-1))，这里的Ackermann(m,n-1)为data
                    }
                }else{
                    stack.pop();    //已经计算出来的ack值被用到了，所以就pop出来
                }
            }
        }
        return res;
    }
}

class Ack{
    public long m;
    public long n;
    public long data;

    public Ack(long m, long n) {
        this.m = m;
        this.n = n;
        this.data = -1;
    }
}
