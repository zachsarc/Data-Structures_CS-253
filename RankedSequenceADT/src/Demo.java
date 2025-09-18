public class Demo {
    public static void main(String[] args) {
        RankedSequenceUsingArray<Integer> rs = new RankedSequenceUsingArray<>(2);
        rs.pushBack(10);
        rs.pushBack(20);
        rs.pushBack(30);
        rs.replaceItem(1, 99);
        System.out.println(rs.returnItem(0)); // 10
        System.out.println(rs.returnItem(1));
        System.out.println(rs.returnItem(2)); // 30
    }
}
