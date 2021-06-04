package co.com.sofka.crud.services;

import co.com.sofka.crud.dao.ToDoListRepository;
import co.com.sofka.crud.dao.ToDoRepository;
import co.com.sofka.crud.dto.ToDoDTO;
import co.com.sofka.crud.dto.ToDoListDTO;
import co.com.sofka.crud.entitys.ToDo;
import co.com.sofka.crud.entitys.ToDoList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ToDoListService {

    @Autowired
    private ToDoListRepository todoListRepository;

    @Autowired
    private ToDoListMapper todoListMapper;

    @Autowired
    private ToDoRepository repository;

    @Autowired
    private ToDoMapper todoMapper;


    public ToDoListDTO getTodoList(Long id) {
        return todoListMapper.todoToTodoDTO(todoListRepository.findById(id).orElseThrow());
    }

    @Override
    public Iterable<ToDoListDTO> listTodoList() {

        return StreamSupport
                .stream(todoListRepository.findAll().spliterator(), false)
                .map(toDoList -> {
                    var listDto = toDoList.getToDos()
                            .stream()
                            .map(item -> new ToDoDTO(item.getId(), item.getName(), item.isCompleted(), toDoList.getId()))
                            .collect(Collectors.toSet());
                    return new ToDoListDTO(toDoList.getId(), toDoList.getName(), listDto);
                })
                .collect(Collectors.toSet());
    }

    public ToDoDTO addNewToDoByListId(Long id, ToDoDTO todo) {

        var listToDo = todoListRepository.findById(id).orElseThrow();
        if(todo.getName().isEmpty()){
            throw new exceptions("To-Do name cant be empty");
        }
        ToDo todoEn = todoMapper.toTodo(todo);
        //addition new to-do
        listToDo.getToDos().add(todoEn);
        var listUpdated = todoListRepository.save(listToDo);
        //last item
        ToDo lastToDo = getLastToDo(listUpdated);
        todo.setId(lastToDo.getId());
        todo.setListId(id);
        return todo;
    }

    @Override
    public ToDoListDTO saveList(ToDoListDTO todoList) {
        return todoListMapper.todoToTodoDTO(todoListRepository.save(todoListMapper.toTodo(todoList)));
    }

    @Override
    public void deleteTodoList(Long id) {
        todoListRepository.delete(todoListMapper.toTodo(getTodoList(id)));
    }

    public ToDoDTO get(Long id) {
        return todoMapper.todoToTodoDTO(repository.findById(id).orElseThrow());
    }

    @Override
    public Iterable<ToDoDTO> listTodos() {
        return todoMapper.toTodoDTOs(repository.findAll());
    }

    @Override
    public ToDoDTO save(ToDoDTO todo) {
        return todoMapper.todoToTodoDTO(repository.save(todoMapper.toTodo(todo)));
    }

    @Override
    public void deleteTodo(Long id) {
        repository.delete(todoMapper.toTodo(get(id)));
    }

    private ToDo getLastToDo(ToDoList listUpdated) {
        var lastToDo = listUpdated.getToDos()
                .stream()
                .max(Comparator.comparingInt(item -> item.getId().intValue()))
                .orElseThrow();
        System.out.println(lastToDo);
        return lastToDo;
    }

}
