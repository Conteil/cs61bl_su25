public class UnionFind {
    int[] uf;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        uf = new int[N];
        for (int i = 0; i < uf.length; i++) {
            uf[i] = -1;
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        int root = find(v);
        return -uf[root];
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        if (v < 0 || v >= uf.length) {
            throw new IllegalArgumentException();
        } else {
            return uf[v];
        }
    }

    /* Returns true if nodes V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        int r1 = find(v1);
        int r2 = find(v2);
        return r1 == r2;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        if (v < 0 || v >= uf.length) {
            throw new IllegalArgumentException();
        } else if (uf[v] < 0) {
            return v;
        } else {
            uf[v] = find(uf[v]);
            return uf[v];
        }
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. */
    public void union(int v1, int v2) {
        int r1 = find(v1);
        int r2 = find(v2);
        if (r1 == r2) {
            return;
        }
        int s1 = sizeOf(r1);
        int s2 = sizeOf(r2);
        if (s1 <= s2) {
            uf[r1] = r2;
            uf[r2] -= s1;
        } else {
            uf[r2] = r1;
            uf[r1] -= s2;
        }
    }
}
