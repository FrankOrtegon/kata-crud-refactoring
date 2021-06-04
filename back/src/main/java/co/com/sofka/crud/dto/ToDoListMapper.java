package co.com.sofka.crud.dto;

import co.com.sofka.crud.entitys.ToDo;
import co.com.sofka.crud.entitys.ToDoList;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ToDoListMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "toDos", target = "toDos")
    })
    ToDoListDTO todoToTodoDTO(ToDoList todo);
    Iterable<ToDoListDTO> toTodoDTOs( Iterable<ToDoList> todoList);

    @InheritInverseConfiguration
    ToDoList toTodo(ToDoListDTO todoDTO);
    Iterable<ToDoList> toTodos( Iterable<ToDoListDTO> todoList);
}
