package task_03.com.fmi.ai;

public class Demo {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        Board b = new Board(10000);
        b.minConflicts();

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
