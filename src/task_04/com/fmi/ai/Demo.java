package task_04.com.fmi.ai;

import java.util.List;

public class Demo {

    public static void main(String[] args) {
        List bag = List.of(new Item("map", 90, 150),
                new Item("compass", 130, 35),
                new Item("water", 1530, 200),
                new Item("sandwich", 500, 160),
                new Item("glucose", 150, 60),
                new Item("tin", 680, 45),
                new Item("banana", 270, 60),
                new Item("apple", 390, 40),
                new Item("cheese", 230, 30),
                new Item("beer", 520, 10),
                new Item("suntan cream", 110, 70),
                new Item("camera", 320, 30),
                new Item("T-shirt", 240, 15),
                new Item("trousers", 480, 10),
                new Item("umbrella", 730, 40),
                new Item("waterproof trousers", 420, 70),
                new Item("waterproof overclothers", 420, 70),
                new Item("note-case", 220, 80),
                new Item("sunglasses", 70, 20),
                new Item("towel", 180, 12),
                new Item("socks", 40, 50),
                new Item("book", 300, 10),
                new Item("notebook", 900, 1),
                new Item("tent", 2000, 150));

        Knapsack knapsack = new Knapsack(bag, 5250);
        List<Item> itemList = knapsack.calcSolution();

        if (knapsack.isCalculated()) {
            System.out.println("Max weight: " + knapsack.getMaxWeight() / 100.0 + " kg");
            System.out.println("Solution weight: " + knapsack.getSolutionWeight() / 100.0 + " kg");
            System.out.println("Solution value: " + knapsack.getProfit());
            System.out.println("The following items can be carries in the bag: ");
            System.out.println(itemList);
        } else {
            System.out.println("Unable to solve probelm..!");
        }

    }
}
