package 回溯;

import java.util.Arrays;

/**
 * @author hf
 * @createtime 2020-12-22-19:31
 **/
public class dfsCube {
    public static void main(String[] args) {
        String matrix = "ABCESFCSADEE";
        String str = "AF";
        boolean bool = hasPath(matrix.toCharArray(),3,4,str.toCharArray());
    }

    public static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (str.length == 0 || rows <= 0 || cols <= 0) {
            return false;
        }
        int n = 0;
        boolean[] isVisited = new boolean[rows * cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isHashPath(matrix, rows, cols, str, isVisited, i, j,n)) {
                    return true;
                }
            }
        }
        return false;

    }

    public static boolean isHashPath(char[] matrix, int rows, int cols, char[] str, boolean[] isVisited, int curx, int cury,int n) {

        if (cury <0||cury >= cols||curx <0 || curx >= rows||isVisited[curx*cols+cury]||!(str[n]==matrix[curx*cols+cury]))
        {
            return false;
        }
        if (n == str.length-1) {
            return true;
        }
        isVisited[curx*cols+cury] = true;

        boolean flag = (isHashPath(matrix, rows, cols, str, isVisited, curx-1, cury,n+1)||
                isHashPath(matrix, rows, cols, str, isVisited, curx+1, cury,n+1)||
                isHashPath(matrix, rows, cols, str, isVisited, curx, cury-1,n+1)||
                isHashPath(matrix, rows, cols, str, isVisited, curx, cury+1,n+1));
        isVisited[curx*cols+cury] = false;
        return flag;
    }
}
