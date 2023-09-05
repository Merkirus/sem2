package lab11;

import java.util.ArrayList;

public class Node implements Comparable<Node> {

    // also called "value" in a binary tree
    // also called "key" in a binary search tree
    ArrayList<Integer> rows;
    String data;

    Node left;
    Node right;
    Node parent; // used in SimpleBinaryTree + red-black tree

    int height; // used in AVL tree
    boolean color; // used in red-black tree

    /**
     * Constructs a new node with the given data.
     *
     * @param data the data to store in the node
     */
    public Node(String data) {
        this.data = data;
        this.rows = new ArrayList<>();
    }

    public String getData() {
        return data;
    }

    @Override
    public int compareTo(Node o) {
        if  (this.rows.get(0).compareTo(o.rows.get(0)) == 0)
            return this.data.compareTo(o.data);
        return this.rows.get(0).compareTo(o.rows.get(0));
    }

//    @Override
//    public int compareTo(Node o) {
//        return this.data.compareTo(o.data);
//    }
}
