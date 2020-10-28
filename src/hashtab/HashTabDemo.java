package hashtab;

import java.util.Scanner;

/**
 * @author hf
 * @createtime 2020-10-28-10:27
 **/
public class HashTabDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);
        //创建菜单实现功能
        String key = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("add:    添加雇员");
            System.out.println("list:   显示雇员");
            System.out.println("find:   查找雇员");
            System.out.println("del:    删除雇员");
            System.out.println("exit:   退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id：");
                    int id = scanner.nextInt();
                    System.out.println("输入姓名：");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("输入id：");
                    int no= scanner.nextInt();
                    hashTab.findEmpById(no);
                    break;
                case "del":
                    System.out.println("输入id：");
                    int num= scanner.nextInt();
                    hashTab.deleteEmpById(num);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

//创建HashTab,管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedListsArray;
    private int size;

    //构造器
    public HashTab(int size) {
        //初始化empLinkedListsArray
        this.size = size;
        empLinkedListsArray = new EmpLinkedList[size];
        //容易忽略对链表数组中每个链表的实例化
        for (int i = 0; i < size; i++) {
            empLinkedListsArray[i] = new EmpLinkedList();
        }
    }

    //遍历所有链表，即遍历哈希表
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListsArray[i].list(i);
        }
    }

    //添加雇员
    public void add(Emp emp) {
        //根据员工的id得到该员工应当添加到那条链表
        int empLinkedListNo = hashFun(emp.id);
        //将emp添加到对应链表中
        empLinkedListsArray[empLinkedListNo].add(emp);
    }

    //根据输入的id查找雇员
    public void findEmpById(int id){
        int empLinkedListNo = hashFun(id);
        Emp emp =  empLinkedListsArray[empLinkedListNo].findEmpById(id);
        if(emp!=null){
            System.out.printf("在%d条链表中找到雇员id=%d",empLinkedListNo,id);
        }else{
            System.out.printf("在哈希表中未找到id是%d的雇员",id);
        }
    }

    //根据输入的id删除雇员
    public void deleteEmpById(int id){
        int empLinkedListNo = hashFun(id);
        empLinkedListsArray[empLinkedListNo].deleteEmpById(id);
    }

    //写一个散列函数，用取模方法
    public int hashFun(int id) {
        return id % size;
    }
}

//定义雇员链表
class EmpLinkedList {
    private Emp head;

    //添加雇员
    public void add(Emp emp) {
        //如果添加的是第一个雇员，直接加载头节点
        if (head == null) {
            head = emp;
            return;
        }

        //如果不是第一个雇员
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;//说明到链表的最后
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    //遍历雇员
    public void list(int no) {
        if (head == null) {
            System.out.println("第" + (no + 1) + "链表为空~");
            return;
        }
        System.out.print("第" + (no + 1) + "链表的信息为：");
        Emp curEmp = head;
        while (true) {
            if (curEmp == null) {
                break;
            }
            System.out.printf("=> id=%d name=%s\t", curEmp.id, curEmp.name);
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    //根据id查找雇员，找到返回Emp,没找到返回null
    public Emp findEmpById(int id){
        //判断链表是否为空，为空返回null
        if(head == null){
            System.out.println("链表为空~");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while(true){
            if(curEmp.id == id){
                break;//如果找到终止循环
            }

            if(curEmp.next == null){
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

    //根据输入id删除
    public void deleteEmpById(int id){
        //首先判断是否为空
        if(head == null){
            System.out.println("链表为空~");
            return;
        }
        //头节点匹配情况
        if(head.id == id){
            head = head.next;
            return;
        }
        Emp curEmp = head;
        while(true){
            if(curEmp.next == null){
                break;
            }
            if(curEmp.next.id == id){
                curEmp.next = curEmp.next.next;
                break;
            }

            curEmp = curEmp.next;
        }
    }

}

//表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next;//默认为空

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}
