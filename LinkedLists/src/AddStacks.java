import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AddStacks {
    // Create 1 stack
    // Create 2 stack
    // Create Result stack
    // Add them

    public static void main(String[] args) {
        LinkedList<Integer> l1 = new LinkedList<>();
        l1.push(5);
        l1.push(3);
        l1.push(1);
        LinkedList<Integer> l2 = new LinkedList<>();
        l2.push(5);
        l2.push(3);
        l2.push(1);
        l2.push(5);
        LinkedList<Integer> resultList = addLists(l1, l2);
        System.out.println("Result: " + resultList);
    }

    private static LinkedList<Integer> addLists(LinkedList<Integer> l1, LinkedList<Integer> l2) {
        LinkedList<Integer> result = new LinkedList<>();

        List<Integer> a1 = new ArrayList<>(l1);
        List<Integer> a2 = new ArrayList<>(l2);

        int size1 = a1.size();
        int size2 = a2.size();

        int maxSize = Math.max(size1, size2);
        int carry = 0;

        for (int i = 0; i < maxSize; i++) {
            int element1 = i < size1 ? a1.get(i) : 0;
            int element2 = i < size2 ? a2.get(i) : 0;

            int sum = element1 + element2 + carry;
            result.push(sum % 10);
            carry = sum / 10;
        }
        if (carry > 0) {
            result.push(carry);
        }
        return result;
    }
}
