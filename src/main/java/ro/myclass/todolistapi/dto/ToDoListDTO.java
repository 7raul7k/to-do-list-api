package ro.myclass.todolistapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ToDoListDTO {

    private long id;
    private String name;
    private String description;
    private String status;
    private String priority;
    private String deadline;


}
