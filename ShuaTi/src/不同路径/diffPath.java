package 不同路径;

/**
 * @author hf
 * @createtime 2021-03-16-8:29
 **/
public class diffPath {
    public static void main(String[] args) {
        int m=3,n=3;
        System.out.println(uniquePaths(m,n));

    }
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i=0;i<n;i++) dp[0][i] = 1;
        for(int i=0;i<m;i++) dp[i][0] = 1;
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                dp[i][j]= dp[i-1][j]+dp[i][j-1];
            }
        }

        return dp[m-1][n-1];

    }
}
