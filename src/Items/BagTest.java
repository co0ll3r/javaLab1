package Items;

import static org.junit.jupiter.api.Assertions.*;

class BagTest {

    @org.junit.jupiter.api.Test
    void calculateWeight() {
        Bag bag1 = new Bag("bag1", "white");
        OneItem item1 = new OneItem("cat", 7, "black", "fluffy", "cute");
        bag1.pushItem(item1);
        bag1.pushItem(new OneItem("brick", 5, "red"));
        bag1.pushItem(new OneItem("brick", 5, "grey"));
        assertEquals(12, bag1.getWeight());
    }

    @org.junit.jupiter.api.Test
    void takeItem() {
        Bag bag1 = new Bag("bag1", "white");
        OneItem item1 = new OneItem("cat", 7, "black", "fluffy", "cute");
        bag1.pushItem(item1);
        bag1.pushItem(new OneItem("brick", 5, "red"));
        bag1.pushItem(new OneItem("brick", 1, "grey"));
        bag1.takeItem();
        bag1.takeItem();
        try {
            bag1.removeItem();
        } catch (ItemIsEmptyException e) {
            e.getMessage();
        }
        bag1.takeItem();
    }

    @org.junit.jupiter.api.Test
    void takeRandomItem() {
    }

    @org.junit.jupiter.api.Test
    void removeItem() {
        Bag bag1 = new Bag("bag1", "white");
        OneItem item1 = new OneItem("cat", 7, "black", "fluffy", "cute");
        bag1.pushItem(item1);
        bag1.pushItem(new OneItem("brick", 5, "red"));
        bag1.pushItem(new OneItem("brick", 1, "grey"));
        try {
            bag1.removeItem();
            bag1.removeItem();
            bag1.getInfo();
            bag1.removeItem();
            bag1.getInfo();
            bag1.removeItem();
        } catch (ItemIsEmptyException e) {
            System.err.print(e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void pushItem() {
        /*        } catch (ItemAlreadyPlacedException e) {
            System.out.println("Error this item already added!");
            // System.out.println(Arrays.toString(e.getStackTrace()));
        } catch (ItemStoreException a) {
            //   System.out.println("Store exception!");
            System.out.println(a.getMessage());
        }*/
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        OneItem a = new OneItem("Chair", 4, "comfortable", "low");
        System.out.println(a.toString());
        assertEquals("Name: Chair; Weight: 4,00; Not added; properties: [low, comfortable].", a.toString());
    }
}
