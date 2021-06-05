package co.com.sofka.crud.repository;

import co.com.sofka.crud.dto.ToDoDTO;
import co.com.sofka.crud.dto.ToDoListDTO;


public interface IToDoListService {

    Iterable<ToDoListDTO> listTodoList();
    ToDoListDTO saveList(ToDoListDTO todoList);

    Iterable<ToDoDTO> listTodos();

    ToDoDTO save(ToDoDTO todo);

    void deleteTodoList(Long id);

    void deleteTodo(Long id);

}