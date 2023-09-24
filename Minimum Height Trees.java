import java.util.*;

public class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            // If there's only one node, it is the MHT.
            return Collections.singletonList(0);
        }

        List<Set<Integer>> adjList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adjList.add(new HashSet<>());
        }

        // Build adjacency list representation of the tree.
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        // Initialize a list of leaf nodes.
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (adjList.get(i).size() == 1) {
                leaves.add(i);
            }
        }

        while (n > 2) {
            List<Integer> newLeaves = new ArrayList<>();
            for (int leaf : leaves) {
                // Remove leaf node and update its adjacent node.
                int neighbor = adjList.get(leaf).iterator().next();
                adjList.get(neighbor).remove(leaf);
                if (adjList.get(neighbor).size() == 1) {
                    newLeaves.add(neighbor);
                }
            }
            n -= leaves.size();
            leaves = newLeaves;
        }

        return leaves;
    }
}
