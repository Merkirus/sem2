package cw6;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ELTRUCKO<Key extends Comparable<? super Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        Key key;
        Value value;
        Node left, right;
        int n;
        boolean color;

        Node(Key key, Value value, int n, boolean color) {
            this.key = key;
            this.value = value;
            this.n = n;
            this.color = color;
        }
    }

    private Node root;

    public ELTRUCKO() {
        root = null;
    }

    private boolean isRed(Node node) {
        if (node == null)
            return BLACK;
        return node.color == RED;
    }

    private int size(Node node) {
        if (node == null)
            return 0;
        else return node.n;
    }

    private Node rotateLeft(Node node) {
        Node child = node.right;
        node.right = child.left;
        child.left = node;
        child.color = node.color;
        node.color = RED;
        child.n = node.n;
        node.n = 1 + size(node.left) + size(node.right);
        return child;
    }

    private Node rotateRight(Node node) {
        Node child = node.left;
        node.left = child.right;
        child.right = node;
        child.color = node.color;
        node.color = RED;
        child.n = node.n;
        node.n = 1 + size(node.left) + size(node.right);
        return child;
    }

    private void flipColors(Node node) {
        node.color = RED;
        if (node.left != null)
            node.left.color = BLACK;
        if (node.right != null)
            node.right.color = BLACK;
    }

    public void insert(Value value) {
        root = put(root, (Key) value, value);
        root.color = BLACK;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null)
            return new Node(key, value, 1, RED);
        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            node.left = put(node.left, key, value);
        if (cmp > 0)
            node.right = put(node.right, key, value);
        else
            node.value = value;

        if (isRed(node.right) && !isRed(node.left))
            node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left))
            node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right))
            flipColors(node);
        node.n = size(node.left) + size(node.right) + 1;
        return node;
    }

    private Node moveRedLeft(Node node) {
        if (node == null)
            return null;
        flipColors(node);
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
        }
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null)
            return null;
        if (!isRed(node.left) && !isRed(node.left.left))
            node = moveRedLeft(node);
        node.left = deleteMin(node.left);
        return balance(node);
    }

    private Node moveRedRight(Node node) {
        if (node == null)
            return null;
        flipColors(node);
        if (!isRed(node.left.left))
            node = rotateRight(node);
        return node;
    }

    public void delete(Key key) {
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = delete(root, key);
        if (!isEmpty())
            root.color = BLACK;
    }

    private Node delete(Node node, Key key) {
        if (key.compareTo(node.key) < 0) {
            if (!isRed(node.left) && !isRed(node.left.left))
                node = moveRedLeft(node);
            node.left = delete(node.left, key);
        } else {
          if (isRed(node.left))
              node = rotateRight(node);
          if (key.compareTo(node.key) == 0 && (node.right == null))
              return null;
          if (!isRed(node.right) && !isRed(node.right.left))
              node = moveRedRight(node);
          if (key.compareTo(node.key) == 0) {
              node.value = get(node.right, min(node.right).key);
              node.key = min(node.right).key;
              node.right = deleteMin(node.right);
          } else {
              node.right = delete(node.right, key);
          }
        }
        return balance(node);
    }

    private Value get(Node node, Key key) {
        if (node == null)
            return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            return get(node.left, key);
        if (cmp > 0)
            return get(node.right, key);
        else
            return node.value;
    }

    private Node min(Node node) {
        if (node.left == null)
            return node;
        return min(node.left);
    }

    private Node balance(Node node) {
        if (isRed(node.right))
            node = rotateLeft(node);
        return node;
    }

    private boolean isEmpty() {
        return size(root) == 0;
    }

    public void print2D() throws IOException {
        BTreePrinter bTreePrinter = new BTreePrinter();
        bTreePrinter.printNode(root);
    }


    class BTreePrinter {

        public <T extends Comparable<?>> void printNode(Node root) {
            int maxLevel = maxLevel(root);

            printNodeInternal(Collections.singletonList(root), 1, maxLevel);
        }

        private <T extends Comparable<?>> void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
            if (nodes.isEmpty() || isAllElementsNull(nodes))
                return;

            int floor = maxLevel - level;
            int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
            int firstSpaces = (int) Math.pow(2, (floor)) - 1;
            int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

            printWhitespaces(firstSpaces);

            List<Node> newNodes = new ArrayList<Node>();
            for (Node node : nodes) {
                if (node != null) {
                    System.out.print(node.value);
                    newNodes.add(node.left);
                    newNodes.add(node.right);
                } else {
                    newNodes.add(null);
                    newNodes.add(null);
                    System.out.print(" ");
                }

                printWhitespaces(betweenSpaces);
            }
            System.out.println("");

            for (int i = 1; i <= endgeLines; i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    printWhitespaces(firstSpaces - i);
                    if (nodes.get(j) == null) {
                        printWhitespaces(endgeLines + endgeLines + i + 1);
                        continue;
                    }

                    if (nodes.get(j).left != null)
                        System.out.print("/");
                    else
                        printWhitespaces(1);

                    printWhitespaces(i + i - 1);

                    if (nodes.get(j).right != null)
                        System.out.print("\\");
                    else
                        printWhitespaces(1);

                    printWhitespaces(endgeLines + endgeLines - i);
                }

                System.out.println("");
            }

            printNodeInternal(newNodes, level + 1, maxLevel);
        }

        private void printWhitespaces(int count) {
            for (int i = 0; i < count; i++)
                System.out.print(" ");
        }

        private <T extends Comparable<?>> int maxLevel(Node node) {
            if (node == null)
                return 0;

            return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
        }

        private <T> boolean isAllElementsNull(List<T> list) {
            for (Object object : list) {
                if (object != null)
                    return false;
            }

            return true;
        }

    }
}