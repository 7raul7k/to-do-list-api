package ro.myclass.todolistapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.myclass.todolistapi.dto.ToDoListDTO;
import ro.myclass.todolistapi.exceptions.ListEmptyException;
import ro.myclass.todolistapi.exceptions.ToDoListNotFoundException;
import ro.myclass.todolistapi.exceptions.ToDoListWasFoundException;
import ro.myclass.todolistapi.models.ToDoList;
import ro.myclass.todolistapi.repo.ToDoListRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ToDoListServiceTest {

    @Mock
    private ToDoListRepository toDoListRepository;

    @InjectMocks
    private ToDoListService toDoListService;

    @BeforeEach
    void clean(){
        toDoListRepository.deleteAll();
    }

    @Captor
    ArgumentCaptor<ToDoList> argumentCaptor;
    @Test
    void getAllToDoList() {
        //create 4 todolist
        //save them in the repository


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

        doReturn(toDoLists).when(toDoListRepository).getAllToDoList();

        assertEquals(toDoLists,toDoListService.getAllToDoList());



    }

    @Test
    void getAllToDoListException() {
        List<ToDoList> toDoLists = new ArrayList<>();

        doReturn(toDoLists).when(toDoListRepository).getAllToDoList();

        assertThrows(ListEmptyException.class, () -> toDoListService.getAllToDoList());

    }

    @Test
    public void getToDoListByName() {

        ToDoList toDoList = ToDoList.builder().name("Andrei").description("Popescu").status("Iasi").priority("test").deadline("test").build();

        doReturn(Optional.of(toDoList)).when(toDoListRepository).getToDoListByName("Andrei");
        assertEquals(toDoList,toDoListService.getToDoListByName("Andrei"));
    }

    @Test
    public void getToDoListNameException(){
        ToDoList toDoList = ToDoList.builder().name("Andrei").description("Popescu").status("Iasi").priority("test").deadline("test").build();

        doReturn(Optional.empty()).when(toDoListRepository).getToDoListByName("Andrei");
        assertThrows(NullPointerException.class, () -> toDoListService.getToDoListByName("Andrei"));

    }

    @Test
    public void getToDoListById() {
        ToDoList toDoList = ToDoList.builder().name("Andrei").description("Popescu").status("Iasi").priority("test").deadline("test").build();

        doReturn(Optional.of(toDoList)).when(toDoListRepository).getToDoListById(1);
        assertEquals(toDoList,toDoListService.getToDoListById(1));
    }

    @Test
    public void getToDoListByIdException(){
        doReturn(Optional.empty()).when(toDoListRepository).getToDoListById(1);

        assertThrows(ToDoListNotFoundException.class, () -> toDoListService.getToDoListById(1));
    }

    @Test
    public void getToDoListByStatus(){
        ToDoList toDoList = ToDoList.builder().name("Andrei").description("Popescu").status("1").priority("test").deadline("test").build();
        ToDoList toDoList1 = ToDoList.builder().name("Adrian").description("Iosif").status("1").priority("test").deadline("test").build();
        ToDoList toDoList2 = ToDoList.builder().name("Cristian").description("Florin").status("1").priority("test").deadline("test").build();
        ToDoList toDoList3 = ToDoList.builder().name("George").description("Razvan").status("1").priority("test").deadline("test").build();

        List<ToDoList> toDoLists = new ArrayList<>();
        toDoLists.add(toDoList);
        toDoLists.add(toDoList1);
        toDoLists.add(toDoList2);
        toDoLists.add(toDoList3);

        doReturn(toDoLists).when(toDoListRepository).getToDoListByStatus("1");

        assertEquals(toDoLists,toDoListService.getToDoListByStatus("1"));
    }

    @Test
    public void getToDoListByStatusException(){
        List<ToDoList> toDoLists = new ArrayList<>();

        doReturn(toDoLists).when(toDoListRepository).getToDoListByStatus("1");

        assertThrows(ListEmptyException.class, () -> toDoListService.getToDoListByStatus("1"));
    }

    @Test
    public void getToDoListByPriority(){
        ToDoList toDoList = ToDoList.builder().name("Andrei").description("Popescu").status("Iasi").priority("1").deadline("test").build();
        ToDoList toDoList1 = ToDoList.builder().name("Adrian").description("Iosif").status("Suceava").priority("1").deadline("test").build();
        ToDoList toDoList2 = ToDoList.builder().name("Cristian").description("Florin").status("Bucuresti").priority("1").deadline("test").build();
        ToDoList toDoList3 = ToDoList.builder().name("George").description("Razvan").status("Constanta").priority("1").deadline("test").build();

        List<ToDoList> toDoLists = new ArrayList<>();
        toDoLists.add(toDoList);
        toDoLists.add(toDoList1);
        toDoLists.add(toDoList2);
        toDoLists.add(toDoList3);

        doReturn(toDoLists).when(toDoListRepository).getToDoListByPriority("1");

        assertEquals(toDoLists,toDoListService.getToDoListByPriority("1"));
    }

    @Test
    public void getToDoListByPriorityException(){
        List<ToDoList> toDoLists = new ArrayList<>();

        doReturn(toDoLists).when(toDoListRepository).getToDoListByPriority("1");

        assertThrows(ListEmptyException.class, () -> toDoListService.getToDoListByPriority("1"));
    }

    @Test
        public void getToDoListByStatusAndPriority(){
        ToDoList toDoList = ToDoList.builder().name("Andrei").description("Popescu").status("1").priority("1").deadline("test").build();
        ToDoList toDoList1 = ToDoList.builder().name("Adrian").description("Iosif").status("1").priority("1").deadline("test").build();
        ToDoList toDoList2 = ToDoList.builder().name("Cristian").description("Florin").status("1").priority("1").deadline("test").build();
        ToDoList toDoList3 = ToDoList.builder().name("George").description("Razvan").status("1").priority("1").deadline("test").build();

        List<ToDoList> toDoLists = new ArrayList<>();
        toDoLists.add(toDoList);
        toDoLists.add(toDoList1);
        toDoLists.add(toDoList2);
        toDoLists.add(toDoList3);

        doReturn(toDoLists).when(toDoListRepository).getToDoListByStatusAndPriority("1","1");

        assertEquals(toDoLists,toDoListService.getToDoListByStatusAndPriority("1","1"));
    }

    @Test
    public void getToDoListByStatusAndPriorityException(){
        List<ToDoList> toDoLists = new ArrayList<>();

        doReturn(toDoLists).when(toDoListRepository).getToDoListByStatusAndPriority("1","1");

        assertThrows(ListEmptyException.class, () -> toDoListService.getToDoListByStatusAndPriority("1","1"));
    }

    @Test
    public void getToDoListByDescription(){
        ToDoList toDoList = ToDoList.builder().name("Andrei").description("Popescu").status("1").priority("1").deadline("test").build();

        doReturn(Optional.of(toDoList)).when(toDoListRepository).getToDoListByDescription("Popescu");

        assertEquals(toDoList,toDoListService.getToDoListByDescription("Popescu"));
    }

    @Test
    public void getToDoListByDescriptionException(){
        doReturn(Optional.empty()).when(toDoListRepository).getToDoListByDescription("Popescu");

        assertThrows(ToDoListNotFoundException.class, () -> toDoListService.getToDoListByDescription("Popescu"));
    }

    @Test
    public void updateToDoList(){
        ToDoListDTO toDoList = ToDoListDTO.builder().name("Andrei").description("Popescu").status("1").priority("1").deadline("test").build();

        ToDoList toDoList1 = ToDoList.builder().id(1L).name("Andrei").description("Popescu").status("1").priority("1").deadline("test").build();

        doReturn(Optional.of(toDoList1)).when(toDoListRepository).getToDoListByName("Andrei");

        toDoListService.updateToDoList(toDoList);

       verify(toDoListRepository,times(1)).saveAndFlush(argumentCaptor.capture());

       assertEquals(toDoList1,argumentCaptor.getValue());

    }

    @Test
    public void updateToDoListException(){
        ToDoListDTO toDoList = ToDoListDTO.builder().name("Andrei").description("Popescu").status("1").priority("1").deadline("test").build();

        doReturn(Optional.empty()).when(toDoListRepository).getToDoListByName("Andrei");

        assertThrows(ToDoListNotFoundException.class, () -> toDoListService.updateToDoList(toDoList));
    }

    @Test
    public void addToDoList(){
        ToDoListDTO toDoList = ToDoListDTO.builder().name("Andrei").description("Popescu").status("1").priority("1").deadline("test").build();

        toDoListService.addNewToDoList(toDoList);

        verify(toDoListRepository,times(1)).save(argumentCaptor.capture());

        assertEquals(toDoList.getName(),argumentCaptor.getValue().getName());
    }

    @Test
    public void addToDoListException(){
        ToDoListDTO toDoList = ToDoListDTO.builder().name("Andrei").description("Popescu").status("1").priority("1").deadline("test").build();

        doReturn(Optional.of(ToDoList.builder().name("Andrei").description("Popescu").status("1").priority("1").deadline("test").build())).when(toDoListRepository).getToDoListByName("Andrei");

        assertThrows(ToDoListWasFoundException.class, () -> toDoListService.addNewToDoList(toDoList));
    }

    @Test
    public void deleteToDoList(){
        ToDoList toDoList = ToDoList.builder().id(1L).name("Andrei").description("Popescu").status("1").priority("1").deadline("test").build();

        doReturn(Optional.of(toDoList)).when(toDoListRepository).getToDoListById(1L);

        toDoListService.deleteToDoList(1L);

        verify(toDoListRepository,times(1)).delete(argumentCaptor.capture());

        assertEquals(toDoList,argumentCaptor.getValue());
    }

    @Test
    public void deleteToDoListException(){
        ToDoList toDoList = ToDoList.builder().id(1L).name("Andrei").description("Popescu").status("1").priority("1").deadline("test").build();

        doReturn(Optional.empty()).when(toDoListRepository).getToDoListById(1L);

        assertThrows(ToDoListNotFoundException.class, () -> toDoListService.deleteToDoList(1L));
    }
}