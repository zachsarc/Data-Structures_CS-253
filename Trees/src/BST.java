public class BST {
    NodeP root;

    public BST() {
        root = null;
    }

    // Method to insert key into tree
    public void insert(int key) {
        root = insertRec(root, key);
    }

    // Helper method for insert
    private NodeP insertRec(NodeP root, int key) {
        if (root == null) {
            return new NodeP(key);
        }

        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }
        return root;
    }

    // Method to print the tree in order
    public void inorder() {
        inOrderRec(root);
    }

    // Helper method for inorder
    private void inOrderRec(NodeP root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.println(root.key + " ");
            inOrderRec(root.right);
        }
    }

    // Method to search the tree
    public boolean search(int key) {
        return searchRec(root, key);
    }

    // Helper method for search
    private boolean searchRec(NodeP root, int key) {
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

    public static void main(String[] args) {
        BST tree = new BST();

        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        tree.inorder();

        System.out.println("Search: ");
        System.out.println(tree.search(40)); // expect true
        System.out.println(tree.search(99)); // expect false

    }

}
