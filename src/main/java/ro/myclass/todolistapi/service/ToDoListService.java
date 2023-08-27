package ro.myclass.todolistapi.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.myclass.todolistapi.dto.ToDoListDTO;
import ro.myclass.todolistapi.exceptions.ListEmptyException;
import ro.myclass.todolistapi.exceptions.ToDoListNotFoundException;
import ro.myclass.todolistapi.exceptions.ToDoListWasFoundException;
import ro.myclass.todolistapi.models.ToDoList;
import ro.myclass.todolistapi.repo.ToDoListRepository;

import java.util.List;
import java.util.Optional;

@Service

@Transactional
public class ToDoListService {

    private ToDoListRepository toDoListRepository;

    public ToDoListService(ToDoListRepository toDoListRepository) {
        this.toDoListRepository = toDoListRepository;
    }

    public List<ToDoList> getAllToDoList(){

        List<ToDoList> toDoLists = this.toDoListRepository.getAllToDoList();

        if (toDoLists.isEmpty()) {
            throw new ListEmptyException();
        } else {
            return toDoLists;
        }
    }

    public void addNewToDoList(ToDoListDTO toDoListDTO){

        Optional<ToDoList> toDoListOptional = this.toDoListRepository.getToDoListByName(toDoListDTO.getName());

        if (toDoListOptional.isEmpty() ){

            ToDoList toDoList = ToDoList.builder().name(toDoListDTO.getName())
                            .description(toDoListDTO.getDescription())
                            .deadline(toDoListDTO.getDeadline())
                            .priority(toDoListDTO.getPriority())
                            .status(toDoListDTO.getStatus())
                            .build();

            toDoListRepository.save(toDoList);

        }else{
            throw new ToDoListWasFoundException();
        }

    }

    public void deleteToDoList(long id){
        Optional<ToDoList> toDoList = this.toDoListRepository.getToDoListById(id);
        if (toDoList.isEmpty()){
            throw new ToDoListNotFoundException();
        }else{
            toDoListRepository.delete(toDoList.get());
        }
    }

    public void updateToDoList(ToDoListDTO toDoListDTO){
        Optional<ToDoList> toDoList = this.toDoListRepository.getToDoListByName(toDoListDTO.getName());
        if (toDoList.isEmpty()){
            throw new ToDoListNotFoundException();
        }

            if(toDoListDTO.getDescription() != null) {
                toDoList.get().setDescription(toDoListDTO.getDescription());
            }if(toDoListDTO.getStatus() != null) {
                toDoList.get().setStatus(toDoListDTO.getStatus());
            }if(toDoListDTO.getPriority() != null) {
                toDoList.get().setPriority(toDoListDTO.getPriority());
            }if(toDoListDTO.getDeadline() != null) {
                toDoList.get().setDeadline(toDoListDTO.getDeadline());
            }

            toDoListRepository.saveAndFlush(toDoList.get());

    }

    public ToDoList getToDoListById(long id){
        Optional<ToDoList> toDoList = this.toDoListRepository.getToDoListById(id);
        if (toDoList.isEmpty()){
            throw new ToDoListNotFoundException();
        }else{
            return toDoList.get();
        }
    }

    public List<ToDoList> getToDoListByStatus(String status){
        List<ToDoList> toDoLists = this.toDoListRepository.getToDoListByStatus(status);
        if (toDoLists.isEmpty()){
            throw new ListEmptyException();
        }else{
            return toDoLists;
        }
    }

    public List<ToDoList> getToDoListByPriority(String priority){
        List<ToDoList> toDoLists = this.toDoListRepository.getToDoListByPriority(priority);
        if (toDoLists.isEmpty()){
            throw new ListEmptyException();
        }else{
            return toDoLists;
        }
    }

    public List<ToDoList> getToDoListByStatusAndPriority(String status, String priority){
        List<ToDoList> toDoLists = this.toDoListRepository.getToDoListByStatusAndPriority(status,priority);
        if (toDoLists.isEmpty()){
            throw new ListEmptyException();
        }else{
            return toDoLists;
        }
    }

    public ToDoList getToDoListByDescription(String description){
        Optional<ToDoList> toDoLists = this.toDoListRepository.getToDoListByDescription(description);
        if (toDoLists.isEmpty()){
            throw new ToDoListNotFoundException();
        }else{
            return toDoLists.get();
        }
    }

    public ToDoList getToDoListByName(String name){
        Optional<ToDoList> toDoLists = this.toDoListRepository.getToDoListByName(name);
        if (toDoLists.isEmpty()){
            throw new ToDoListNotFoundException();
        }else{
            return toDoLists.get();
        }
    }


}
