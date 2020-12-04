package dijkstra;

import java.util.Arrays;

/**
 * @author hf
 * @createtime 2020-11-30-18:35
 **/
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;// 表示不可以连接
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        //创建 Graph 对象
        Graph graph = new Graph(vertex, matrix);
        //测试, 看看图的邻接矩阵是否 ok
        graph.showGraph();
        //测试迪杰斯特拉算法
        graph.dsj(6);
        graph.showDijkstra();
    }
}

class Graph {
    private char[] vertexs;//顶点数组
    private int[][] matrix;//邻接矩阵
    private VisitedVertex vv;//已经访问的顶点的集合

    //构造器
    public Graph(char[] vertexs, int[][] matrix) {
        this.vertexs = vertexs;
        this.matrix = matrix;
    }

    //显示结果
    public void showDijkstra() {
        vv.show();
    }

    //显示图
    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    //dijkstra算法实现
    public void dsj(int index) {
        vv = new VisitedVertex(vertexs.length, index);
        update(index); // 更新 index 顶点到周围顶点的距离和前驱顶点
        for (int i = 1; i < vertexs.length; i++) {
            index = vv.updateArr();
            update(index);
        }
    }

    //更新index顶点到周围顶点的距离和周围顶点的前驱节点
    private void update(int index) {
        int len = 0;
        //处理邻接矩阵的matrix[index]行
        for (int i = 0; i < matrix[index].length; i++) {
            // len 含义是 : 出发顶点到 index 顶点的距离 + 从 index 顶点到 j 顶点的距离的和
            len = vv.getDis(index) + matrix[index][i];
            // 如果 i 顶点没有被访问过，并且 len 小于出发顶点到 i 顶点的距离，就需要更新
            if (!vv.in(i) && len < vv.getDis(i)) {
                vv.updatePre(i, index);
                vv.updateDis(i, len);
            }
        }
    }
}

// 已访问顶点集合
class VisitedVertex {
    //记录各个顶点是否访问过
    private int[] already_arr;
    // 每个下标对应的值为前一个顶点下标, 会动态更新
    private int[] pre_visited;
    // 记录出发顶点到其他所有顶点的距离,比如 G 为出发顶点，就会记录 G 到其它顶点的距离，会动态更新，求的最短距离就会存放到 dis
    private int[] dis;

    //构造器
    //length:表示顶点的个数、index：出发点对应的下标
    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        //初始化dis
        Arrays.fill(dis, 65535);
        this.already_arr[index] = 1;
        this.dis[index] = 0;
    }

    //判断index顶点是否被访问过
    public boolean in(int index) {
        return already_arr[index] == 1;
    }

    //更新出发顶点到index的距离
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    //更新前驱节点
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    //返回出发顶点到index的距离
    public int getDis(int index) {
        return dis[index];
    }

    /*** 继续选择并返回新的访问顶点， 比如这里的 G 完后，就是 A 点作为新的访问顶点(注意不是出发顶点) * @return */
    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        //更新index被访问过
        already_arr[index] = 1;
        return index;
    }

    //显示最后的结果
    //即将三个数组的情况输出
    public void show() {
        System.out.println("==========================");
        //输出 already_arr
        for (int i : already_arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        //输出 pre_visited
        for (int i : pre_visited) {
            System.out.print(i + " ");
        }
        System.out.println();
        //输出 dis
        for (int i : dis) {
            System.out.print(i + " ");
        }
        System.out.println();
        //为了好看最后的最短距离，我们处理
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int count = 0;
        for (int i : dis) {
            if (i != 65535) {
                System.out.print(vertex[count] + "(" + i + ") ");
            } else {
                System.out.println("N ");
            }
            count++;
        }
        System.out.println();
    }
}
