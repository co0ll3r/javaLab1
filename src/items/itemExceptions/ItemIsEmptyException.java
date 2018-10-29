package items.itemExceptions;

public class ItemIsEmptyException extends Exception{
    public ItemIsEmptyException(){
        super("You can't delete items in an empty container!");
    }
}
