package co.com.sofka.crud.dao;

import co.com.sofka.crud.entitys.ToDo;
import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends CrudRepository<ToDo, Long> {
}
