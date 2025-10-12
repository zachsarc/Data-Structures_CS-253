public class BinaryTree {
    Node root;

    public BinaryTree() {
        root = null;
    }

    // Insert a new node with a given key
    public void insert(int key) {
        root = insertRec(root, key);
    }

    // Insert a new key in BST
    private Node insertRec(Node root, int key) {
        // If the tree is empty, return a new node
        if (root == null) {
            root = new Node(key);
            return root;
        }

        // Else, recur down the tree
        if (key < root.key){
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }
        return root;
    }

    // Method to print the tree inorder
    public void inorder() {
        inorderRec(root);
    }

    // Inorder Traversal of BST
    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    // Search the tree for a key
    public boolean search (int key) {
        return searchRec(root, key);
    }

    // Search the tree for a key in BST
    private boolean searchRec(Node root, int key) {
        if (root == null) {
            return false;
        }
        if (root.key == key) {
            return true;
        }
        if (key < root.key) {
            return searchRec(root.left, key);
        } else {
            return searchRec(root.right, key);
        }
    }

    // Find minimum value in the tree
    public int findMin() {
        return findMinRec(root);
    }

    // Find minimum value in BST
    private int findMinRec(Node root) {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        if (root.left == null) {
            return root.key;
        }
        return findMinRec(root.left);
    }

    // Find maximum value in a tree
    public int findMax() {
        return findMaxRec(root);
    }

    // Find maximum value in BST
    private int findMaxRec(Node root) {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        if (root.right == null) {
            return root.key;
        }
        return findMaxRec(root.right);
    }
}
class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}
