package dynamic;

/**
 * @author hf
 * @createtime 2020-11-22-15:35
 **/
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1,4,3};//物品的重量
        int[] val = {1500,3000,2000};//物品的价值
        int m = 4;//背包的容量
        int n = val.length;//物品的个数

        //创建二维数组
        //v[i][j] 表示在前i个物品中能够装入容量为j的背包的最大价值
        int[][] v = new int[n+1][m+1];
        //定义二维数组记录商品放入的情况
        int[][] path = new int[n+1][m+1];

        //初始化二维数组的第一行和第一列
        for(int i = 0;i<v.length;i++){
            v[i][0] = 0;//将第一列赋值为0
        }
        for(int i = 0;i<v[0].length;i++){
            v[0][i] = 0;//将第一行赋值为0
        }

        //输出v，查看当前的矩阵情况
        for(int i = 0;i<v.length;i++){
            for(int j = 0;j<v[i].length;j++){
                System.out.print(v[i][j]+" ");
            }
            System.out.println();
        }

        //根据公式进行动态规划处理
        for(int i =1;i<v.length;i++){//不处理第一列，第一列均为0
            for(int j = 1;j<v[0].length;j++){//不处理第一行
                if(w[i-1]>j){
                    v[i][j] = v[i-1][j];
                }else{
//                    v[i][j] = Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    if(v[i-1][j] < val[i-1]+v[i-1][j-w[i-1]]){
                        v[i][j] = val[i-1]+v[i-1][j-w[i-1]];
                        path[i][j] = 1;
                    }else{
                        v[i][j] = v[i-1][j];
                    }
                }
            }
        }

        //输出最后放入了哪些物品
        int i = path.length-1;
        int j = path[0].length-1;
        while(i>0 && j>0){
            if(path[i][j] == 1){
                System.out.println("第"+i+"个商品放入背包！");
                j-=w[i-1];
            }
            i--;
        }

        System.out.println("Path矩阵情况：");
        for(int z = 0;z<path.length;z++){
            for(int y = 0;y<path[z].length;y++){
                System.out.print(path[z][y]+" ");
            }
            System.out.println();
        }
    }
}
