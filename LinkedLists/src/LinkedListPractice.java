import java.nio.file.Path;

public class LinkedListPractice {
    Node head;
    public LinkedListPractice() {
        this.head = null;
    }

    void insert(int data) {
        Node newNode = new Node(data); // create node

        if (this.head == null) { // check if there's no head
            this.head = newNode;
        } else {
            Node cur = this.head;
            while (cur.next != null) { // get last node
                cur = cur.next; // assign the current node to the last node
            }
            cur.next = newNode; // assign the newNode to the now last node
        }
    }

    void addHead(int data) {
        Node newNode = new Node(data);

        if (this.head == null) {
            this.head = newNode;
        } else {
            newNode.next = this.head;
            this.head = newNode;
        }
    }

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
}
