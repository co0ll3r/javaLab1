package items;

import items.itemExceptions.*;

import java.util.ArrayList;
import java.util.Random;

public class Box extends Bag{
    public Box(String name, double weight, String... properties) {
        super(name, weight, properties);
        getProperties().add("flat");
    }

    public Box(String name, double weight, int maxItems, int maxWeight, String... properties) {
        super(name, weight, maxItems, maxWeight, properties);
        getProperties().add("flat");
    }

    public Box(String name, double weight, ArrayList<OneItem> newContainer, int maxItems, int maxWeight, String... properties) {
        super(name, weight, newContainer, maxItems, maxWeight, properties);
        getProperties().add("flat");
    }

    @Override
    public String toString() {
        return "Box is closed?: " + checkIsContainerClosed() + " " + super.toString();
    }
}
