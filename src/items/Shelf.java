package items;

import items.itemExceptions.*;

public class Shelf extends Container {
    public Shelf(String name, double weight, String... properties) {
        super(name, weight, properties);
    }

    public Shelf(String name, double weight, int maxItems, int maxWeight, String... properties) {
        super(name, weight, maxItems, maxWeight, properties);
    }

    @Override
    public OneItem takeItem() {
        return getItemContainer().get(getCurrentSize());
    }

    @Override
    public void removeItem() throws ItemIsEmptyException, CannotAccessTheContainer {
        super.removeItem();
        OneItem itemRem = getItemContainer().get(getCurrentSize());
        if (itemRem instanceof Container)
            ((Container) itemRem).openContainer();
        changeWeight(-(itemRem.getWeight()));
        getItemContainer().remove(getCurrentSize());
    }

    // make flat constraints
    @Override
    public void pushItem(OneItem newItem) throws ItemAlreadyPlacedException, ItemStoreException, AddTheSameException, CannotAccessTheContainer {
        for (String a :
                newItem.getProperties()) {
            if (a.equalsIgnoreCase("flat")) {
                addItem(newItem);
                return;
            }
        }
        System.err.println("Can't add not flat item!");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
