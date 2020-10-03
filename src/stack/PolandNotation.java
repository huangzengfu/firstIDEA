package stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author hf
 * @createtime 2020-10-03-16:03
 **/
public class PolandNotation {
    public static void main(String[] args){
        //先定义一个逆波兰表达式 3 4 + 5 * 6 - 使用空格隔开
        String suffixExpression = "3 4 + 5 * 6 -";
        //思路：1、先将逆波兰式放在arrayList中
        //2、将Arraylist传给一个函数，遍历ArrayList配合栈完成计算

        List<String> rpnList = getListString(suffixExpression);
        System.out.println("rpnList="+rpnList);
//        int res = calculate(rpnList);
//        System.out.printf("逆波兰表达式%s 的结果是 %d",suffixExpression,res);
    }

    //将一个逆波兰式的数据和运算符 放到 ArrayList中
    public static List<String> getListString(String suffixExpression){
        //将 表达式分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
//        Collections.addAll(list, split);
        for (String elem:split){
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
    public static int calculate(List<String> ls){
        //创建一个栈
        Stack<String> stack = new Stack<String>();
        //遍历ls
        for(String item:ls){
            if(item.matches("\\d+")){
                stack.push(item);
            }else{
                //从栈中pop出两个数，进行运算
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                switch(item){
                    case "+":
                        res = num1+num2;
                        break;
                    case "-":
                        res = num2-num1;
                        break;
                    case "*":
                        res = num1*num2;
                        break;
                    case "/":
                        res = num2/num1;
                        break;
                    default:
                        throw new RuntimeException("运算符有误~");

                }
                stack.push(res+"");
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
