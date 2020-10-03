package stack;

/**
 * @author hf
 * @createtime 2020-09-30-14:04
 **/
public class Calculator {
    public static void main(String[] args) {
        //定义一个中缀表达式
        String expression = "30+2*6-2";
        //定义两个栈：一个数栈，一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义相关的变量
        int index = 0;//用于扫描
        char ch = ' ';//每次扫描得到的字符存放ch
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        String keepNum = "";//用于保存多位数

        //while循环扫描expression
        do {
            //获取表达式中的每个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断获取的字符是什么，进行相关处理
            if (operStack.isOper(ch)) {//如果是运算符
                //判断当前栈是否为空
                if (!operStack.isEmpty()) {
                    //如果符号栈中有操作符，就进行比较，如果当前的操作符的优先级小于或者等于栈中的操作符，就需要从栈中pop出两个数
                    //从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                    } else {//当前的操作符优先级大于栈中的操作符
                        operStack.push(ch);
                    }
                } else {
                    //如果为空，直接入栈
                    operStack.push(ch);
                }
            } else {
                //numStack.push(ch - 48);//ch是一个字符，需要做ASCII码处理
                //处理多位数的情况
                keepNum += ch;
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }


            }

            //index++，继续扫描表达式
            index++;
        } while (index < expression.length());

        //当表达式扫描完毕，就顺序的从数栈和符号栈中取出数和操作符，进行计算
        while (true) {
            //如果符号栈为空，则计算到最后的结果，且数栈中只有一个结果
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = operStack.cal(num1, num2, oper);
            numStack.push(res);

        }
        int res2 = numStack.pop();
        System.out.printf("表达式%s 的结果是%d", expression, res2);

    }
}

class ArrayStack2 {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[this.maxSize];
    }

    //返回栈顶的方法
    public int peek() {
        return stack[top];
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

    //返回运算符的优先级，用数字表示，数字越大，优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;//假定目前表达式中只有加减乘除
        }
    }

    //判断是否是运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}
