public class LinkedListPractice {
    Node head;
    public LinkedListPractice() {
        this.head = null;
    }

    void insert(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            return;
        } else {
            Node cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = newNode;
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
        if (this.head == null) {
            return -1;
        }
        // Single node
        if (this.head.next == null) {
            int value = this.head.data;
            this.head = null; // list becomes empty
            return value;
        }

        // Two or more nodes
        Node cur = this.head;
        Node prev = null;
        int value = this.head.data;

        while (cur.next != null) {
            prev = cur;
            cur = cur.next;
            prev.next = null;
        }
        return value;
    }

    int deleteLast() {
        if (this.head == null) return  -1; // List is empty
        if (this.head.next == null) { // Single node
            int value = this.head.data;
            this.head = null;
            return value;
        }
        Node prev = head, cur = head.next;
        while (cur.next != null) {
            prev = cur;
            cur = cur.next;
        }
        prev.next = null;
        return cur.data;
    }

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        LinkedListPractice ll = new LinkedListPractice();
        int five = 5;
        Node newNode = new Node(five);
        ll.addHead(five);
        ll.addHead(3);
        ll.addHead(12);
        ll.addHead(63);
        ll.printList();
        ll.deleteLast();
        ll.printList();
    }

    void printList() {
        LinkedListPractice.Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null"); // Marks the end of the list
    }
}
