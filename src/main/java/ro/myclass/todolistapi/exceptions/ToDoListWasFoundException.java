package ro.myclass.todolistapi.exceptions;

public class ToDoListWasFoundException extends RuntimeException{

    public ToDoListWasFoundException() {
        super("ToDoList was found! ");
    }
}
