package ro.myclass.todolistapi.repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ro.myclass.todolistapi.ToDoListApiApplication;
import ro.myclass.todolistapi.models.ToDoList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ToDoListApiApplication.class)
@Transactional
class ToDoRepositoryTest {

    @Autowired
    ToDoRepository toDoListRepository;

    @BeforeEach
    public void clean() {
        toDoListRepository.deleteAll();
    }

    @Test
    public void getAllToDoLists(){
        ToDoList toDoList = ToDoList.builder().name("Andrei").description("Popescu").status("Iasi").priority("test").deadline("test").build();
        ToDoList toDoList1 = ToDoList.builder().name("Adrian").description("Iosif").status("Suceava").priority("test").deadline("test").build();
        ToDoList toDoList2 = ToDoList.builder().name("Cristian").description("Florin").status("Bucuresti").priority("test").deadline("test").build();
        ToDoList toDoList3 = ToDoList.builder().name("George").description("Razvan").status("Constanta").priority("test").deadline("test").build();

        toDoListRepository.save(toDoList);
        toDoListRepository.save(toDoList1);
        toDoListRepository.save(toDoList2);
        toDoListRepository.save(toDoList3);

        List<ToDoList> toDoLists = new ArrayList<>();
        toDoLists.add(toDoList);
        toDoLists.add(toDoList1);
        toDoLists.add(toDoList2);
        toDoLists.add(toDoList3);

        assertEquals(toDoLists,toDoListRepository.getAllToDoList());
    }

    @Test
    public void getToDoListByName(){
        ToDoList toDoList = ToDoList.builder().name("Andrei").description("Popescu").status("Iasi").priority("test").deadline("test").build();
        toDoListRepository.save(toDoList);
        assertEquals(toDoList,toDoListRepository.getToDoListByName("Andrei").get());
    }

    @Test
    public void getToDoListById(){
        ToDoList toDoList = ToDoList.builder().name("Andrei").description("Popescu").status("Iasi").priority("test").deadline("test").build();
        toDoListRepository.save(toDoList);
        assertEquals(toDoList,toDoListRepository.getToDoListById(1).get());
    }

    @Test
    public void getToDoListByStatus(){
        ToDoList toDoList = ToDoList.builder().name("Andrei").description("Popescu").status("1").priority("test").deadline("test").build();
        ToDoList toDoList1 = ToDoList.builder().name("Adrian").description("Iosif").status("1").priority("test").deadline("test").build();
        ToDoList toDoList2 = ToDoList.builder().name("Cristian").description("Florin").status("1").priority("test").deadline("test").build();
        ToDoList toDoList3 = ToDoList.builder().name("George").description("Razvan").status("1").priority("test").deadline("test").build();

        toDoListRepository.save(toDoList);
        toDoListRepository.save(toDoList1);
        toDoListRepository.save(toDoList2);
        toDoListRepository.save(toDoList3);

        List<ToDoList> toDoLists = new ArrayList<>();
        toDoLists.add(toDoList);
        toDoLists.add(toDoList1);
        toDoLists.add(toDoList2);
        toDoLists.add(toDoList3);

        assertEquals(toDoLists,toDoListRepository.getToDoListByStatus("1"));
    }

    @Test
    public void getToDoListByPriority(){
        ToDoList toDoList = ToDoList.builder().name("Andrei").description("Popescu").status("Iasi").priority("1").deadline("test").build();
        ToDoList toDoList1 = ToDoList.builder().name("Adrian").description("Iosif").status("Suceava").priority("1").deadline("test").build();
        ToDoList toDoList2 = ToDoList.builder().name("Cristian").description("Florin").status("Bucuresti").priority("1").deadline("test").build();
        ToDoList toDoList3 = ToDoList.builder().name("George").description("Razvan").status("Constanta").priority("1").deadline("test").build();

        toDoListRepository.save(toDoList);
        toDoListRepository.save(toDoList1);
        toDoListRepository.save(toDoList2);
        toDoListRepository.save(toDoList3);

        List<ToDoList> toDoLists = new ArrayList<>();
        toDoLists.add(toDoList);
        toDoLists.add(toDoList1);
        toDoLists.add(toDoList2);
        toDoLists.add(toDoList3);

        assertEquals(toDoLists,toDoListRepository.getToDoListByPriority("1"));
    }

    @Test
    public void getToDoListByStatusAndPriority(){

        ToDoList toDoList = ToDoList.builder().name("Andrei").description("Popescu").status("1").priority("1").deadline("test").build();
        ToDoList toDoList1 = ToDoList.builder().name("Adrian").description("Iosif").status("1").priority("1").deadline("test").build();
        ToDoList toDoList2 = ToDoList.builder().name("Cristian").description("Florin").status("1").priority("1").deadline("test").build();
        ToDoList toDoList3 = ToDoList.builder().name("George").description("Razvan").status("1").priority("1").deadline("test").build();

        toDoListRepository.save(toDoList);
        toDoListRepository.save(toDoList1);
        toDoListRepository.save(toDoList2);
        toDoListRepository.save(toDoList3);

        List<ToDoList> toDoLists = new ArrayList<>();
        toDoLists.add(toDoList);
        toDoLists.add(toDoList1);
        toDoLists.add(toDoList2);
        toDoLists.add(toDoList3);

        assertEquals(toDoLists,toDoListRepository.getToDoListByStatusAndPriority("1","1"));
    }

    @Test
    public void getToDoListByDescription(){
        ToDoList toDoList = ToDoList.builder().name("Andrei").description("Popescu").status("Iasi").priority("test").deadline("test").build();
        toDoListRepository.save(toDoList);
        assertEquals(toDoList,toDoListRepository.getToDoListByDescription("Popescu").get());
    }



}

