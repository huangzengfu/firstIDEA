package graph;

import javax.management.ValueExp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author hf
 * @createtime 2020-11-20-14:26
 **/
public class Graph {
    private ArrayList<String> vertexList;//存放定点的集合
    private int[][] edges;//存储图对应的邻接矩阵
    private int numOfEdges;//表示边的数目
    //定义数组boolean[],用来标记某个节点是否被访问
    private boolean[] isVisited;

    //初始化矩阵
    public Graph(int n) {
        //初始化vertexList和edges
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    //插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    //添加边
    public void insertEdge(int v1, int v2, int height) {
        edges[v1][v2] = height;
        edges[v2][v1] = height;//因为是无向图，反过来也要添加
        numOfEdges++;//将边的数目加1
    }

    //图中常用的方法
    //1、返回结点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //2、返回边的个数
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //3、获取i对应的数据
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //4、返回两个结点之间的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //5、输出图对应的邻接矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    //得到第一个邻接节点的下标w,如果存在就返回对应的下标，否则返回-1
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //根据前一个邻接节点的下标获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //深度优先遍历算法DFS
    public void dfs(boolean[] isVisited, int i) {
        //首先访问该节点，输出
        System.out.print(getValueByIndex(i) + "->");
        //然后将该节点设置为已经访问过
        isVisited[i] = true;
        //查找i的第一个邻接节点w
        int w = getFirstNeighbor(i);
        while (w != -1) {//说明有邻接节点
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            //如果w节点已经被访问过
            w = getNextNeighbor(i, w);
        }
    }

    //对dfs进行重载，遍历所有节点，进行dfs
    public void dfs() {
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    //广度优先搜索
    public void bfs(boolean[] isVisited, int i) {
        int u;//表示队列头节点对应的下标
        int w;//邻接节点下标

        //队列，记录节点的访问顺序
        LinkedList queue = new LinkedList();
        //访问节点，输出节点值
        System.out.println(getValueByIndex(i));
        //将该节点标记为已访问
        isVisited[i] = true;
        //将节点加入队列
        queue.addLast(i);

        while (!queue.isEmpty()) {
            u = (Integer) queue.removeFirst();//第一个节点出队列
            w = getFirstNeighbor(u);//获取第一个节点的第一个邻接节点
            while (w != -1) {//找到
                //是否访问过
                if (!isVisited[w]) {
                    System.out.println(getValueByIndex(w) + "->");
                    isVisited[w] = true;
                    //入队列
                    queue.addLast(w);
                }
                //若访问过就找下一个邻接节点
                w = getNextNeighbor(u, w);
            }
        }
    }

    //对dfs进行重载，遍历所有节点，进行dfs
    public void bfs() {
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    public static void main(String[] args) {
        //结点个数
        int n = 5;
        //图顶点的值
        String[] VerTexs = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);
        for (String vertex : VerTexs) {
            graph.insertVertex(vertex);
        }

        //创建图的边A-B，A-C，B-C,B-D，B-E
        graph.insertEdge(0, 1, 1);//A-B
//        graph.insertEdge(1,0,1);//B-A
        graph.insertEdge(0, 2, 1);//A-C
//        graph.insertEdge(2,0,1);//C-A
        graph.insertEdge(1, 2, 1);//B-C
//        graph.insertEdge(2,1,1);//C-B
        graph.insertEdge(1, 3, 1);//B-D
//        graph.insertEdge(3,1,1);//D-B
        graph.insertEdge(1, 4, 1);//B-E
//        graph.insertEdge(4,1,1);//E-B

        System.out.println("显示图的邻接矩阵~");
        graph.showGraph();
        System.out.println("获取图的边长~" + graph.getNumOfEdges());
        System.out.println("获取图的顶点个数~" + graph.getNumOfVertex());
        System.out.println("根据索引获取的图的顶点值~" + graph.getValueByIndex(1));
        System.out.println("深度优先遍历：");
        graph.dfs();
    }

}
