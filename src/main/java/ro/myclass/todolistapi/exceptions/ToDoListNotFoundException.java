package ro.myclass.todolistapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ToDoListNotFoundException extends RuntimeException{

    public ToDoListNotFoundException() {
        super("ToDoList not found! ");
    }
}
