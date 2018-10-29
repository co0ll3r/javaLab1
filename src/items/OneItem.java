package items;

import items.itemExceptions.*;

import java.util.*;

public class OneItem {
    private String name;
    private double weight;
    private Set<String> properties;
    private boolean isAdded;

    public OneItem(String name, double weight, String... properties) {
        this.name = name;
        this.weight = weight;
        if (properties.length > 0) {
            this.properties = new HashSet<>();
            this.properties.addAll(Arrays.asList(properties));
        }
    }

    void itemAdded() {
        isAdded = true;
    }

    void itemRemoved() {
        isAdded = false;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    void setWeight(double weight) {
        this.weight = weight;
    }

    Set<String> getProperties() {
        return properties;
    }

    // any alternatives?
    boolean isAdded() {
        return isAdded;
    }

    // what will happen if the fields is empty?
    public void getInfo() {
        System.out.print("Name: " + getName());

        if (weight > 0) { // Is it works for double?
            System.out.print(", Weight: " + getWeight());
        }

        if (properties != null) {
            System.out.print(", Properties: { ");
            for (String e :
                    getProperties()) {
                System.out.print(e + " ");
            }
            System.out.print("}");
        }

        System.out.print("; ");
    }

    // properties are shown with brackets [], fix it?
    @Override
    public String toString() {
        return String.format("Name: %s; Weight: %.2f; " + (isAdded ? "Already added" : "Not added") +
                "; properties: %s.", name, weight, properties.toString());
    }
}

