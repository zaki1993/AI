package com.fmi.ai;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo {

    public static void main(String[] args) throws IOException {
        /*List bag = List.of(new Item(90, 150),
                new Item(130, 35),
                new Item(1530, 200),
                new Item(500, 160),
                new Item(150, 60),
                new Item(680, 45),
                new Item(270, 60),
                new Item(390, 40),
                new Item(230, 30),
                new Item(520, 10),
                new Item(110, 70),
                new Item(320, 30),
                new Item(240, 15),
                new Item(480, 10),
                new Item(730, 40),
                new Item(420, 70),
                new Item(420, 70),
                new Item(220, 80),
                new Item(70, 20),
                new Item(180, 12),
                new Item(40, 50),
                new Item(300, 10),
                new Item(900, 1),
                new Item(2000, 150));
*/
    	runKnapsack("data_01", 5000);
    	runKnapsack("data_02", 5000);
    }
    
    private static List<Item> readFile(String filePath) throws IOException {
    	
    	List result = null;
    	
    	try (Stream<String> fileStream = Files.lines(Paths.get(filePath))) {
    		result = fileStream.map(line -> new Item(line)).collect(Collectors.toList());
    	}
    	
    	return result;
    }
    
    private static void runKnapsack(String fileName, int maxGrams) throws IOException {
    	
    	List bag = readFile(fileName);
        Knapsack knapsack = new Knapsack(bag, maxGrams);
        List<Item> itemList = knapsack.calcSolution();

        if (knapsack.isCalculated()) {
        	
        	// to print additional info
        	/*System.out.println(knapsack.getMaxWeight() / 100.0);
        	System.out.println(knapsack.getSolutionWeight() / 100.0);
            System.out.println(itemList);*/
            
            System.out.println(knapsack.getProfit());
        } else {
            System.out.println("Unable to solve probelm..!");
        }
    }
}
