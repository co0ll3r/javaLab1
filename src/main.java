import items.*;
import items.itemExceptions.AddTheSameException;
import items.itemExceptions.CannotAccessTheContainer;
import items.itemExceptions.ItemAlreadyPlacedException;
import items.itemExceptions.ItemStoreException;

public class main {
    public static void mainFunc(String[] args) {
        OneItem abc = new OneItem("toy", 2, "red", "wide", "smart");
        Bag bag1 = new Bag("bag", 0.5, "grey", "doted");
        try {
            bag1.addItem(abc);
            bag1.addItem(new OneItem("cat", 5, "fluffy"));
            bag1.addItem(new OneItem("table", 20));
            bag1.addItem(new OneItem("chair", 8));
            bag1.getInfo();
//            bag1.takeItem(0).getInfo();


/*        for (OneItem a :
               bag1) {
           a.getInfo();
        }*/

            Bag bag2 = new Bag("bag", 1);
            bag1.addItem(abc);
            bag2.getInfo();

            Shelf shelf1 = new Shelf("shelf", 1, 5, 35, "brown", "wooden");

            shelf1.addItem(abc);
            shelf1.addItem(new OneItem("brick", 5, "flat", "fusion"));
            shelf1.addItem(new OneItem("brick", 8, "grey"));
            shelf1.addItem(new OneItem("gold bar", 25, "expensive"));
            shelf1.addItem(bag2);
            shelf1.getInfo();
        } catch (ItemAlreadyPlacedException | ItemStoreException a) {
            System.err.println(a.getMessage());
        } catch (AddTheSameException | CannotAccessTheContainer e) {
            e.printStackTrace();
        }

/*        while (bag1.iterator().hasNext()){
            bag1.iterator().next().getInfo();
        }*/
    }
}
