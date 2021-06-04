package Test;

/**
 * @author hf
 * @createtime 2021-05-27-18:52
 **/
public class dynamicLinkingTest {
    int num =10;

    public void methodA(){
        System.out.println("methodA()...............");
    }

    public void methodB(){
        System.out.println("methodB()..............");

        methodA();

        num++;
    }
}
