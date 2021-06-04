package co.com.sofka.crud.controller;

import co.com.sofka.crud.dto.*;
import co.com.sofka.crud.services.ToDoListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"${settings.cors_origin}"})
public class ToDoListController {

    @Autowired
    private ToDoListService service;


    @GetMapping(value = "api/todos")
    public Iterable<ToDoDTO> list() {
        return service.listTodos();
    }

    @PostMapping(value = "api/todolist")
    public ToDoListDTO saveTodoList(ToDoListDTO todoList) {
        return service.saveList(todoList);
    }


    @GetMapping(value = "api/todolist")
    public Iterable<ToDoListDTO> getTodoList() {
        return service.listTodoList();
    }

    @PostMapping(value = "api/{idList}/todo")
    public ToDoDTO save(@PathVariable("idList")Long idList, @RequestBody ToDoDTO todo) {
        return service.addNewToDoByListId(idList,todo);
    }

    @PutMapping(value = "api/todo")
    public ToDoDTO update(@RequestBody ToDoDTO todo) {
        System.out.println(todo.getName());
        if (todo.getId() != null) {
            return service.save(todo);
        }
        throw new RuntimeException("No existe el id para actualziar");
    }

    @DeleteMapping(value = "api/{id}/todo")
    public void delete(@PathVariable("id") Long id) {
        service.deleteTodo(id);
    }

    @GetMapping(value = "api/{id}/todo")
    public ToDoDTO get(@PathVariable("id") Long id) {
        return service.get(id);
    }

}