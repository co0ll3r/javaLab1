package items;

import items.itemExceptions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

abstract class Container extends OneItem implements Iterable<OneItem> {
    private List<OneItem> itemContainer;
    private int currentSize;
    private int maxItems = 10;
    private int maxWeight = 15;
    private boolean isContainerClosed = false;

    Container(String name, double weight, String... properties) {
        super(name, weight, properties);
        itemContainer = new ArrayList<>();
    }

    Container(String name, double weight, int maxItems, int maxWeight, String... properties) {
        this(name, weight, properties);// this();// c!!!
        this.maxItems = maxItems;
        this.maxWeight = maxWeight;
    }

    Container(String name, double weight, ArrayList<OneItem> newContainer, int maxItems, int maxWeight, String... properties) {
        this(name, weight, maxItems, maxWeight, properties);
        this.itemContainer = newContainer;
        this.currentSize = itemContainer.size();
    }

    List<OneItem> getItemContainer() {
        return itemContainer;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    // instead of calculating weight of all the container
    void changeWeight(double value) {
        setWeight(getWeight() + value);
    }

    void addItem(OneItem newItem) throws ItemAlreadyPlacedException, ItemStoreException, CannotAccessTheContainer, AddTheSameException {
        if (checkIsContainerClosed())
            throw new CannotAccessTheContainer("You can't add this item. ", this.getName());
        if (newItem.isAdded())
            throw new ItemAlreadyPlacedException();
        if (newItem == this)
            throw new AddTheSameException("You're trying to add an item the same item!");
        if (currentSize >= maxItems)
            throw new ItemStoreException(newItem, this.getName() + " overflow! You're trying to put " + (currentSize + 1) +
                    " items in " + this.getName() + ", when the maximum is " + maxItems + ".");
        if (this.getWeight() + newItem.getWeight() > maxWeight)
            throw new ItemStoreException(this.getName() + " overweight! The weight would be " + (getWeight() +
                    newItem.getWeight()) + ", when the maximum is " + maxWeight + ".", newItem);

        newItem.itemAdded();
        currentSize++;
        itemContainer.add(newItem);
        changeWeight(newItem.getWeight());
        // not sure about exact this implementation
        if (newItem instanceof Container)
            ((Container) newItem).closeContainer();
    }

    void removeItem() throws ItemIsEmptyException, CannotAccessTheContainer {
        if (checkIsContainerClosed())
            throw new CannotAccessTheContainer("You can't delete this item. ", this.getName());
        if (getCurrentSize() == 0)
            throw new ItemIsEmptyException();
        currentSize--;
    }

   /**
     * Polite version of findByName without exceptions
     *
     * @param name String not null
     * @return true if the container has the equal name, otherwise return false, even if the container is empty
     */
    public boolean containItem(String name) {
        if (getCurrentSize() == 0)
            return false;
        for (OneItem a :
                getItemContainer()) {
            if (name.equals(a.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Use
     *
     * @param name String not null
     * @return OneItem or NULL if the container doesn't have an item with such the name
     */
    public OneItem findByName(String name) throws ItemIsEmptyException {
        if (getCurrentSize() == 0)
            throw new ItemIsEmptyException();
        for (OneItem a :
                getItemContainer()) {
            //equalsIgnoreCase - alternative
            if (name.equals(a.getName())) {
                System.out.println("The item has found");
                return a;
            }
        }
        System.out.println("The item hasn't found");
        return null;
    }

    @Override
    public void getInfo() {
        int level = 1;
        getInfo(level);
    }

    // It made in such a way only for output hierarchy
    private void getInfo(int level) {
        super.getInfo();
        System.out.print("items: " + getCurrentSize() + "\n");
        for (OneItem a :
                itemContainer) {
            for (int i = 0; i < level; i++)
                System.out.print("\t");
            if (a instanceof Container)
                ((Container) a).getInfo(level + 1);
            else {
                a.getInfo();
            }
        }
        if (getCurrentSize() != 0)
            System.out.println();
    }

    /**
     * three methods to resolve the problem,
     * when you're trying to add an item to a container, that's holds in an another container.
     */
    public void openContainer() {
        isContainerClosed = false;
    }

    public void closeContainer() {
        isContainerClosed = true;
    }

    boolean checkIsContainerClosed() {
        return isContainerClosed;
    }

    @Override
    public Iterator<OneItem> iterator() {
        return new Iterator<>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < currentSize && itemContainer.get(currentIndex) != null; // currentSize - 1?
            }

            @Override
            public OneItem next() {
                return itemContainer.get(currentIndex++);
            }
        };
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format(" Current size: %d; MaxItems: %d; MaxWeight: %d; items in the container: %s",
                        getCurrentSize(), maxItems, maxWeight, getItemContainer().toString());
    }

    abstract OneItem takeItem() throws CannotAccessTheContainer;

    abstract void pushItem(OneItem newItem) throws ItemAlreadyPlacedException, ItemStoreException, AddTheSameException, CannotAccessTheContainer;

}
