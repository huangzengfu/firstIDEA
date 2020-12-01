package kruskal;

import java.util.Arrays;

/**
 * @author hf
 * @createtime 2020-11-26-21:18
 **/
public class kruskalCase {

    private int edgeNum;//边的个数
    private char[] vertexs;//顶点数组
    private int[][] matrix;//邻接矩阵
    //使用INF 表示两个顶点不能联通
    private static final int INF = Integer.MAX_VALUE;

    //构造器
    public kruskalCase(char[] vertexs, int[][] matrix) {
        //初始化顶点数和边的个数
        int vlen = vertexs.length;

        //初始化顶点
        this.vertexs = new char[vlen];
        for (int i = 0; i < vlen; i++) {
            this.vertexs[i] = vertexs[i];
        }
        //初始化边
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        //初始化边长
        this.edgeNum = 0;
        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (matrix[i][j] != INF) {
                    this.edgeNum++;
                }
            }
        }

    }

    //打印邻接矩阵
    public void print() {
        System.out.println("打印邻接矩阵~");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%12d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }

    //返回vertexs中顶点对应的下标
    public int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    //获取邻接矩阵的边放到EData[]数组中，从matrix邻接矩阵中获取，EData[]形式如[['A','B',12],['B','F',7]]
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    //对边进行排序处理
    public void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EData tmp = edges[j + 1];
                    edges[j + 1] = edges[j];
                    edges[j] = tmp;
                }
            }
        }
    }

    /**
     * * 功能: 获取下标为 i 的顶点的终点(), 用于后面判断两个顶点的终点是否相同
     * * @param ends ： 数组就是记录了各个顶点对应的终点是哪个,ends 数组是在遍历过程中，逐步形成
     * * @param i : 表示传入的顶点对应的下标
     * * @return 返回的就是 下标为 i 的这个顶点对应的终点的下标, 一会回头还有来理解
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    public void kruskal() {
        //表示最后结果数组的索引
        int index = 0;
        int ends[] = new int[edgeNum];//用于保存"已有最小生成树" 中的每个顶点在最小生成树中的终点
        //创建结果数组, 保存最后的最小生成树
        EData[] rets = new EData[edgeNum];

        //获取图所有的边
        EData[] edges = getEdges();
        //按照边的权值大小进行排序(从小到大)
        sortEdges(edges);
        //遍历 edges 数组，将边添加到最小生成树中时，判断是准备加入的边否形成了回路，如果没有，就加入 rets, 否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            //获取到第 i 条边的第一个顶点(起点)
            int p1 = getPosition(edges[i].start);
            //获取到第 i 条边的第 2 个顶点
            int p2 = getPosition(edges[i].end);
            //获取 p1 这个顶点在已有最小生成树中的终点
            int m = getEnd(ends, p1);
            //获取 p2 这个顶点在已有最小生成树中的终点
            int n = getEnd(ends, p2);
            //是否构成回路
            if (m != n) {
                ends[m] = n;
                rets[index++] = edges[i];
            }
        }
        System.out.println("最小生成树为=" + Arrays.toString(rets));
    }

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //克鲁斯卡尔算法的邻接矩阵
        int matrix[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}};
        //创建Kruskal实例
        kruskalCase kruskalCase = new kruskalCase(vertexs, matrix);
//        kruskalCase.print();
        System.out.println("排序前xx = " + Arrays.toString(kruskalCase.getEdges()));
        EData[] edges = kruskalCase.getEdges();
        kruskalCase.sortEdges(edges);
        System.out.println("排序后xx = " + Arrays.toString(edges));
        kruskalCase.kruskal();
    }
}

//创建一个类，它的实例对象就是一条边
class EData {
    char start;//边的开始节点
    char end;//边的结束节点
    int weight;//边的权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}

