package items;

import items.itemExceptions.*;

import java.util.ArrayList;
import java.util.Random;

public class Box extends Container {
//    private boolean isBoxClosed = false;


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
    public OneItem takeItem() throws CannotAccessTheContainer {
        if (checkIsContainerClosed()) {
            System.out.println("The box is closed, can't take anything.");
            throw new CannotAccessTheContainer("You're trying to get an item from the closed box");
        }
        int randomIndex = new Random().nextInt(getCurrentSize());
        return getItemContainer().get(randomIndex); // take random
    }

    // it's copy of the removeItem() from the Bag class
    @Override
    public void removeItem() throws ItemIsEmptyException, CannotAccessTheContainer {
        super.removeItem();
        int index = new Random().nextInt(getCurrentSize() + 1);
        OneItem itemForDelete = getItemContainer().get(index);
        itemForDelete.itemRemoved(); // make isAdded = false

        System.out.println("DELETE: " + itemForDelete + " has deleted!"); // maybe doesn't need
        if (getItemContainer().get(index) instanceof Container)
            ((Container) getItemContainer().get(index)).openContainer();

        changeWeight(-getItemContainer().get(index).getWeight());
        getItemContainer().remove(index);
    }

    // you can make: if closed, then transform into a stack with two items, the first one is the closed box;
    @Override
    public void pushItem(OneItem newItem) throws ItemAlreadyPlacedException, ItemStoreException, AddTheSameException, CannotAccessTheContainer {
        addItem(newItem);
        changeWeight(newItem.getWeight());
    }

    @Override
    public String toString() {
        return "Box is closed?: " + checkIsContainerClosed() + " " + super.toString();
    }
}
