package co.com.sofka.crud.dao;


import co.com.sofka.crud.entitys.ToDo;
import co.com.sofka.crud.entitys.ToDoList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoListRepository extends CrudRepository<ToDoList, Long> {
    Iterable<ToDo> findAllToDosById(Long id);
}