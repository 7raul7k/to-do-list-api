package ro.myclass.todolistapi.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.myclass.todolistapi.dto.ToDoListDTO;
import ro.myclass.todolistapi.models.ToDoList;
import ro.myclass.todolistapi.service.ToDoListService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/todo-list")
@Slf4j
public class ToDoListResource {



    private ToDoListService toDoListService;

    public ToDoListResource(ToDoListService toDoListService) {
        this.toDoListService = toDoListService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ToDoList>> getAllToDoList(){

        List<ToDoList> toDoLists = this.toDoListService.getAllToDoList();

        log.info("REST request to get all ToDoLists: {}", toDoLists);

        return new ResponseEntity<>(toDoLists, HttpStatus.OK);

    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewToDoList(ToDoListDTO toDoList){

        this.toDoListService.addNewToDoList(toDoList);

        log.info("REST request to add new ToDoList: {}", toDoList);

        return new ResponseEntity<>("ToDoList was added", HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteToDoList(@PathVariable("id") long id){

        this.toDoListService.deleteToDoList(id);

        log.info("REST request to delete ToDoList with id: {}", id);

        return new ResponseEntity<>("ToDoList was deleted", HttpStatus.OK);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateToDoList(@RequestBody ToDoListDTO toDoListDTO){

        this.toDoListService.updateToDoList(toDoListDTO);

        log.info("REST request to update ToDoList with id: {}", toDoListDTO.getId());

        return new ResponseEntity<>("ToDoList was updated", HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ToDoList> getToDoListById(@PathVariable("id") long id){

        ToDoList toDoList = this.toDoListService.getToDoListById(id);

        log.info("REST request to get ToDoList with id: {}", id);

        return new ResponseEntity<>(toDoList, HttpStatus.OK);

    }

    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<ToDoList> getToDoListByName(@PathVariable("name") String name){

        ToDoList toDoList = this.toDoListService.getToDoListByName(name);

        log.info("REST request to get ToDoList with name: {}", name);

        return new ResponseEntity<>(toDoList, HttpStatus.OK);

    }

    @GetMapping("/get-by-status/{status}")
    public ResponseEntity<List<ToDoList>> getToDoListByStatus(@PathVariable("status") String status){

        List<ToDoList> toDoLists = this.toDoListService.getToDoListByStatus(status);

        log.info("REST request to get ToDoList with status: {}", status);

        return new ResponseEntity<>(toDoLists, HttpStatus.OK);

    }

    @GetMapping("/get-by-priority/{priority}")
    public ResponseEntity<List<ToDoList>> getToDoListByPriority(@PathVariable("priority") String priority){

        List<ToDoList> toDoLists = this.toDoListService.getToDoListByPriority(priority);

        log.info("REST request to get ToDoList with priority: {}", priority);

        return new ResponseEntity<>(toDoLists, HttpStatus.OK);

    }

    @GetMapping("/get-by-status-and-priority/")
    public ResponseEntity<List<ToDoList>> getToDoListByStatusAndPriority(@RequestParam String status, @RequestParam String priority){

        List<ToDoList> toDoLists = this.toDoListService.getToDoListByStatusAndPriority(status, priority);

        log.info("REST request to get ToDoList with status: {} and priority: {}", status, priority);

        return new ResponseEntity<>(toDoLists, HttpStatus.OK);

    }

    @GetMapping("/get-by-description/{description}")
    public ResponseEntity<ToDoList> getToDoListByDescription(@PathVariable("description") String description){

        ToDoList toDoList = this.toDoListService.getToDoListByDescription(description);

        log.info("REST request to get ToDoList with description: {}", description);

        return new ResponseEntity<>(toDoList, HttpStatus.OK);

    }


}
