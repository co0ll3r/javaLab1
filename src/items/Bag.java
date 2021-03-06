package items;

import items.itemExceptions.*;

import java.util.ArrayList;
import java.util.Random;

public class Bag extends Container {
    public Bag(String name, double weight, String... properties) {
        super(name, weight, properties);
    }

    public Bag(String name, double weight, int maxItems, int maxWeight, String... properties) {
        super(name, weight, maxItems, maxWeight, properties);
    }

    public Bag(String name, double weight, ArrayList<OneItem> newContainer, int maxItems, int maxWeight, String... properties) {
        super(name, weight, newContainer, maxItems, maxWeight, properties);
    }

    @Override
    public OneItem takeItem() throws CannotAccessTheContainer {
        if (checkIsContainerClosed()) {
            System.out.println("The " + getName() + " is closed, can't take anything.");
            throw new CannotAccessTheContainer("You're trying to get an item from the closed box");
        }
        int randomIndex = new Random().nextInt(getCurrentSize());
        return getItemContainer().get(randomIndex); // take random
    }

    @Override
    public void removeItem() throws ItemIsEmptyException, CannotAccessTheContainer {
        super.removeItem();
        int index = new Random().nextInt(getItemContainer().size());

        OneItem itemForDelete = getItemContainer().get(index);
        itemForDelete.itemRemoved(); // make isAdded = false

        System.out.println("DELETE: " + itemForDelete + " has deleted!"); // maybe doesn't need
        if (itemForDelete instanceof Container) {
            ((Container) itemForDelete).openContainer();
        }

        changeWeight(-getItemContainer().get(index).getWeight());
        getItemContainer().remove(index);
    }

    @Override
    public void addItem(OneItem newItem) throws ItemAlreadyPlacedException, ItemStoreException, AddTheSameException, CannotAccessTheContainer {
        super.addItem(newItem);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

