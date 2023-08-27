package ro.myclass.todolistapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ToDoListWasFoundException extends RuntimeException{

    public ToDoListWasFoundException() {
        super("ToDoList was found! ");
    }
}
