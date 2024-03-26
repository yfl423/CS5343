import java.util.*;

/**
 * INSTRUCTIONS
 * Implement Dijkstra's algorithm.
 * Your graph must have at least 10 vertices and 20 edges.
 * Print out the graph - list of vertices and edges(pair of vertices)
 * Run dijkstra's algorithm.
 * Print the tree that results - list of vertices in the tree (same as above) and list of edges that make up the tree.
 * You may use heap library. That is the only library you an use.
 * Submit the code and screen shots of execution results
 **/
public class main {
    public static void main(String[] args) {
        Graph graph = new Graph(10, 20);
        graph.print();
        graph.printTree();
    }
}

class Graph {
    private Map<Integer, Map<Integer, Integer>> data = new HashMap<>();

    public Graph(int vertexCnt, int edgeCnt) {
        for (int i = 0; i < vertexCnt; i++) data.put(i, new HashMap<>());
        for (int i = 0; i < edgeCnt; i++) {
            int v1 = (int) (Math.random() * vertexCnt);
            int v2;
            do {
                v2 = (int) (Math.random() * vertexCnt);
            } while (v2 == v1 || data.get(v1).containsKey(v2));
            data.get(v1).put(v2, (int) (Math.random() * 10) + 1);
        }
    }

    /**
     * printTree: print tree of all vertices
     **/
    public void printTree() {
        System.out.println("The Dijkstra's result is as followed:");
        Object[] vertices = (data.keySet().toArray());
        for (int i = 0; i < vertices.length; i++) {
            System.out.println("Vertex: " + vertices[i] + " Tree: "+ doDijkstra((int) vertices[i]));
        }
    }

    /**
     * doDijkstra: Use Dijkstra's algorithm, to get the tree (optimal path to all other vertices) for the input vertex
     **/
    private Map<Integer, Integer> doDijkstra(int vertex) {
        Map<Integer, Integer> registerTable = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        for (int v : data.keySet()) registerTable.put(v, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] arr1, int[] arr2) -> arr2[1] - arr1[1]);
        pq.add(new int[]{vertex, 0});
        while (pq.size() > 0) {
            int[] poll = pq.poll();
            int next = poll[0];
            int cost = poll[1];
            visited.add(next);
            registerTable.put(next, cost);
            for (int v : data.get(next).keySet()) {
                if (!visited.contains(v)) pq.add(new int[]{v, registerTable.get(next) + data.get(next).get(v)});
            }
        }
        return registerTable;
    }

    /**
     * Print Graph Information as required
     * **/
    public void print() {
        StringBuilder sb = new StringBuilder();
        sb.append("List of vertices: ");
        for (int v : data.keySet()) {
            sb.append(v);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(".");
        System.out.println(sb);
        sb = new StringBuilder();
        sb.append("Edges(pair of vertices):\n");
        for (int v1 : data.keySet()) {
            for (int v2 : data.get(v1).keySet()) {
                sb.append("{edge:");
                sb.append(v1).append("->").append(v2).append(" weigh:").append(data.get(v1).get(v2));
                sb.append("}\n");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(".");
        System.out.println(sb);
    }
}
