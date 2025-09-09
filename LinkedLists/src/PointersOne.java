public class PointersOne {
    public static void main(String[] args) {
        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        A.next = C;                      // Build A -> C
        show("Start", A);                // [A]->[C]->null

        // Insert B after A, step by step:
        Node p = A;                      // p is the node after which we insert
        Node q = p.next;                 // 1) save tail part (q = C)
        // If you set p.next = B first, you lose the original p.next and cannot hook B.next to C.
        // Saving q = p.next first prevents losing the tail.
        showVars("after q=p.next", A, p, B, q);

        B.next = q;                      // 2) point new node to tail (B -> C)
        showVars("after B.next=q", A, p, B, q);

        p.next = B;                      // 3) splice in (A -> B)
        showVars("after p.next=B", A, p, B, q);

        show("Result", A);               // [A]->[B]->[C]->null
    }

    static void show(String label, Node head) {
        System.out.print(label + "  ");
        Node cur = head;
        while (cur != null) { System.out.print("[" + cur.v + "]->"); cur = cur.next; }
        System.out.println("null");
    }
    static void showVars(String label, Node head, Node p, Node x, Node q) {
        show(label, head);
        System.out.println("   p=" + p.v + ", x=" + x.v + ", q=" + (q==null? "null" : q.v));
    }

}
