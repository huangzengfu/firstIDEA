package 回溯;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hf
 * @createtime 2020-12-28-20:07
 **/
public class telephoneNumToString {

    public static void main(String[] args) {
        String str = "321";
        letterCombinations(str);
        System.out.println(res);
    }

    private static String letterMap[] = {
            " ",    //0
            "",     //1
            "abc",  //2
            "def",  //3
            "ghi",  //4
            "jkl",  //5
            "mno",  //6
            "pqrs", //7
            "tuv",  //8
            "wxyz"  //9
    };

    private static ArrayList<String> res;

    public static List<String> letterCombinations(String digits) {
        res = new ArrayList<String>();
        if (digits.equals(""))
            return res;

        findCombination(digits, 0, "");
        return res;
    }

    private static void findCombination(String digits, int index, String s) {

        if (index == digits.length()) {
            res.add(s);
            return;
        }

        char c = digits.charAt(index);
        String letters = letterMap[c - '0'];
        for (int i = 0; i < letters.length(); i++) {
            findCombination(digits, index + 1, s + letters.charAt(i));
        }

        return;
    }
//    public static void main(String[] args) {
//        String str = "12";
//        String letterMap[] = {
//                " ",    //0
//                "",     //1
//                "abc",  //2
//                "def",  //3
//                "ghi",  //4
//                "jkl",  //5
//                "mno",  //6
//                "pqrs", //7
//                "tuv",  //8
//                "wxyz"  //9
//        };
//        String s ="";
//        int index = 0;
//        ArrayList<String> res = new ArrayList<String>();
//        getTransfarString(str,0,s,letterMap,res);
//        System.out.println(res);
//        System.out.println();
//        System.out.println(res.size());
//    }
//
//    public static void getTransfarString(String digits, int index, String s, String[] letterMap, ArrayList<String> res){
//        if(s.length() == digits.length()){
//            res.add(s);
//            return;
//        }
//        char c = digits.charAt(index);
//        String numObj = letterMap[c-'0'];
//        for(int i=0;i<numObj.length();i++){
////            s += numObj.charAt(i);
//            getTransfarString(digits,index+1,s+numObj.charAt(i),letterMap,res);
//        }
//    }

}
