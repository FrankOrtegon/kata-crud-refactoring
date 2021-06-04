package co.com.sofka.crud.controller;

import co.com.sofka.crud.dto.ToDoModelDTO;
import co.com.sofka.crud.dto.ToDoListModelDTO;
import co.com.sofka.crud.services.ToDoListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"${settings.cors_origin}"})
public class ToDoListController {

    private ToDoListService toDoListService;

    @Autowired
    public ToDoListController(ToDoListService toDoListService) {
        this.toDoListService = toDoListService;
    }

    @GetMapping(value = "api/list")
    public Iterable<ToDoListModelDTO> getAllListToDos(){
        return toDoListService.getAllListToDos();
    }

    @GetMapping(value = "api/{listId}/todos")
    public Iterable<ToDoModelDTO> getToDosByListId(@PathVariable("listId") Long listId){
        return toDoListService.getToDosByListId(listId);
    }

    @PostMapping(value = "api/todolist")
    public ToDoListModelDTO newListToDo(@RequestBody ToDoListModelDTO todo){
        return toDoListService.newListToDo(todo);
    }

    @DeleteMapping(value = "api/{id}/todolist")
    public void deleteListById(@PathVariable("id") Long id){
        toDoListService.deleteListById(id);
    }

    @PutMapping(value = "api/{listId}/todo")
    public ToDoModelDTO updateAToDoByListId(@PathVariable("listId") Long listId, @RequestBody ToDoModelDTO todo){
        if(todo.getId() != null){
            return toDoListService.updateAToDoByListId(listId, todo);
        }
        throw new NotFoundIdException("No existe el id para actualizar");
    }

    @PostMapping(value = "api/{listId}/todo")
    public ToDoModelDTO addNewToDoByListId(@PathVariable("listId") Long listId, @RequestBody ToDoModelDTO todo){
        return toDoListService.addNewToDoByListId(listId, todo);
    }

    @DeleteMapping(value = "api/{id}/todo")
    public void deleteAToDoById(@PathVariable("id")Long id){
        toDoListService.deleteAToDoById(id);
    }

}