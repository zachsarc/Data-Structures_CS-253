public class DoublyLinkedListImplementation {
    int[] list = new int[8];
    int top = -1;
    int end = -1;

    void insert(int data) {
        if (top == -1) {
            list[++top] = data;
            end = top;
        } else if (top != list.length){
            list[++top] = data;
        } else if (end == 0){
            System.out.println("Array is full");
        } else {
            list[0] = data;
            top = 0;
        }
    }

    int deleteTop() {
        int value;
        if (top == -1) {
            return -1;
        } else if (top == end){
            value = list[top];
            top = -1;
            end = -1;
        } else {
            value = list[top--];
        }
        return value;
    }
}
