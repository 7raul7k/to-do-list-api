package ro.myclass.todolistapi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.myclass.todolistapi.dto.ToDoListDTO;
import ro.myclass.todolistapi.exceptions.ListEmptyException;
import ro.myclass.todolistapi.exceptions.ToDoListWasFoundException;
import ro.myclass.todolistapi.models.ToDoList;
import ro.myclass.todolistapi.service.ToDoListService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class ToDoListResourceTest {

    @Mock
    private ToDoListService toDoListService;

    @InjectMocks
    private ToDoListResource toDoListResource;

    private ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc restMockMvc;

    @BeforeEach
    void initialConfig(){
        restMockMvc = MockMvcBuilders.standaloneSetup(toDoListResource).build();
    }

    @Test
    public void getAllToDoList() throws Exception{

        Faker faker = new Faker();

        List<ToDoList> toDoList = new ArrayList<>();

        for(int i = 0 ; i < 10 ; i ++){
            toDoList.add(ToDoList.builder().name(faker.name().firstName()).description(faker.name().lastName()).status(faker.address().city()).priority(faker.address().country()).deadline(faker.address().countryCode()).build());
        }

        doReturn(toDoList).when(toDoListService).getAllToDoList();

        restMockMvc.perform(get("/api/v1/todo-list/all")).andExpect(content().string(objectMapper.writeValueAsString(toDoList))).andExpect(status().isOk());
    }

    @Test
    public void getAllToDoListBadRequest() throws Exception{
        doThrow(ListEmptyException.class).when(toDoListService).getAllToDoList();

        restMockMvc.perform(get("/api/v1/todo-list/all")).andExpect(status().isBadRequest());
    }

    @Test
    public void addNewToDoList() throws Exception{
        Faker faker = new Faker();

        ToDoList toDoList = ToDoList.builder().name(faker.name().firstName()).description(faker.name().lastName()).status(faker.address().city()).priority(faker.address().country()).deadline(faker.address().countryCode()).build();

        restMockMvc.perform(post("/api/v1/todo-list/add").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(toDoList))).andExpect(status().isOk());
    }

    @Test
    public void addNewToDoListBadRequest() throws Exception{
        ToDoListDTO toDoList = ToDoListDTO.builder().name("Andrei").description("Popescu").status("Iasi").priority("test").deadline("test").build();
        doThrow(ToDoListWasFoundException.class).when(toDoListService).addNewToDoList(toDoList);

        restMockMvc.perform(post("/api/v1/todo-list/add").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(toDoList))).andExpect(status().isBadRequest());
    }

    @Test
    public void deleteToDoList() throws Exception{
        Faker faker = new Faker();

        ToDoList toDoList = ToDoList.builder().name(faker.name().firstName()).description(faker.name().lastName()).status(faker.address().city()).priority(faker.address().country()).deadline(faker.address().countryCode()).build();

        restMockMvc.perform(delete("/api/v1/todo-list/delete/1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(toDoList))).andExpect(status().isOk());
    }

    @Test
    public void deleteToDoListBadRequest() throws Exception{
        doThrow(ToDoListWasFoundException.class).when(toDoListService).deleteToDoList(1);

        restMockMvc.perform(delete("/api/v1/todo-list/delete/1")).andExpect(status().isBadRequest());
    }
    @Test
    public void updateToDoList() throws Exception{
        Faker faker = new Faker();

        ToDoList toDoList = ToDoList.builder().name(faker.name().firstName()).description(faker.name().lastName()).status(faker.address().city()).priority(faker.address().country()).deadline(faker.address().countryCode()).build();

        restMockMvc.perform(put("/api/v1/todo-list/update/1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(toDoList))).andExpect(status().isOk());
    }

    @Test
    public void updateToDoListBadRequest() throws Exception{
        ToDoListDTO toDoList = ToDoListDTO.builder().name("Andrei").description("Popescu").status("Iasi").priority("test").deadline("test").build();
        doThrow(ToDoListWasFoundException.class).when(toDoListService).updateToDoList(toDoList);

        restMockMvc.perform(put("/api/v1/todo-list/update/1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(toDoList))).andExpect(status().isBadRequest());
    }

    @Test
    public void getToDoListById() throws Exception{
        Faker faker = new Faker();

        ToDoList toDoList = ToDoList.builder().name(faker.name().firstName()).description(faker.name().lastName()).status(faker.address().city()).priority(faker.address().country()).deadline(faker.address().countryCode()).build();

        doReturn(toDoList).when(toDoListService).getToDoListById(1);

        restMockMvc.perform(get("/api/v1/todo-list/get/1")).andExpect(content().string(objectMapper.writeValueAsString(toDoList))).andExpect(status().isOk());
    }

    @Test
    public void getToDoListByIdBadRequest() throws Exception{
        doThrow(ToDoListWasFoundException.class).when(toDoListService).getToDoListById(1);

        restMockMvc.perform(get("/api/v1/todo-list/get/1")).andExpect(status().isBadRequest());
    }

    @Test
    public void getToDoListByName() throws Exception{
        Faker faker = new Faker();

        ToDoList toDoList = ToDoList.builder().name(faker.name().firstName()).description(faker.name().lastName()).status(faker.address().city()).priority(faker.address().country()).deadline(faker.address().countryCode()).build();

        doReturn(toDoList).when(toDoListService).getToDoListByName("Andrei");

        restMockMvc.perform(get("/api/v1/todo-list/get-by-name/Andrei")).andExpect(content().string(objectMapper.writeValueAsString(toDoList))).andExpect(status().isOk());
    }

    @Test
    public void getToDoListByNameBadRequest() throws Exception{
        doThrow(ToDoListWasFoundException.class).when(toDoListService).getToDoListByName("Andrei");

        restMockMvc.perform(get("/api/v1/todo-list/get-by-name/Andrei")).andExpect(status().isBadRequest());
    }

    @Test
    public void getToDoListByStatus() throws Exception{
        Faker faker = new Faker();

        List<ToDoList> toDoList = new ArrayList<>();

        for(int i = 0 ; i < 10 ; i ++){
            toDoList.add(ToDoList.builder().name(faker.name().firstName()).description(faker.name().lastName()).status("1").priority(faker.address().country()).deadline(faker.address().countryCode()).build());
        }

        doReturn(toDoList).when(toDoListService).getToDoListByStatus("1");

        restMockMvc.perform(get("/api/v1/todo-list/get-by-status/1")).andExpect(content().string(objectMapper.writeValueAsString(toDoList))).andExpect(status().isOk());
    }

    @Test
    public void getToDoListByStatusBadRequest() throws Exception{
        doThrow(ToDoListWasFoundException.class).when(toDoListService).getToDoListByStatus("1");

        restMockMvc.perform(get("/api/v1/todo-list/get-by-status/1")).andExpect(status().isBadRequest());
    }

    @Test
    public void getToDoListByPriority() throws Exception{
        Faker faker = new Faker();

        List<ToDoList> toDoList = new ArrayList<>();

        for(int i = 0 ; i < 10 ; i ++){
            toDoList.add(ToDoList.builder().name(faker.name().firstName()).description(faker.name().lastName()).status(faker.address().city()).priority("1").deadline(faker.address().countryCode()).build());
        }

        doReturn(toDoList).when(toDoListService).getToDoListByPriority("1");

        restMockMvc.perform(get("/api/v1/todo-list/get-by-priority/1")).andExpect(content().string(objectMapper.writeValueAsString(toDoList))).andExpect(status().isOk());
    }

    @Test
    public void getToDoListByPriorityBadRequest() throws Exception{
        doThrow(ToDoListWasFoundException.class).when(toDoListService).getToDoListByPriority("1");

        restMockMvc.perform(get("/api/v1/todo-list/get-by-priority/1")).andExpect(status().isBadRequest());
    }

    @Test
    public void getToDoListByStatusAndPriority() throws Exception{
        Faker faker = new Faker();

        List<ToDoList> toDoList = new ArrayList<>();

        for(int i = 0 ; i < 10 ; i ++){
            toDoList.add(ToDoList.builder().name(faker.name().firstName()).description(faker.name().lastName()).status("1").priority("1").deadline(faker.address().countryCode()).build());
        }

        doReturn(toDoList).when(toDoListService).getToDoListByStatusAndPriority("1","1");

        restMockMvc.perform(get("/api/v1/todo-list/get-by-status-and-priority/?status=1&priority=1")).andExpect(content().string(objectMapper.writeValueAsString(toDoList))).andExpect(status().isOk());
    }

    @Test
    public void getToDoListByStatusAndPriorityBadRequest() throws Exception{
        doThrow(ToDoListWasFoundException.class).when(toDoListService).getToDoListByStatusAndPriority("1","1");

        restMockMvc.perform(get("/api/v1/todo-list/get-by-status-and-priority/?status=1&priority=1")).andExpect(status().isBadRequest());
    }

    @Test
    public void getToDoListByDescription() throws Exception{
        Faker faker = new Faker();

        ToDoList toDoList = ToDoList.builder().name(faker.name().firstName()).description("Popescu").status(faker.address().city()).priority(faker.address().country()).deadline(faker.address().countryCode()).build();

        doReturn(toDoList).when(toDoListService).getToDoListByDescription("Popescu");

        restMockMvc.perform(get("/api/v1/todo-list/get-by-description/Popescu")).andExpect(content().string(objectMapper.writeValueAsString(toDoList))).andExpect(status().isOk());
    }

    @Test
    public void getToDoListByDescriptionBadRequest() throws Exception{
        doThrow(ToDoListWasFoundException.class).when(toDoListService).getToDoListByDescription("test");

        restMockMvc.perform(get("/api/v1/todo-list/get-by-description/test")).andExpect(status().isBadRequest());
    }




}