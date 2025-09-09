public class SinglyList {
    public static void main(String[] args) {
        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        A.next = B; B.next = C;
        show("Start", A);                    // [A]->[B]->[C]->null

        Node p = A;                          // remove after p
        Node x = p.next;                     // 1) x is the one we remove (B)
        Node q = x.next;                     // 2) save tail (C)
        showVars("saved x and q", A, p, x, q);

        p.next = q;                          // 3) bypass x (A -> C)
        x.next = null;                       // 4) detach removed node (optional)
        show("Result", A);                   // [A]->[C]->null
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
