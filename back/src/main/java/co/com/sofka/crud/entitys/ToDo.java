package co.com.sofka.crud.entitys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "todo")
public class ToDo {
    @Id
    @GeneratedValue
    private Long id;
    private Long ListId;
    private String name;
    private boolean completed;


    public Long getListId() {
        return ListId;
    }

    public void setListId(Long listId) {
        this.ListId = listId;
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}