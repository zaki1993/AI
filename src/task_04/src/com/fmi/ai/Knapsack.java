package com.fmi.ai;

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

        if (n > 0 && maxWeight > 0) {
            List<List<Integer>> holder = new ArrayList<>();
            List<Integer> currHolder = new ArrayList<>();

            holder.add(currHolder);
            for (int j = 0; j <= maxWeight; j++) {
            	currHolder.add(0);
            }

            // loop through the items
            for (int i = 1; i <= n; i++) {
                List<Integer> prev = currHolder;
                holder.add(currHolder = new ArrayList<>());
                for (int j = 0; j <= maxWeight; j++) {
                    if (j > 0) {
                        int weight = bag.get(i - 1).getWeight();
                        
                        // if the current item's weight is bigger then j then add it to the list
                        // otherwise get the max weight from the previous item and the current item + the difference of j and weight
                        currHolder.add((weight > j) ? prev.get(j) :
                                        		Math.max(prev.get(j), bag.get(i - 1).getValue() + prev.get(j - weight)));
                    } else {
                    	currHolder.add(0);
                    }
                }
            }
            profit = currHolder.get(maxWeight);

            for (int i = n, j = maxWeight; i > 0 && j >= 0; i--) {
                int tmpI = holder.get(i).get(j);
                int tmpIPrev = holder.get(i - 1).get(j);
                if ((i == 0 && tmpI > 0) || (i > 0 && tmpI != tmpIPrev)) {
                    Item item = bag.get(i - 1);
                    int weight = item.getWeight();
                    j -= weight;
                    solutionWeight += weight;
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
}