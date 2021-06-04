package co.com.sofka.crud.repository.dao;


import co.com.sofka.crud.entitys.ToDoList;
import org.springframework.data.repository.CrudRepository;

public interface ToDoListRepository extends CrudRepository<ToDoList, Long> {
}