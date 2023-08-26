package ro.myclass.todolistapi.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Table(name = "todo_list")
@Entity(name = "ToDoList")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ToDoList {
    @Id
    @SequenceGenerator(name = "todo_list_sequence",
            sequenceName = "todo_list_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "todo_list_sequence"
    )
    @Column(
            name = "id"
    )
    private long id;
    @Column(name = "todo_list_name",
            nullable = false,
            columnDefinition = "TEXT")
    private String name;
    @Column(name = "todo_list_description",
            nullable = false,
            columnDefinition = "TEXT")
    private String description;
    @Column(name = "todo_list_status",
            nullable = false,
            columnDefinition = "TEXT")
    private String status;
    @Column(name = "todo_list_priority",
            nullable = false,
            columnDefinition = "TEXT")
    private String priority;
    @Column(name = "todo_list_deadline",
            nullable = false,
            columnDefinition = "TEXT")
    private String deadline;


    @Override
    public String toString(){
        return id+", "+name+", "+description+", "+status+", "+priority+", "+deadline;
    }

    @Override
    public boolean equals(Object obj){
        ToDoList toDoList = (ToDoList) obj;

        if(this.name.equals(toDoList.getName()) && this.description.equals(toDoList.getDescription()) && this.status.equals(toDoList.getStatus()) && this.priority.equals(toDoList.getPriority()) && this.deadline.equals(toDoList.getDeadline())){
            return true;
        }else{
            return false;
        }
    }
}
