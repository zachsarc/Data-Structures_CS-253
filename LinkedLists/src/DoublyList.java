public class DoublyList {
    public static void main(String[] args) {
        Node L = new Node("L");
        Node R = new Node("R");
// make L <-> R
        L.next = R; R.prev = L;
        showD("Start", L);                   // [L] <-> [R]

        Node X = new Node("X");
// order: point X to neighbors first, then neighbors to X
        X.prev = L;                          // 1) backlink
        X.next = R;                          // 2) forward link
        if (L != null) L.next = X;           // 3) L points to X
        if (R != null) R.prev = X;           // 4) R points back to X
        showD("After insert", L);            // [L] <-> [X] <-> [R]

        // Removing x from between L and R

        L = X.prev;
        R = X.next;
        if (L != null) L.next = R;
        if (R != null) R.prev = L;
        X.prev = X.next = null;              // detach
        showD("After Removing", L);
    }


    static void showD(String label, Node leftmost) {
        System.out.print(label + "  ");
        Node cur = leftmost;
        while (cur.prev != null) cur = cur.prev;   // ensure we are at head
        while (cur != null) {
            System.out.print("[" + cur.v + "]");
            if (cur.next != null) System.out.print(" <-> ");
            cur = cur.next;
        }
        System.out.println();
    }

}
