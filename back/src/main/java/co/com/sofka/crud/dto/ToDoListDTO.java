package co.com.sofka.crud.dto;

import co.com.sofka.crud.entitys.ToDo;

import java.util.Set;

public class ToDoListDTO {
    private Long id;
    private String name;
    private Set<ToDoDTO> toDos;

    public ToDoListDTO(Long id, String name, Set<ToDoDTO> toDos) {
        this.id = id;
        this.name = name;
        this.toDos = toDos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ToDoDTO> getToDos() {
        return toDos;
    }

    public void setToDos(Set<ToDoDTO> toDos) {
        this.toDos = toDos;
    }
}
