import java.util.Stack;

public class DelimiterMatching {
    // push "("
    // pop ")"
    public static void main(String[] args) {
        System.out.println(delimit("Hello (2x-32)(()( asd"));
    }

    public static Stack<Character> delimit(String sen) {
        Stack<Character> sOne = new Stack<>();
        char[] arr = sen.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            boolean result = Character.toString(arr[i]).matches("\\(");
            if (result) {
                sOne.push(arr[i]);
            } else if (Character.toString(arr[i]).matches("\\)")) {
                if (!sOne.isEmpty()) {
                    sOne.pop();
                } else {
                    System.out.println("Unmatched closing parenthesis at index: " + i);
                }
            }
        }
        return sOne;
    }
}
