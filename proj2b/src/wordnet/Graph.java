package wordnet;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Graph {
    private LinkedList<Integer>[] adjLists;
    private int vertexCount;

    public Graph(int numVertices) {
        adjLists = (LinkedList<Integer>[]) new LinkedList[numVertices];
        for (int k = 0; k < numVertices; k++) {
            adjLists[k] = new LinkedList<Integer>();
        }
        vertexCount = numVertices;
    }

    public void addEdge(int from, int to) {
        LinkedList<Integer> list = adjLists[from];
        if (!list.contains(to)) {
            adjLists[from].addLast(to);
        }
    }

    public List<Integer> neighbors(int index) {
        return adjLists[index];
    }

    public int vertexCounts() {
        return vertexCount;
    }

    public HashSet<Integer> traversal(int index) {
        GraphTraversal gt = new GraphTraversal();
        return gt.traversal(this, index);
    }

    public HashSet<Integer> traversal(List<Integer> indexs) {
        GraphTraversal gt = new GraphTraversal();
        return gt.traversal(this, indexs);
    }
}
