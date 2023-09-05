package lab11;

import cw3.*;
import cw5.KPList;

import java.io.*;

public class RedBlackTree {

    static final boolean RED = false;
    static final boolean BLACK = true;

    private Node root;

    public Node searchNode(String key) {
        Node node = root;
        while (node != null) {
            if (key.compareTo(node.data) == 0) {
                return node;
            } else if (key.compareTo(node.data) < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        return null;
    }

    public void insertNode(String key, Integer row) {
        Node node = root;
        Node parent = null;

        while (node != null) {
            parent = node;
            if (key.compareTo(node.data) < 0) {
                node = node.left;
            } else if (key.compareTo(node.data) > 0) {
                node = node.right;
            } else {
                // TODO - tu zachodzi zmiana zachowania
                node.rows.add(row);
                return;
            }
        }

        Node newNode = new Node(key);
        newNode.color = RED;
        newNode.rows.add(row);
        if (parent == null) {
            root = newNode;
        } else if (key.compareTo(parent.data) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        newNode.parent = parent;

        fixAfterInsert(newNode);
        root.color = BLACK;
    }

    private void fixAfterInsert(Node node) {
        Node parent = node.parent;

        if (parent == null)
            return;

        if (parent.color == BLACK)
            return;

        Node grandparent = parent.parent;

        Node uncle = getUncle(parent);

        if (uncle != null && uncle.color == RED) {
            parent.color = BLACK;
            grandparent.color = RED;
            uncle.color = BLACK;
            fixAfterInsert(grandparent);
        }
        else if (parent == grandparent.left) {
            if (node == parent.right) {
                rotateLeft(parent);
                parent = node;
            }
            rotateRight(grandparent);
            parent.color = BLACK;
            grandparent.color = RED;
        } else {
            if (node == parent.left) {
                rotateRight(parent);
                parent = node;
            }
            rotateLeft(grandparent);
            parent.color = BLACK;
            grandparent.color = RED;
        }
    }

    private Node getUncle(Node parent) {
        Node grandparent = parent.parent;
        if (grandparent.left == parent)
            return grandparent.right;
        else
            return grandparent.left;
    }

    public void deleteNode(String key) {
        Node node = root;

        while (node != null && node.data.compareTo(key) != 0) {
            if (key.compareTo(node.data) < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        if (node == null)
            return;

        Node childReplacer;
        boolean originalColor = node.color;

        if (node.left == null || node.right == null) {
            childReplacer = deleteNodeWithZeroOrOneChild(node);
        } else {
            Node minimumOfRight = findMinimum(node.right);
            originalColor = minimumOfRight.color;
            node.data = minimumOfRight.data;
            childReplacer = deleteNodeWithZeroOrOneChild(minimumOfRight);
        }

        if (originalColor == BLACK) {
            fixAfterDelete(childReplacer);
            if (childReplacer.getClass() == NilNode.class) {
                replaceParent(childReplacer.parent, childReplacer, null);
            }
        }
    }

    private Node deleteNodeWithZeroOrOneChild(Node node) {
        if (node.left != null) {
            replaceParent(node.parent, node, node.left);
            return node.left;
        } else if (node.right != null) {
            replaceParent(node.parent, node, node.right);
            return node.right;
        } else {
            Node newChild = node.color == BLACK ? new NilNode() : null;
            replaceParent(node.parent, node, newChild);
            return newChild;
        }
    }

    private Node findMinimum(Node node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    private void fixAfterDelete(Node node) {
        if (node == root) {
            node.color = BLACK;
            return;
        }

        Node sibling = getSibling(node);

        if (sibling.color == RED) {
            handleRedSibling(node, sibling);
            sibling = getSibling(node);
        }

        if (isBlack(sibling.left) && isBlack(sibling.right)) {
            sibling.color = RED;

            if (node.parent.color == RED)
                node.parent.color = BLACK;
            else
                fixAfterDelete(node.parent);
        } else
            handleBlackSiblingWithAtLeastOneRedChild(node, sibling);
    }

    private void handleRedSibling(Node node, Node sibling) {
        sibling.color = BLACK;
        node.parent.color = RED;
        if (node == node.parent.left)
            rotateLeft(node.parent);
        else
            rotateRight(node.parent);
    }

    private void handleBlackSiblingWithAtLeastOneRedChild(Node node, Node sibling) {
        boolean nodeIsLeftChild = node == node.parent.left;

        // Case 5: Black sibling with at least one red child + "outer nephew" is black
        // --> Recolor sibling and its child, and rotate around sibling
        if (nodeIsLeftChild && isBlack(sibling.right)) {
            sibling.left.color = BLACK;
            sibling.color = RED;
            rotateRight(sibling);
            sibling = node.parent.right;
        } else if (!nodeIsLeftChild && isBlack(sibling.left)) {
            sibling.right.color = BLACK;
            sibling.color = RED;
            rotateLeft(sibling);
            sibling = node.parent.left;
        }

        // Case 6: Black sibling with at least one red child + "outer nephew" is red
        // --> Recolor sibling + parent + sibling's child, and rotate around parent
        sibling.color = node.parent.color;
        node.parent.color = BLACK;
        if (nodeIsLeftChild) {
            sibling.right.color = BLACK;
            rotateLeft(node.parent);
        } else {
            sibling.left.color = BLACK;
            rotateRight(node.parent);
        }
    }

    private Node getSibling(Node node) {
        Node parent = node.parent;
        if (node == parent.left)
            return parent.right;
        else
            return parent.left;
    }

    private boolean isBlack(Node node) {
        return node == null || node.color == BLACK;
    }

    private static class NilNode extends Node {
        private NilNode() {
            super("");
            this.color = BLACK;
        }
    }

    private void rotateRight(Node node) {
        Node parent = node.parent;
        Node leftChild = node.left;

        node.left = leftChild.right;
        if (leftChild.right != null)
            leftChild.right.parent = node;

        leftChild.right = node;
        node.parent = leftChild;

        replaceParent(parent, node, leftChild);
    }

    private void rotateLeft(Node node) {
        Node parent = node.parent;
        Node rightChild = node.right;

        node.right = rightChild.left;
        if (rightChild.left != null)
            rightChild.left.parent = node;

        rightChild.left = node;
        node.parent = rightChild;

        replaceParent(parent, node, rightChild);
    }

    private void replaceParent(Node parent, Node oldChild, Node newChild) {
        if (parent == null)
            root = newChild;
        else if (parent.left == oldChild)
            parent.left = newChild;
        else
            parent.right = newChild;

        if (newChild != null)
            newChild.parent = parent;
    }

    public void writeToFile() {
        try {
            FileWriter fw = new FileWriter("temp2.txt");
            KPList<Node> kpList = writeToFile(root);
            int index = 1;
            while (!kpList.isEmpty()) {
                Node node = kpList.first();
                if (node.rows.get(0) == index) {
                    fw.write(node.data);
                    fw.write(" ");
                    if (node.rows.size() == 1) {
                        kpList.dequeue();
                    } else {
                        kpList.first().rows.remove(0);
                    }
                } else {
                    fw.write('\n');
                    ++index;
                }
            }
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private KPList<Node> writeToFile(Node node) throws EmptyStackException, FullQueueException, FullStackException, EmptyQueueException {
        KPList<Node> kpList = new KPList<>();
        KPList<Node> result = new KPList<>();
        kpList.enqueue(node);
        while (!kpList.isEmpty()) {
            int size = kpList.size();
            for (int i=0; i < size; i++) {
                Node current = kpList.dequeue();
                result.enqueue(current);
                if (current.left != null)
                    kpList.enqueue(current.left);
                if (current.right != null)
                    kpList.enqueue(current.right);
            }
        }
        return result;
    }

    public void writeToFile2() {
        try {
            FileWriter fw = new FileWriter("temp2.txt");
            KPList<Node> kpList = writeToFile(root);
            while (!kpList.isEmpty()) {
                Node node = kpList.dequeue();
                fw.write(node.data + " - " + node.rows.toString() +"\n");
            }
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readAndBuild() {
        String[] output;
        try {
            BufferedReader br = new BufferedReader(new FileReader("temp.txt"));
            String temp = "";
            int index = 1;
            while ((temp = br.readLine()) != null) {
                output = temp.split(" ");
                for (int i=0; i < output.length; i++) {
                    if (output[i].charAt(output[i].length() - 1) == ','
                            || output[i].charAt(output[i].length() - 1) == '.') {
                        output[i] = output[i].substring(0, output[i].length()-1);
                    }
                }
                for (String string : output)
                    insertNode(string, index);
                ++index;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Node getRoot() {
        return root;
    }

    public void przejdzWszerz() throws EmptyQueueException, FullQueueException {
        przejdzWszerz(root);
    }

    private void przejdzWszerz(Node node) throws FullQueueException, EmptyQueueException {
        ArrayQueue<Node> queue = new ArrayQueue<>();
        queue.enqueue(node);
        System.out.println("PRZECHODZENIE WSZERZ");
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0; i < size; i++) {
                Node current = queue.dequeue();
                System.out.print(current.data + ", " + current.rows.toString() + " | ");
                if (current.left != null)
                    queue.enqueue(current.left);
                if (current.right != null)
                    queue.enqueue(current.right);
            }
            System.out.println();
        }
        System.out.println("KONIEC PRZECHODZENIA WSZERZ");
    }
}