package task_04.com.fmi.ai;

public class Item {

    private final String name;
    private final int weight;
    private final int value;

    private boolean isInKnapsack;

    public Item(String name, int weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
        setInKnapsack(false);
    }

    public void setInKnapsack(boolean isInKnapsack) {
        this.isInKnapsack = isInKnapsack;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public boolean getInKnapsack() {
        return isInKnapsack;
    }

    @Override
    public String toString() {
        return new StringBuilder("Item@").append(hashCode())
                                         .append("{")
                                         .append("name: ").append(name).append(", ")
                                         .append("weight: ").append(weight).append(", ")
                                         .append("value: ").append(value)
                                         .append("}").toString();
    }
}