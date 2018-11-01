package tests;

import items.*;
import items.itemExceptions.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BagTest {

    @org.junit.jupiter.api.Test
    void constructorsTest() {

    }

    @org.junit.jupiter.api.Test
    void putInTheSame() throws CannotAccessTheContainer, ItemAlreadyPlacedException, AddTheSameException, ItemStoreException {
        Bag bag1 = new Bag("bag1", 0.5, "white");
        Bag bag2 = new Bag("bag1", 0.5, "white");
        Bag bag3 = new Bag("bag1", 0.5, "white");
        Bag bag4 = new Bag("bag1", 0.5, "white");
        Bag bag5 = new Bag("bag1", 0.5, "white");
        OneItem item1 = new OneItem("cat", 7, "black", "fluffy", "cute");

        bag1.addItem(item1);
        assertThrows(AddTheSameException.class, () -> bag1.addItem(bag1));
        bag1.addItem(bag2);

        assertThrows(CannotAccessTheContainer.class, () -> bag2.addItem(bag1));
        bag3.addItem(bag1);
        assertThrows(CannotAccessTheContainer.class, () -> bag1.addItem(bag3));
        assertThrows(AddTheSameException.class, () -> bag3.addItem(bag3));
        bag4.addItem(bag3);
        assertThrows(ItemAlreadyPlacedException.class, () -> bag4.addItem(bag3));
        bag5.addItem(bag4);
        assertThrows(ItemAlreadyPlacedException.class, () -> bag5.addItem(bag3));

        bag5.getInfo();
    }

    @org.junit.jupiter.api.Test
    void calculateWeight() throws CannotAccessTheContainer, ItemIsEmptyException, AddTheSameException, ItemAlreadyPlacedException, ItemStoreException {
        Bag bag1 = new Bag("bag1", 0.5, "white");
        OneItem item1 = new OneItem("cat", 7, "black", "fluffy", "cute");

        bag1.addItem(item1);
        bag1.addItem(new OneItem("brick", 5, "red"));
        assertThrows(ItemStoreException.class, () -> bag1.addItem(new OneItem("brick", 5, "grey")));
        assertEquals(12.5, bag1.getWeight());
        bag1.removeItem();
        bag1.removeItem();
        assertEquals(0.5, bag1.getWeight());
    }

    @org.junit.jupiter.api.Test
    void takeItem() throws CannotAccessTheContainer, ItemIsEmptyException, AddTheSameException, ItemAlreadyPlacedException, ItemStoreException {
        Bag bag1 = new Bag("bag1", 1, "white");
        OneItem item1 = new OneItem("cat", 7, "black", "fluffy", "cute");
        bag1.addItem(item1);
        bag1.addItem(new OneItem("ball", 5, "red"));
        bag1.addItem(new OneItem("book", 1, "grey"));
        bag1.takeItem();
        bag1.takeItem();
        bag1.removeItem();
        bag1.takeItem();
    }

    @org.junit.jupiter.api.Test
    void TestExceptions() throws CannotAccessTheContainer, ItemAlreadyPlacedException, AddTheSameException, ItemStoreException, ItemIsEmptyException {
        OneItem uniqueItem = new OneItem("key", 0.058, "rare", "golden");
        var item2 = new OneItem("handle", 0.03, "oiled");
        OneItem item3 = new OneItem("desk", 10, "brown");
        OneItem item4 = new OneItem("fork", 0.01, "copper");
        Bag bag1 = new Bag("bag1", 1, 2, 10);
        Bag bag2 = new Bag("bag2", 0.5, 2, 5, "weak");

        bag1.addItem(uniqueItem);
        bag1.addItem(item2);

        assertThrows(ItemStoreException.class, () -> bag1.addItem(item4));
        assertThrows(ItemAlreadyPlacedException.class, () -> bag2.addItem(uniqueItem));
        assertThrows(ItemAlreadyPlacedException.class, () -> bag2.addItem(item2));
        assertThrows(ItemStoreException.class, () -> bag2.addItem(item3));

        assertThrows(ItemIsEmptyException.class, bag2::removeItem);

        assertThrows(ItemIsEmptyException.class, bag2::removeItem);

        assertEquals(1.088, bag1.getWeight(), 0.00001);
        assertEquals(0.5, bag2.getWeight(), 0.00001);
        bag1.getInfo();
        bag2.getInfo();
    }

    @org.junit.jupiter.api.Test
    void pushAndRemoveItem() throws CannotAccessTheContainer, ItemAlreadyPlacedException, AddTheSameException, ItemStoreException, ItemIsEmptyException {
        Bag bag1 = new Bag("bag1", 1, "white");
        Bag bag2 = new Bag("bag2", 1, "white");
        OneItem item1 = new OneItem("cat", 7, "black", "fluffy", "cute");

        bag1.addItem(item1);
        bag1.addItem(new OneItem("bar", 5, "silver"));
        bag1.addItem(new OneItem("vase", 1, "transparent"));

        bag1.removeItem();
        bag1.removeItem();
        bag1.getInfo();
        bag1.removeItem();
        bag1.getInfo();
        assertThrows(ItemIsEmptyException.class, bag1::removeItem);
        bag1.addItem(bag2);
        assertThrows(CannotAccessTheContainer.class, bag2::removeItem);
        bag1.getInfo();
        bag1.removeItem();
        System.out.println("---------");
        bag1.getInfo();
//        bag1.removeItem();
        bag2.addItem(item1);
        bag1.addItem(bag2);
        assertThrows(CannotAccessTheContainer.class, bag2::removeItem);
        bag1.removeItem();

        bag1.addItem(new OneItem("fork", 0.0054321, "copper"));
        bag1.getInfo();

        assertEquals(1.0054321, bag1.getWeight(), 0.00000001);
        assertEquals(8, bag2.getWeight(), 0.00001);
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        OneItem a = new OneItem("Chair", 4, "comfortable", "low");
        System.out.println(a.toString());
        assertEquals("Name: Chair; Weight: 4,00; Not added; properties: [low, comfortable].", a.toString());
    }

    @org.junit.jupiter.api.Test
    void findAndIteratorTest() throws ItemIsEmptyException {
        OneItem item1 = new OneItem("potato", 3, "fresh");
        OneItem item2 = new OneItem("milk", 1, "cheap");
        OneItem item3 = new OneItem("bread", 0.5, "warm");
        ArrayList<OneItem> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        Bag bag = new Bag("bag", 0.01, items, 7, 15, "black");
        // double search!! slow?
        if (bag.containItem("sad"))
            bag.findByName("sad").getInfo();
        if (bag.containItem("potato"))
            bag.findByName("potato").getInfo();

        // Iterator test!
        System.out.println();
        for (OneItem a :
                bag) {
            a.getInfo();
        }

        System.out.println();
        System.out.println(bag.toString());
    }
}
