package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author hf
 * @createtime 2020-10-03-16:03
 **/
public class PolandNotation {
    public static void main(String[] args) {
        /*逆波兰表达式求值*/
//        //先定义一个逆波兰表达式 3 4 + 5 * 6 - 使用空格隔开
//        String suffixExpression = "3 4 + 5 * 6 -";
//        //思路：1、先将逆波兰式放在arrayList中
//        //2、将Arraylist传给一个函数，遍历ArrayList配合栈完成计算
//
//        List<String> rpnList = getListString(suffixExpression);
//        System.out.println("rpnList="+rpnList);
//        int res = calculate(rpnList);
//        System.out.printf("逆波兰表达式%s 的结果是 %d",suffixExpression,res);
        /*中缀表达式转成后缀表达式*/
        //思路：1、1+((2+3)*4)-5 转成 1 2 3 + 4 * + 5 -
        //2、因为直接对str进行操作不方便，因此先将中缀表达式转成对应的list
        //3、将得到的中缀表达式对应的list 转为 后缀表达式的list
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpression = toInfixExpression(expression);
        System.out.println(infixExpression);
    }

    //将一个逆波兰式的数据和运算符 放到 ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将 表达式分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
//        Collections.addAll(list, split);
        for (String elem : split) {
            System.out.println(elem);
            list.add(elem);
        }
        return list;
    }

    //完成对逆波兰表达式的计算
    /*
     * 1．从左至右扫描，将 3 和 4 压入堆栈；
     * 2．遇到+运算符，因此弹出 4 和 3（4 为栈顶元素，3 为次顶元素），计算出 3+4 的值，得 7，再将 7 入栈；
     * 3．将 5 入栈；
     * 4．接下来是×运算符，因此弹出 5 和 7，计算出 7×5=35，将 35 入栈；
     * 5．将 6 入栈；
     * 6．最后是-运算符，计算出 35-6 的值，即 29，由此得出最终结果
     * */
    public static int calculate(List<String> ls) {
        //创建一个栈
        Stack<String> stack = new Stack<String>();
        //遍历ls
        for (String item : ls) {
            if (item.matches("\\d+")) {
                stack.push(item);
            } else {
                //从栈中pop出两个数，进行运算
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                switch (item) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num2 - num1;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num2 / num1;
                        break;
                    default:
                        throw new RuntimeException("运算符有误~");

                }
                stack.push(res + "");
            }
        }
        return Integer.parseInt(stack.pop());
    }

    //将中缀表达式转成对应的list
    public static List<String> toInfixExpression(String s) {
        //定义一个List
        List<String> ls = new ArrayList<String>();
        int i = 0;//用于遍历字符串
        StringBuilder str;// 对多位数的拼接
        char c;
        do {
            //如果字符是一个操作符，就加入到老师
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add(c + "");
                i++;
            } else {//如果是一个数字，需要考虑多位数的拼接
                str = new StringBuilder();//先将str置空
                while (i < s.length() && 48 <= (c = s.charAt(i)) && (c = s.charAt(i)) <= 57) {
                    str.append(c);
                    i++;
                }
                ls.add(str.toString());
            }
        } while (i < s.length());
        return ls;
    }

    //将得到的中缀表达式的list转为 后缀表达式的list
    public static List<String> parseSuffixExpression(List<String> ls){
        //定义两个栈
        Stack<String> s1 = new Stack<String>();//符号栈
        //说明：因为s2这个栈在整个转换过程中，没有pop操作，而且后面还要逆序输出
        //因此定义栈比较麻烦，这里直接用List<String> s2代替
//        Stack<String> s2 = new Stack<String>();//存放中间结果栈
        List<String> s2 = new ArrayList<String>();

        //遍历ls
        for(String item:ls){
            //如果是个数，加入s2
            if(item.matches("\\d+")){
                s2.add(item);
            } else if(item.equals("(")){
                s1.push(item);
            }else if (item.equals(")")){
                //如果是右括号“）”,则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while(!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//！！！将左括号出栈，消除小括号
            }else{
                //当item的优先级小于s1栈顶的运算符，将s1栈顶的运算符弹出并加入到s2中，然后再次与s1中新的栈顶运算符进行比较
                while(s1.size() !=0 && Operation.getValue(s1.peek())>=Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                s1.push(item);//还需将item压入栈
            }
        }

        //将s1中剩余的符号加入s2
        while(s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;
    }
}

//编写一个类Operation 可以返回一个运算符对应的优先级
class Operation{
    private static int ADD =1;
    private static int SUB =1;
    private static int MUL =2;
    private static int DIV =2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation){
        int result = 0;
        switch(operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符~");
                break;
        }
        return result;
    }
}
