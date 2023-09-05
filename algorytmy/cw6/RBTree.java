package cw6;

import cw5.BST;

import java.io.IOException;
import java.util.*;

public class RBTree<T extends Comparable<? super T>> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    class Node {
        T value;
        Node left, right, parent;
        boolean color;

        Node(T value) {
            this.value = value;
            color = RED;
        }

        Node(Node parent, T value) {
            this.parent = parent;
            this.value = value;
            color = BLACK;
        }
    }

    private Node root;
    private Comparator<T> comparator;

    public RBTree(Comparator<T> comp) {
        this.comparator = comp;
    }

    private Node insertTree(T value) {
        if (root == null) {
            root = new Node(null, value);
            root.left = new Node(root, null);
            root.right = new Node(root, null);
            return root;
        }
        Node newNode = null;
        Node node = root;

        while (node != null) {
            if (node.value == null) {
                node.value = value;
                node.color = RED;

                node.left = new Node(node, null);
                node.right = new Node(node, null);
                newNode = node;
                break;
            } else if (value.compareTo(node.value) < 0) {
                node = node.left;
            } else  {
                node = node.right;
            }
        }

        if (newNode != null)
            balanceAfterInsert(newNode);

        return newNode;
    }

    public void insert(T value) {
        Node node = insertTree(value);
    }

    private void balanceAfterInsert(Node node) {
        Node curr = node;
        Node parent = node.parent;

        if (parent == null) {
            node.color = BLACK;
            return;
        }

        if (parent.color == BLACK) {
            return;
        }

        Node grandParent = getGrandParent(node);
        Node uncle = getUncle(node);

        boolean uncleColor;

        if (uncle != null)
            uncleColor = uncle.color;
        else
            uncleColor = BLACK;

        if (parent.color == RED && uncleColor == RED) {
            parent.color = BLACK;
            uncle.color = BLACK;
            if (grandParent != null) {
                grandParent.color = RED;
                balanceAfterInsert(grandParent);
            }
            return;
        }

        if (parent.color == RED && uncleColor == BLACK) {
            if (node == parent.right && parent == grandParent.left) {
                rotateLeft(parent);
                node = node.left;
                parent = node.parent;
                grandParent = getGrandParent(node);
                uncle = getUncle(node);
            } else if (node == parent.left && parent == grandParent.right) {
                rotateRight(parent);
                node = node.right;
                parent = node.parent;
                grandParent = getGrandParent(node);
                uncle = getUncle(node);
            }
        }

        if (parent.color == RED && uncleColor == BLACK) {
            parent.color = BLACK;
            grandParent.color = RED;
            if (node == parent.left && parent == grandParent.left)
                rotateRight(grandParent);
            else if (node == parent.right && parent == grandParent.right)
                rotateLeft(grandParent);
        }
    }

    private void rotateRight(Node node) {
        Node parent = node.parent;
        Node leftChild = node.left;
        Node rightChild = node.right;

        leftChild.right = node;
        node.parent = leftChild;
        node.left = rightChild;

        if (rightChild!=null)
            rightChild.parent = node;

        if (parent!=null) {
            if (node == parent.left)
                parent.left = leftChild;
            else if (node == parent.right)
                parent.right = leftChild;
            else
                throw new NoSuchElementException();
            leftChild.parent = parent;
        } else {
          root = leftChild;
          root.parent = null;
        }
    }

    private void rotateLeft(Node node) {
        Node parent = node.parent;
        Node leftChild = node.left;
        Node rightChild = node.right;

        rightChild.left = node;
        node.parent = rightChild;
        node.right = leftChild;

        if (leftChild!=null)
            leftChild.parent = node;

        if (parent!=null) {
            if (node == parent.left)
                parent.left = rightChild;
            else if (node == parent.right)
                parent.right = rightChild;
            else
                throw new NoSuchElementException();
            rightChild.parent = parent;
        } else {
            root = rightChild;
            root.parent = null;
        }
    }

    private Node getGrandParent(Node node) {
        if (node.parent == null || node.parent.parent == null)
            return null;
        return node.parent;
    }

    private Node getUncle(Node node) {
        Node grandParent = getGrandParent(node);
        if (grandParent == null)
            return null;
        if (grandParent.left != null && grandParent.left == node.parent)
            return grandParent.right;
        else if (grandParent.right != null && grandParent.right == node.parent) {
            return grandParent.left;
        }
        return null;
    }

    private Node getSibling(Node node) {
        if (node.parent == null)
            return null;
        if (node.left == node)
            return node.parent.right;
        else if (node.right == node)
            return node.parent.left;
        else
            throw new NoSuchElementException();
    }

    private boolean isLeaf(Node node) {
        if (node.left != null)
            return false;
        if (node.right != null)
            return false;
        return true;
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
