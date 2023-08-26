package ro.myclass.todolistapi.exceptions;

public class ListEmptyException extends RuntimeException{

    public ListEmptyException() {
        super("empty list! ");
    }
}
