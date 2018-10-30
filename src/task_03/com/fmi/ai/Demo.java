package task_03.com.fmi.ai;

public class Demo {

    public static void main(String[] args) {
        Board b = new Board(8);
        System.out.println("Generated board..!");
        b.print();

        b.minConflicts();

        System.out.println("Solved..!");
        b.print();
    }
}
