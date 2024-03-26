import java.util.*;

import static graph.Graph.*;

public class BFS4TopologicalSort {
    public static void main(String[] args) {
        System.out.println("graph1:");
        process(graph1, true);
        System.out.println("graph2:");
        process(graph2, false);
    }

    public static void process(int[][] graph, boolean isLetter) {
        // Step1: prepare inDegree table & map
        int max = Integer.MIN_VALUE;
        for (int[] edge : graph) max = Math.max(max, Math.max(edge[0], edge[1]));
        Integer[] inDegree = new Integer[max + 1];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] edge : graph) {
            if (!map.containsKey(edge[0])) map.put(edge[0], new ArrayList<>());
            map.get(edge[0]).add(edge[1]);
            if (inDegree[edge[0]] == null) inDegree[edge[0]] = 0;
            if (inDegree[edge[1]] == null) inDegree[edge[1]] = 0;
            inDegree[edge[1]]++;
        }
        // Step2: execute BFS to do topological sort
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < inDegree.length; i++) if (inDegree[i] != null && inDegree[i] == 0) q.add(i);
        while (q.size() > 0) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int poll = q.poll();
                System.out.print("vertex: ");
                if (isLetter) System.out.println((char) (poll + 'a'));
                else System.out.println(poll);
                if (map.containsKey(poll)) {
                    for (int next : map.get(poll)) {
                        inDegree[next]--;
                        if (inDegree[next] == 0) q.offer(next);
                    }
                }
            }
        }
        // Step3: check InDegree table to see if there is a circle
        for (int i = 1; i < inDegree.length; i++)
            if (inDegree[i] != null && inDegree[i] > 0) {
                System.out.print("vertex: ");
                if (isLetter) System.out.print((char) (i + 'a'));
                else System.out.print(i);
                System.out.println(" cannot be sorted due to circle dependency");

            }
    }
}
