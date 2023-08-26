package ro.myclass.todolistapi.exceptions;

public class ToDoListNotFoundException extends RuntimeException{

    public ToDoListNotFoundException() {
        super("ToDoList not found! ");
    }
}
