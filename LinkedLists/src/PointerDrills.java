public class PointerDrills {
    // Simple node
    static class Node {
        int val;
        Node next;
        Node(int v) { this.val = v; }
    }

    // 1) Add a new node at head - return new head
    static Node pushFront(Node head, int v) {
        // TODO: create a node and wire it before head
        Node newHead = new Node(v);
        newHead.next = head;
        return newHead;
    }

    // 2) Splice x after p - no return
    static void insertAfter(Node p, Node x) {
        // TODO: if p is null, do nothing
        // target: p -> x -> oldPnext
        if (p != null && x != null && x.next == null) {
            // save tail
            Node q = p.next; // saving value
            x.next = q; // pointing node forward
            p.next = x; // point head to new p.next (x in the middle of p and q)
        }
    }

    // 3) Delete node after p - return removed node or null
    static Node deleteAfter(Node p) {
        // TODO: handle p == null or p.next == null
        return null;
    }

    // 4) Reverse the whole list in place - return new head
    static Node reverse(Node head) {
        // TODO: classic 3-pointer loop
        return null;
    }

    // --------- helpers for tests ----------
    static Node fromArray(int... a) {
        Node head = null, tail = null;
        for (int v : a) {
            Node n = new Node(v);
            if (head == null) { head = tail = n; }
            else { tail.next = n; tail = n; }
        }
        return head;
    }

    static String toStringList(Node head) {
        StringBuilder sb = new StringBuilder("[");
        for (Node c = head; c != null; c = c.next) {
            sb.append(c.val);
            if (c.next != null) sb.append(" -> ");
        }
        return sb.append("]").toString();
    }

    static void check(String label, boolean ok) {
        if (!ok) throw new AssertionError("Fail: " + label);
        System.out.println("Pass: " + label);
    }

    // --------- quick self-tests you should pass ----------
    public static void main(String[] args) {
        // pushFront
        Node h = fromArray(2, 3);
        h = pushFront(h, 1);
        check("pushFront", toStringList(h).equals("[1 -> 2 -> 3]"));

        // insertAfter - insert 99 after the first node (value 1)
        Node first = h;           // node with 1
        insertAfter(first, new Node(99));
        check("insertAfter mid", toStringList(h).equals("[1 -> 99 -> 2 -> 3]"));

        // deleteAfter - remove the 99 we just added
        Node removed = deleteAfter(first);
        check("deleteAfter returns", removed != null && removed.val == 99);
        check("deleteAfter list", toStringList(h).equals("[1 -> 2 -> 3]"));

        // reverse - full list
        h = reverse(h);
        check("reverse 3", toStringList(h).equals("[3 -> 2 -> 1]"));

        // edge cases
        h = reverse(fromArray()); // empty
        check("reverse empty", toStringList(h).equals("[]"));
        h = reverse(fromArray(42)); // single
        check("reverse single", toStringList(h).equals("[42]"));

        // deleteAfter at tail - no change
        Node one = fromArray(5);
        check("deleteAfter tail", deleteAfter(one) == null && toStringList(one).equals("[5]"));

        System.out.println("All basic pointer drills passed.");
    }
}
