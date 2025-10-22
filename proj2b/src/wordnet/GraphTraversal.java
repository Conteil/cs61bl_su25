package wordnet;

import java.util.HashSet;
import java.util.List;

public class GraphTraversal {
    boolean[] marked;
    HashSet<Integer> nodes = new HashSet<>();

    public HashSet<Integer> traversal(Graph graph, int index) {
        nodes.add(index);
        marked = new boolean[graph.vertexCounts()];
        dfs(graph, index);
        return nodes;
    }

    public HashSet<Integer> traversal(Graph graph, List<Integer> indexs) {
        for (int index: indexs) {
            traversal(graph, index);
        }
        return nodes;
    }

    public void dfs(Graph graph, int index) {
        for (int node: graph.neighbors(index)) {
            if (!marked[node]) {
                marked[node] = true;
                nodes.add(node);
            }
            dfs(graph, node);
        }
    }
}
