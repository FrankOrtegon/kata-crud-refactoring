package co.com.sofka.crud.services;

import co.com.sofka.crud.dto.ToDoDTO;
import co.com.sofka.crud.dto.ToDoListDTO;
import co.com.sofka.crud.entitys.ToDoList;

public interface IToDoListService {

    Iterable<ToDoListDTO> listTodoList();

    ToDoListDTO saveList(ToDoListDTO todoList);


    Iterable<ToDoDTO> listTodos();

    ToDoDTO save(ToDoDTO todo);

    void deleteTodoList(Long id);

    void deleteTodo(Long id);

}