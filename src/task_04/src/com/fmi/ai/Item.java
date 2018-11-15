package com.fmi.ai;

public class Item {

    private final int weight;
    private final int value;
    
    public Item(String line) {
    	
    	String[] parts = line.split(" ");
    	this.weight = Integer.parseInt(parts[0]);
    	this.value = Integer.parseInt(parts[1]);
    }

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return new StringBuilder("Item@").append(hashCode())
                                         .append("{")
                                         .append("weight: ").append(weight).append(", ")
                                         .append("value: ").append(value)
                                         .append("}").toString();
    }
}