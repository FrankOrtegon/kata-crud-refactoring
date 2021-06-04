package co.com.sofka.crud.dto;

import java.util.HashSet;
import java.util.Set;

public class ToDoListModelDTO {
    private Long id;
    private String name;
    private Set<ToDoModelDTO> items = new HashSet<>();

    public ToDoListModelDTO(){
        super();
    }
    public ToDoListModelDTO(Long id, String name, Set<ToDoModelDTO> items) {
        this.id = id;
        this.name = name;
        this.items = items;
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

    public Set<ToDoModelDTO> getItems() {
        return items;
    }

    public void setItems(Set<ToDoModelDTO> items) {
        this.items = items;
    }
}