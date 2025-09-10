import java.util.LinkedList;

public class LlCode {
    public static void main(String[] args) {
        LinkedList<NodeO> myList = new LinkedList<>();
        NodeO nodeOne = new NodeO("hi");
        NodeO nodeTwo = new NodeO("hello");
        NodeO nodeThree = new NodeO("Ciao");
        myList.add(0, nodeOne);
        myList.add(1, nodeTwo);
        myList.add(2, nodeThree);

        for (NodeO node : myList) {
            System.out.println(node);
        }
    }
}
