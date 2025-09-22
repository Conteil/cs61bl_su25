public class RedBlackTree<T extends Comparable<T>> {

    /* Root of the tree. */
    RBTreeNode<T> root;

    static class RBTreeNode<T> {

        final T item;
        boolean isBlack;
        RBTreeNode<T> left;
        RBTreeNode<T> right;

        /* Creates a RBTreeNode with item ITEM and color depending on ISBLACK
           value. */
        RBTreeNode(boolean isBlack, T item) {
            this(isBlack, item, null, null);
        }

        /* Creates a RBTreeNode with item ITEM, color depending on ISBLACK
           value, left child LEFT, and right child RIGHT. */
        RBTreeNode(boolean isBlack, T item, RBTreeNode<T> left,
                   RBTreeNode<T> right) {
            this.isBlack = isBlack;
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }

    /* Creates an empty RedBlackTree. */
    public RedBlackTree() {
        root = null;
    }

    /* Creates a RedBlackTree from a given 2-3 TREE. */
    public RedBlackTree(TwoThreeTree<T> tree) {
        Node<T> ttTreeRoot = tree.root;
        root = buildRedBlackTree(ttTreeRoot);
    }

    /* Builds a RedBlackTree that has isometry with given 2-3 tree rooted at
       given node R, and returns the root node. */
    RBTreeNode<T> buildRedBlackTree(Node<T> r) {
        if (r == null) {
            return null;
        }

        if (r.getItemCount() == 1) {
            RBTreeNode<T> rbTreeRoot = new RBTreeNode<>(true, r.getItemAt(0));
            rbTreeRoot.left = buildRedBlackTree(r.getChildAt(0));
            rbTreeRoot.right = buildRedBlackTree(r.getChildAt(1));
            return rbTreeRoot;
        } else {
            RBTreeNode<T> rbTreeRoot = new RBTreeNode<>(true, r.getItemAt(1));
            rbTreeRoot.left = new RBTreeNode<>(false, r.getItemAt(0));
            rbTreeRoot.left.left = buildRedBlackTree(r.getChildAt(0));
            rbTreeRoot.left.right = buildRedBlackTree(r.getChildAt(1));
            rbTreeRoot.right = buildRedBlackTree(r.getChildAt(2));
            return rbTreeRoot;
        }
    }

    /**
     * Flips the color of node and its children. Assume that NODE has both left
     * and right children
     * @param node
     */
    void flipColors(RBTreeNode<T> node) {
        node.isBlack = !node.isBlack;
        node.left.isBlack = !node.left.isBlack;
        node.right.isBlack = !node.right.isBlack;
    }

    /**
     * Rotates the given node to the right. Returns the new root node of
     * this subtree. For this implementation, make sure to swap the colors
     * of the new root and the old root!
     * @param node
     * @return
     */
    RBTreeNode<T> rotateRight(RBTreeNode<T> node) {
        RBTreeNode<T> returnNode = new RBTreeNode<>(node.isBlack, node.left.item);
        returnNode.right = new RBTreeNode<>(node.left.isBlack, node.item);
        returnNode.right.right = node.right;
        returnNode.right.left = node.left.right;
        returnNode.left = node.left.left;
        return returnNode;
    }

    /**
     * Rotates the given node to the left. Returns the new root node of
     * this subtree. For this implementation, make sure to swap the colors
     * of the new root and the old root!
     * @param node
     * @return
     */
    RBTreeNode<T> rotateLeft(RBTreeNode<T> node) {
        RBTreeNode<T> returnNode = new RBTreeNode<>(node.isBlack, node.right.item);
        returnNode.left = new RBTreeNode<>(node.right.isBlack, node.item);
        returnNode.left.left = node.left;
        returnNode.left.right = node.right.left;
        returnNode.right = node.right.right;
        return returnNode;
    }

    /**
     * Inserts the item into the Red Black Tree. Colors the root of the tree black.
     * @param item
     */
    public void insert(T item) {
        root = insert(root, item);
        root.isBlack = true;
    }

    /**
     * Inserts the given node into this Red Black Tree. Comments have been provided to help break
     * down the problem. For each case, consider the scenario needed to perform those operations.
     * Make sure to also review the other methods in this class!
     * @param node
     * @param item
     * @return
     */
    private RBTreeNode<T> insert(RBTreeNode<T> node, T item) {
        // Insert (return) new red leaf node.
        // Handle normal binary search tree insertion.
        if (node == null) {
            return new RBTreeNode<T>(false, item);
        } else {
            int comp = item.compareTo(node.item);
            if (comp > 0) {
                node.right = insert(node.right, item);
            } else {
                node.left = insert(node.left, item);
            }
            // fix this return statement
            return checkHelper(node);
        }
    }

    /**
     * Helper method that returns whether the given node is red. Null nodes (children or leaf
     * nodes) are automatically considered black.
     * @param node
     * @return
     */
    private boolean isRed(RBTreeNode<T> node) {
        return node != null && !node.isBlack;
    }

    private RBTreeNode checkHelper(RBTreeNode<T> node) {
        if (isRed(node.right) && !isRed(node.left)) {
            // Rotate left operation (handle "middle of three" and "right-leaning red" structures)
            return rotateLeft(node);
        } else if (isRed(node.left) && isRed(node.right)) {
            // Color flip (handle "largest of three" structure)
            flipColors(node);
            return node;
        } else if (isRed(node.left) && isRed(node.left.left)) {
            // Rotate right operation (handle "smallest of three" structure)
            return checkHelper(rotateRight(node));
        } else {
            return node;
        }
    }
}
