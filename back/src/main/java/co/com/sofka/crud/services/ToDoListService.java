package co.com.sofka.crud.services;

import co.com.sofka.crud.repository.dao.ToDoListRepository;
import co.com.sofka.crud.repository.dao.ToDoRepository;
import co.com.sofka.crud.dto.ToDoDTO;
import co.com.sofka.crud.dto.ToDoListDTO;
import co.com.sofka.crud.dto.ToDoListMapper;
import co.com.sofka.crud.dto.ToDoMapper;
import co.com.sofka.crud.entitys.ToDo;
import co.com.sofka.crud.entitys.ToDoList;
import co.com.sofka.crud.exception.exceptions;
import co.com.sofka.crud.repository.IToDoListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class ToDoListService implements IToDoListService {

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
            throw new exceptions("El nombre de la tarea no puede estar vacio");
        }
        ToDo todoEn = todoMapper.toTodo(todo);

        listToDo.getToDos().add(todoEn);
        var listUpdated = todoListRepository.save(listToDo);

        ToDo lastToDo = getLastToDo(listUpdated);
        todo.setId(lastToDo.getId());
        todo.setListId(id);
        return todo;
    }

    @Override
    public ToDoListDTO saveList(ToDoListDTO todoListDto) {
        ToDoList todoList = todoListRepository.save(todoListMapper.toTodo(todoListDto));
        return todoListMapper.todoToTodoDTO(todoList);
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
        return lastToDo;
    }

}
