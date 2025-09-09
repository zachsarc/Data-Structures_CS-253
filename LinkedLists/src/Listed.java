import java.util.LinkedList;

public class Listed {
    public static void main(String[] args) {
        LinkedList<Integer> ll = new LinkedList<>();
        ll.push(4);
        ll.push(5);
        ll.push(4);
        System.out.println("LinkedList currently holds " + ll.size() + " elements");

        System.out.println("The top element in the linked list is: " + ll.peek());
        System.out.println("After the pop there will be " + ll.size() + " elements " + "and the popped elements value was: " + ll.pop());
    }
}
