package tests;

import items.*;
import items.itemExceptions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoxTest {

    @Test
    void addTheSame() {
        Box box1 = new Box("box1", 1.5, 4, 20, "flat", "wooden");
        Box box2 = new Box("box2", 1, 4, 20, "flat", "wooden");
        Box box3 = new Box("box3", 0.5, 4, 20, "flat", "wooden");
        Box box4 = new Box("box4", 1.25, 4, 21, "flat", "wooden");
        var item1 = new OneItem("book", 0.451, "flat", "1984");
        var item2 = new OneItem("laptop", 2.5, "flat", "asus");
        var item3 = new OneItem("dvd-player", 4, "flat");
        var item4 = new OneItem("barbell", 2.5, "flat");
        var item5 = new OneItem("server", 7, "flat");
        try {
            box1.addItem(item1);
            box1.addItem(item2);
            box1.addItem(item3);
            box2.addItem(box1);
            box2.addItem(item4);
            box3.addItem(box2);
            box3.addItem(item5);
            box4.addItem(box3);
        } catch (ItemAlreadyPlacedException | CannotAccessTheContainer | ItemStoreException | AddTheSameException e) {
            e.printStackTrace();
        }
        box4.getInfo();
        System.out.println();
        box1.getInfo();
/*        assertThrows(AddTheSameException.class, () -> box2.addItem(box2));
        assertThrows(CannotAccessTheContainer.class, () -> box2.addItem(box1));
 */   }

    @Test
    void takeItem() {
        Box box1 = new Box("box", 1.5, 4, 20, "flat", "wooden");
        var item1 = new OneItem("book", 0.451, "flat", "1984");
        var item2 = new OneItem("laptop", 2.5, "flat", "asus");
        var item3 = new OneItem("dvd-player", 4, "flat");
        var item4 = new OneItem("barbell", 20, "flat");
        var item5 = new OneItem("server", 7, "flat");
        var item6 = new OneItem("computer", 3, "FLAT");
        try {
            box1.addItem(item1);
            box1.addItem(item2);
            box1.addItem(item3);
            assertThrows(ItemStoreException.class, () -> box1.addItem(item4));
            box1.addItem(item6);
            box1.getInfo();
            assertThrows(ItemStoreException.class, () -> box1.addItem(item5));
            assertThrows(ItemAlreadyPlacedException.class, () -> box1.addItem(item1));
            assertThrows(ItemStoreException.class, () -> box1.addItem
                    (new OneItem("spoon", 0.0015, "engraved", "small")));
            assertThrows(ItemStoreException.class, () -> box1.addItem
                    (new OneItem("spoon", 0.0015, "engraved", "small", "flag")));
        } catch (ItemAlreadyPlacedException | ItemStoreException | AddTheSameException | CannotAccessTheContainer e) {
            e.printStackTrace();
        }
        assertEquals(11.451, box1.getWeight());
        try {
            box1.removeItem();
            box1.removeItem();
            box1.removeItem();
            box1.removeItem();
        } catch (ItemIsEmptyException | CannotAccessTheContainer e) {
            e.printStackTrace();
        }
//        shelf1.getInfo();
        assertThrows(ItemIsEmptyException.class, box1::removeItem);
        assertEquals(1.5, box1.getWeight(), 0.000001);
    }

    @Test
    void closeBox() {
        Box box = new Box("crate", 1.1, 3, 15, "wooden");
        box.getInfo();
        var item1 = new OneItem("book", 0.451, "flat", "1984");
        var item2 = new OneItem("laptop", 2.5, "flat", "asus");
        var item3 = new OneItem("dvd-player", 4, "flat");
        var item4 = new OneItem("laptop", 2.5, "flat", "asus");
        var item5 = new OneItem("dvd-player", 4, "flat");
        try {
            box.addItem(item1);
            box.addItem(item2);
            box.addItem(item3);
        } catch (ItemAlreadyPlacedException | ItemStoreException | AddTheSameException | CannotAccessTheContainer e) {
            e.printStackTrace();
        }
        System.out.println();
        box.getInfo();
        box.closeContainer();
//        if (!box.checkIsBoxClosed())
        assertThrows(CannotAccessTheContainer.class, () -> box.takeItem().getInfo());
        assertThrows(CannotAccessTheContainer.class, () -> box.addItem(item4));
        assertThrows(CannotAccessTheContainer.class, () -> box.addItem(item5));
        assertThrows(CannotAccessTheContainer.class, box::removeItem);
        try {
            box.openContainer();
//            if (!box.checkIsBoxClosed())
            box.takeItem().getInfo();
        } catch (CannotAccessTheContainer e) {
            e.printStackTrace();
        }
        assertEquals(8.051, box.getWeight(), 0.00001);
    }

    @Test
    void putOnShelf() {
        Box box1 = new Box("crate", 1, 3, 15, "wooden");
        Box box2 = new Box("bag", 2, 3, 15, "wooden");
        Box box3 = new Box("box", 3, 3, 15, "wooden");
        var item1 = new OneItem("book", 1.451, "flat", "1984");
        try {
            box1.addItem(box2);
            box1.addItem(box3);
            box1.addItem(item1);
            OneItem box4 = box1.takeItem();
            box4.getInfo();
        } catch (ItemAlreadyPlacedException | AddTheSameException | ItemStoreException | CannotAccessTheContainer e) {
            e.printStackTrace();
        }

    }
}