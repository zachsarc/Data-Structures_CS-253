public class LinkedListClassImplementation {
    Node head;
    public LinkedListClassImplementation() {
        this.head = null;
    }

    void insert(int data) {
        Node newNode = new Node(data);

        if (this.head == null) {
            this.head = newNode;
        } else {
            Node cur = this.head;
            // this loop travels through the linked list
            while (cur.next != null) { // get last node
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
            return -1;
        } else {
            value = this.head.data;
            this.head = this.head.next;
        }
        return value;
    }

    int deleteTail() {
        int value;
        if (this.head == null) {
            return -1;
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

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
}
