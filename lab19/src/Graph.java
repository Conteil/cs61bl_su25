import java.util.*;

public class Graph {

    private LinkedList<Edge>[] adjLists;
    private int vertexCount;

    /* Initializes a graph with NUMVERTICES vertices and no Edges. */
    public Graph(int numVertices) {
        adjLists = (LinkedList<Edge>[]) new LinkedList[numVertices];
        for (int k = 0; k < numVertices; k++) {
            adjLists[k] = new LinkedList<Edge>();
        }
        vertexCount = numVertices;
    }

    /* Adds a directed Edge (V1, V2) to the graph. That is, adds an edge
       in ONE directions, from v1 to v2. */
    public void addEdge(int v1, int v2) {
        addEdge(v1, v2, 0);
    }

    /* Adds an undirected Edge (V1, V2) to the graph. That is, adds an edge
       in BOTH directions, from v1 to v2 and from v2 to v1. */
    public void addUndirectedEdge(int v1, int v2) {
        addUndirectedEdge(v1, v2, 0);
    }

    /* Adds a directed Edge (V1, V2) to the graph with weight WEIGHT. If the
       Edge already exists, replaces the current Edge with a new Edge with
       weight WEIGHT. */
    public void addEdge(int v1, int v2, int weight) {
        for (Edge e: adjLists[v1]) {
            if (e.to == v2) {
                e.weight = weight;
                return;
            }
        }

        adjLists[v1].addLast(new Edge(v1, v2, weight));
    }

    /* Adds an undirected Edge (V1, V2) to the graph with weight WEIGHT. If the
       Edge already exists, replaces the current Edge with a new Edge with
       weight WEIGHT. */
    public void addUndirectedEdge(int v1, int v2, int weight) {
        addEdge(v1, v2, weight);
        addEdge(v2, v1, weight);
    }

    /* Returns true if there exists an Edge from vertex FROM to vertex TO.
       Returns false otherwise. */
    public boolean isAdjacent(int from, int to) {
        for (Edge e: adjLists[from]) {
            if (e.to == to) {
                return true;
            }
        }

        return false;
    }

    /* Returns a list of all the vertices u such that the Edge (V, u)
       exists in the graph. */
    public List<Integer> neighbors(int v) {
        List<Integer> neighbors = new ArrayList<>();
        for (Edge e: adjLists[v]) {
            neighbors.add(e.to);
        }
        return neighbors;
    }
    /* Returns the number of incoming Edges for vertex V. */
    public int inDegree(int v) {
        int inDegree = 0;
        for (int i = 0; i < adjLists.length; i++) {
            if (isAdjacent(i, v)) {
                inDegree++;
            }
        }
        return inDegree;
    }

    /* Returns a list of the vertices that lie on the shortest path from start to stop.
    If no such path exists, you should return an empty list. If START == STOP, returns a List with START. */
    public ArrayList<Integer> shortestPath(int start, int stop) {
        ArrayList<Integer> path = new ArrayList<>();

        int[] distTo = new int[adjLists.length];
        int[] edgeTo = new int[adjLists.length];
        boolean[] visited = new boolean[adjLists.length];
        PriorityQueue<Edge> fringe = new PriorityQueue<>(adjLists.length, new EdgeComparator());
        for (int i = 0; i < adjLists.length; i++) {
            distTo[i] = Integer.MAX_VALUE;
            edgeTo[i] = -1;
        }

        fringe.add(new Edge(-1, start, 0));
        distTo[start] = 0;

        while(!visited[stop]) {
            Edge curr = fringe.poll();
            int currNode = curr.to;

            if (visited[currNode] == true) {
                continue;
            }

            visited[currNode] = true;

            for (Edge e: adjLists[currNode]) {
                if (visited[e.to]) {
                    continue;
                }

                if (distTo[currNode] + e.weight < distTo[e.to]) {
                    distTo[e.to] = distTo[currNode] + e.weight;
                    edgeTo[e.to] = currNode;
                    fringe.add(new Edge(currNode, e.to, distTo[e.to]));
                }
            }
        }

        if (edgeTo[stop] != -1) {
            for (int curr = stop; curr != start; curr = edgeTo[curr]) {
                path.add(0, curr);
            }
            path.add(0, start);
        }
        return path;
    }

    private Edge getEdge(int v1, int v2) {
        for (Edge e: adjLists[v1]) {
            if (e.to == v2) {
                return e;
            }
        }
        return null;
    }

    private class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    private class Edge {

        private int from;
        private int to;
        private int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public String toString() {
            return "(" + from + ", " + to + ", weight = " + weight + ")";
        }

        public int to() {
            return this.to;
        }

    }

    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(0, 1, 10);
        g.addEdge(0, 3, 30);
        g.addEdge(0, 4, 100);
        g.addEdge(1, 2, 50);
        g.addEdge(2, 4, 10);
        g.addEdge(3, 4, 60);
        g.addEdge(3, 2, 20);
        ArrayList<Integer> path0 = g.shortestPath(0, 1);
        System.out.println(path0);
    }
}
