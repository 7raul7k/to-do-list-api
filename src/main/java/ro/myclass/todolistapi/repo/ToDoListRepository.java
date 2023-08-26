package ro.myclass.todolistapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.myclass.todolistapi.models.ToDoList;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList,Long> {


    @Query("select t from ToDoList t where t.name = ?1")
    Optional<ToDoList> getToDoListByName(String name);

    @Query("select t from ToDoList t where t.id = ?1")
    Optional<ToDoList> getToDoListById(long id);

    @Query("select t from ToDoList t where t.status = ?1")
    List<ToDoList> getToDoListByStatus(String status);

    @Query("select t from ToDoList t where t.priority = ?1")
    List<ToDoList> getToDoListByPriority(String priority);

    @Query("select t from ToDoList t where t.status = ?1 and t.priority = ?2")
    List<ToDoList> getToDoListByStatusAndPriority(String status, String priority);

    @Query("select t from ToDoList t where t.description = ?1")
    Optional<ToDoList> getToDoListByDescription(String description);



    @Query("select t from ToDoList t")
    List<ToDoList> getAllToDoList();




}
