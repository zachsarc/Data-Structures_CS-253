public class DoublyLinked {
    Node head;
    Node prev;
    Node last;
    public DoublyLinked() {
        this.head = null;
        this.prev = null;
    }
    void insert(int data) {
        Node newNode = new Node(data);

        if (this.head == null) { // If no head, assign head to new Node
            this.head = newNode;
        } else {
            Node cur = this.head;
            // this loop travels through the linked list
            while (cur.next != null) { // get last node
                cur = cur.next;
            }
            cur.next = newNode;
            newNode.prev = cur;
        }
    }

    void addHead(int data) {
        Node newNode = new Node(data);
        if (this.head == null) {
            this.head = newNode;
        } else {
            this.head.prev = newNode;
            newNode.next = this.head;
            this.head = newNode;
        }
    }

    void addFirst(int data) {
        Node newNode = new Node(data);

        if (this.head == null && this.last == null) {
            this.head = this.last = newNode;
        } else {
            newNode.next = this.head;
            this.head.prev = newNode;
            this.head = newNode;
        }
    }

    // add deleting

    // Testing
    public static void main(String[] args) {
        LinkedListClassImplementation ll = new LinkedListClassImplementation();
        ll.insert(20);
        ll.insert(30);
        ll.insert(40);
        System.out.println("Breakpoint");
        ll.printList();
    }

    void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null"); // Marks the end of the list
    }


    static class Node {
        int data;
        Node next;
        Node prev;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
}