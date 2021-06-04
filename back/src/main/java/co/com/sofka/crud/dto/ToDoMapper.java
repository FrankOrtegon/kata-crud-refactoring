package co.com.sofka.crud.dto;

import co.com.sofka.crud.entitys.ToDo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ToDoMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "completed", target = "completed"),
            @Mapping(source = "groupListId", target = "groupListId")
    })
    ToDoDTO todoToTodoDTO(ToDo todo);
    Iterable<ToDoDTO> toTodoDTOs( Iterable<ToDo> todoList);

    @InheritInverseConfiguration
    ToDo toTodo(ToDoDTO todoDTO);
    Iterable<ToDo> toTodos( List<ToDoDTO> todoList);
}