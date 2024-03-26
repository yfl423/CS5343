import java.util.*;

import static graph.Graph.graph1;
import static graph.Graph.graph2;

public class DFS4TopologicalSort {
    public static void main(String[] args) {
        System.out.println("graph1:");
        process(graph1, true);
        System.out.println("graph2:");
        process(graph2, false);
    }

    static Map<Integer, Set<Integer>> map;
    static int[] status; // 0: unvisited 1: tracking 2: visited
    static List<Integer> resultList;

    public static void process(int[][] graph, boolean isLetter) {
        // Step 1: set up
        if (map == null) map = new HashMap<>();
        else map.clear();
        status = new int[26];
        resultList = new ArrayList<>();

        map = new HashMap<>();
        for (int[] edge : graph) {
            if (!map.containsKey(edge[0])) map.put(edge[0], new HashSet<>());
            map.get(edge[0]).add(edge[1]);
        }
        // Step 2: Execute DFS to do topological sort
        for (int c : map.keySet()) {
            if (status[c] == 0) doDFS(c);
        }
        // Step 3: Print
        for (int i = resultList.size() - 1; i >= 0; i--) {
            System.out.print("vertex: ");
            if (isLetter) System.out.println((char) (resultList.get(i) + 'a'));
            else System.out.println(resultList.get(i));
        }
    }

    static void doDFS(int curr) {
        if (status[curr] == 2) return; // Already visited
        if (status[curr] == 1) { // Current one points to some vertex which actually is already on the path, means a circle exists
            System.out.println("Detect circle exists!!!");
            return;
        }
        status[curr] = 1; // Mark as tracking
        if (map.containsKey(curr)) {
            for (int next : map.get(curr)) doDFS(next);
        }

        resultList.add(curr);
        status[curr] = 2;
    }
}
