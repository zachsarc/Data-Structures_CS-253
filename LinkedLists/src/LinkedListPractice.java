import java.nio.file.Path;

public class LinkedListPractice {
    Node head;
    public LinkedListPractice() {
        this.head = null;
    }

    void insert(int data) {
        Node newNode = new Node(data);

        if (this.head == null) {
            this.head = newNode;
        } else {
            Node cur = newNode.next;
            while (newNode.next != null) {
                cur = cur.next;
            }
            this.head = cur;
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

    int deleteHead() {
        int value;

        if (this.head == null) {
            return -1; // the list is empty
        } else {
            value = this.head.data;
            this.head = this.head.next;
        }
        return value;
    }

    int deleteTail() {
        int value;
        if (this.head == null) {
            return -1; // no list
        } else {
            Node cur = this.head;
            Node prev = null;
            while (cur.next != null) {
                prev = cur;
                cur = cur.next;
            }
            value = cur.data;
            prev.next = null;
        }
        return value;
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
