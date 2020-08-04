package hf.java;

import java.util.HashMap;

public class DebuggerTest {
    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }

        HashMap<String,String> map = new HashMap<>();
        map.put("name","HF");
        map.put("age","23");
        map.put("school","Tsinghua");
        map.put("major","Computers");

        String age = map.get("age");
        System.out.println(age);
        System.out.println("age = " + age);

        map.remove("major");

        System.out.println(map);

    }
}
