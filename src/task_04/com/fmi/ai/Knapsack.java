package task_04.com.fmi.ai;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

public class Knapsack {

    private List<Item> bag;
    private int maxWeight;
    private int solutionWeight;
    private int profit;
    private boolean calculated;

    public Knapsack(List<Item> bag, int maxWeight) {
        this.bag = bag;
        this.maxWeight = maxWeight;
        this.calculated = false;
        this.profit = 0;
        this.solutionWeight = 0;
    }

    public List<Item> calcSolution() {

        int n = bag.size();
        setIsInKnapsack(false);

        if (n > 0 && maxWeight > 0) {
            List<List<Integer>> c = new ArrayList<>();
            List<Integer> curr = new ArrayList<>();

            c.add(curr);
            for (int j = 0; j <= maxWeight; j++) {
                curr.add(0);
            }

            for (int i = 1; i <= n; i++) {
                List<Integer> prev = curr;
                c.add(curr = new ArrayList<>());
                for (int j = 0; j <= maxWeight; j++) {
                    if (j > 0) {
                        int wH = bag.get(i - 1).getWeight();
                        curr.add(
                                (wH > j)
                                        ?
                                        prev.get(j)
                                        :
                                        Math.max(
                                                prev.get(j),
                                                bag.get(i - 1).getValue() + prev.get(j - wH)
                                        )
                        );
                    } else {
                        curr.add(0);
                    }
                }
            }
            profit = curr.get(maxWeight);

            for (int i = n, j = maxWeight; i > 0 && j >= 0; i--) {
                int tempI = c.get(i).get(j);
                int tempI_1 = c.get(i - 1).get(j);
                if (
                        (i == 0 && tempI > 0)
                                ||
                                (i > 0 && tempI != tempI_1)
                        ) {
                    Item iH = bag.get(i - 1);
                    int wH = iH.getWeight();
                    iH.setInKnapsack(true);
                    j -= wH;
                    solutionWeight += wH;
                }
            }
            calculated = true;
        }
        return bag;
    }

    public int getProfit() {
        return profit;
    }

    public int getSolutionWeight() {
        return solutionWeight;
    }

    public boolean isCalculated() {
        return calculated;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    private void setIsInKnapsack(boolean inKnapsack) {
        bag.forEach(item -> item.setInKnapsack(inKnapsack));
    }
}