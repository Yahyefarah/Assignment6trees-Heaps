import java.util.ArrayList;
import java.util.List;

public class BRTree {

    // Node class for the BR Tree
    static class Node {
        int key;
        Node left;
        Node right;
        Node parent;
        boolean color; // true/false for red or black

        public Node(int key) {
            this.key = key;
            left = null;
            right = null;
            parent = null;
        }
    }


    private Node root;


    public BRTree() {
        root = null;
    }

    //  delete all nodes with keys in the inclusive range [a, b]
    public void deleteRange(int a, int b) {
        // Store all keys that should be deleted
        List<Integer> keysToDelete = new ArrayList<>();

        // First collect all keys in the range
        collectInRange(root, a, b, keysToDelete);

        // Then delete them one by one
        for (int key : keysToDelete) {
            root = deleteNode(root, key);
        }
    }

    // this  function helps me to collect keys in the range [a, b]
    private void collectInRange(Node node, int a, int b, List<Integer> list) {
        // Base case
        if (node == null) {
            return;
        }

        // Search left subtree if there may be values >= a
        if (node.key > a) {
            collectInRange(node.left, a, b, list);
        }

        // Add current key if it is in the range
        if (node.key >= a && node.key <= b) {
            list.add(node.key);
        }

        // Search right subtree if there may be values <= b
        if (node.key < b) {
            collectInRange(node.right, a, b, list);
        }
    }

    // i use Simple BST-style deletion as in the zybooks
    private Node deleteNode(Node node, int key) {
        if (node == null) {
            return null;
        }

        // Find the node to delete
        if (key < node.key) {
            node.left = deleteNode(node.left, key);
        } else if (key > node.key) {
            node.right = deleteNode(node.right, key);
        } else {
            // Case 1: no child
            if (node.left == null && node.right == null) {
                return null;
            }

            // Case 2: one child
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            // Case 3: two children
            // Replace with smallest value in right subtree
            Node minNode = findMin(node.right);
            node.key = minNode.key;
            node.right = deleteNode(node.right, minNode.key);
        }

        return node;
    }

    // Find the smallest node in a subtree
    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Insert method for testing
    public void insert(int key) {
        root = insertRec(root, null, key);
    }

    private Node insertRec(Node node, Node parent, int key) {
        if (node == null) {
            Node newNode = new Node(key);
            newNode.parent = parent;
            return newNode;
        }

        if (key < node.key) {
            node.left = insertRec(node.left, node, key);
        } else if (key > node.key) {
            node.right = insertRec(node.right, node, key);
        }

        return node;
    }

    // Print inorder to check result
    public void printInOrder() {
        printInOrderRec(root);
        System.out.println();
    }

    private void printInOrderRec(Node node) {
        if (node == null) {
            return;
        }

        printInOrderRec(node.left);
        System.out.print(node.key + " ");
        printInOrderRec(node.right);
    }

    // Test the program
    public static void main(String[] args) {
        BRTree tree1 = new BRTree();
        int[] values = {10, 19, 20, 30, 42, 55, 77};

        for (int v : values) {
            tree1.insert(v);
        }

        System.out.print("Original tree: ");
        tree1.printInOrder();

        tree1.deleteRange(15, 20);
        System.out.print("After deleteRange(15, 20): ");
        tree1.printInOrder();

        BRTree tree2 = new BRTree();
        for (int v : values) {
            tree2.insert(v);
        }

        tree2.deleteRange(0, 2);
        System.out.print("After deleteRange(0, 2): ");
        tree2.printInOrder();

        BRTree tree3 = new BRTree();
        for (int v : values) {
            tree3.insert(v);
        }

        tree3.deleteRange(25, 60);
        System.out.print("After deleteRange(25, 60): ");
        tree3.printInOrder();
    }
}
