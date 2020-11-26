package prim;

import java.util.Arrays;

/**
 * @author hf
 * @createtime 2020-11-26-11:02
 **/
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        //邻接矩阵的关系使用二维数组表示,10000 这个大数，表示两个点不联通
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };
        //创建graph对象
        MGraph mGraph = new MGraph(verxs);
        //创建最小生成树
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph, verxs, data, weight);
        minTree.showGraph(mGraph);

        minTree.prim(mGraph,1);
    }

}

//创建最小生成树
class MinTree {
    //创建图的邻接矩阵graph：图对象、verxs:图的顶点个数、data：图的各个顶点的值、weight：图的邻接矩阵
    public void createGraph(MGraph graph, int verxs, char[] data, int[][] weight) {
        int i, j;
        for (i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    //编写prim算法，生成最小生成树
    /*
     * graph:待处理的图
     * v:表示从哪个顶点开始生成
     * */
    public void prim(MGraph graph, int v) {
        //标记节点是否被访问过
        int[] visited = new int[graph.verxs];
        //把当前节点标记为已访问
        visited[v] = 1;
        //h1,h2用来标记两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;//将minWeight初始化为一个大数，后续会做修改

        for (int k = 1; k < graph.verxs; k++) {//因为有 graph.verxs 顶点，普利姆算法结束后，有 graph.verxs-1 边
            //这个是确定每一次生成的子图 ，和哪个结点的距离最近
            for (int i = 0; i < graph.verxs; i++) {// i 结点表示被访问过的结点
                for (int j = 0; j < graph.verxs; j++) {//j 结点表示还没有访问过的结点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        //替换 minWeight(寻找已经访问过的结点和未访问过的结点间的权值最小的边)
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }

            System.out.println("最小的边为"+graph.data[h1]+"->"+graph.data[h2]+" "+"权值为"+minWeight);
            //替换 minWeight(寻找已经访问过的结点和未访问过的结点间的权值最小的边)
            visited[h2] =1;
            //minWeight 重新设置为最大值 10000，进入下一次循环
            minWeight = 10000;

        }


    }


}

class MGraph {

    int verxs;//表示图的节点个数
    char[] data;//表示每个节点的值
    int[][] weight;//存放边，就是我们的邻接矩阵

    public MGraph(int verxs) {
        this.verxs = verxs;
        this.data = new char[verxs];
        this.weight = new int[verxs][verxs];
    }
}
