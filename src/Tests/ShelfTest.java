package Tests;

import Items.*;
import Items.ItemExceptions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShelfTest {
    @Test
    void addBoxesOnBoxes() throws CannotAccessTheContainer, ItemAlreadyPlacedException, AddTheSameException, ItemStoreException {
        Shelf shelf1 = new Shelf("shelf", 1.5, 4, 20, "flat", "wooden");
        Box box1 = new Box("box", 1.5, 4, 20, "flat", "wooden");
        Box box2 = new Box("box2", 1.5, 4, 20, "flat", "wooden");
        Box box3 = new Box("box3", 1.5, 4, 20, "flat", "wooden");
        var item1 = new OneItem("book", 0.451, "flat", "1984");

        box1.pushItem(item1);
        shelf1.pushItem(box1);
        shelf1.pushItem(box2);
        shelf1.pushItem(box3);
        //  box3.takeItem();
        assertThrows(CannotAccessTheContainer.class, box3::takeItem);
        shelf1.getInfo();

    }

    @Test
    void calculateWeight() throws CannotAccessTheContainer, ItemAlreadyPlacedException, AddTheSameException, ItemStoreException, ItemIsEmptyException {
        Shelf shelf1 = new Shelf("shelf", 1.5, 4, 20, "flat", "wooden");
        var item1 = new OneItem("book", 0.451, "flat", "1984");
        var item2 = new OneItem("laptop", 2.5, "flat", "asus");
        var item3 = new OneItem("dvd-player", 4, "flat");
        var item4 = new OneItem("barbell", 20, "flat");
        var item5 = new OneItem("server", 7, "flat");
        var item6 = new OneItem("computer", 3, "FLAT");

        shelf1.pushItem(item1);
        shelf1.pushItem(item2);
        shelf1.pushItem(item3);
        assertThrows(ItemStoreException.class, () -> shelf1.pushItem(item4));
        shelf1.pushItem(item6);
        assertThrows(ItemStoreException.class, () -> shelf1.pushItem(item5));
        assertThrows(ItemAlreadyPlacedException.class, () -> shelf1.pushItem(item1));
        shelf1.pushItem(new OneItem("spoon", 0.0015, "engraved", "small"));
        shelf1.pushItem(new OneItem("spoon", 0.0015, "engraved", "small", "flag"));

        assertEquals(11.451, shelf1.getWeight());
        shelf1.removeItem();
        assertEquals(8.451, shelf1.getWeight());
        shelf1.removeItem();
        // doesn't work without delta!
        assertEquals(4.451, shelf1.getWeight(), 0.00001);
        shelf1.removeItem();
        assertEquals(1.951, shelf1.getWeight(), 0.00001);
        shelf1.removeItem();
        assertEquals(1.5, shelf1.getWeight(), 0.00001);

        shelf1.getInfo();
        assertThrows(ItemIsEmptyException.class, shelf1::removeItem);
    }

    @Test
    void addBox() throws CannotAccessTheContainer, ItemAlreadyPlacedException, AddTheSameException, ItemStoreException {
        Shelf shelf1 = new Shelf("shelf", 1.5, 4, 20, "flat", "wooden");
        var item1 = new OneItem("book", 0.451, "flat", "1984");
        var item2 = new OneItem("laptop", 2.5, "flat", "asus");
        var item3 = new OneItem("dvd-player", 4, "flat");
        var item4 = new OneItem("barbell", 20, "flat");
        var item5 = new OneItem("server", 7, "flat");
        var item6 = new OneItem("computer", 3, "FLAT");
        Box box1 = new Box("box1", 1, 3, 5, "small");

        box1.pushItem(item1);
        box1.pushItem(item2);

    }

    @Test
    void removeItem() {
    }

    @Test
    void pushItem() {
    }
}