package co.com.sofka.crud.services;

import co.com.sofka.crud.dto.ToDoDTO;
import co.com.sofka.crud.dto.ToDoMapper;
import co.com.sofka.crud.entitys.ToDo;
import co.com.sofka.crud.dao.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ToDoService implements IToDoService {

    @Autowired
    private ToDoRepository repository;
    @Autowired
    private ToDoMapper todoMapper;

    public ToDoDTO get(Long id) {
        return todoMapper.todoToTodoDTO(repository.findById(id).orElseThrow());
    }
    @Override
    public Iterable<ToDoDTO> list() {
        return todoMapper.toTodoDTOs(repository.findAll());
    }

    @Override
    public ToDoDTO save(ToDoDTO todo) {
        return todoMapper.todoToTodoDTO(repository.save(todoMapper.toTodo(todo)));
    }

    @Override
    public void delete(Long id) {
        repository.delete(todoMapper.toTodo(get(id)));
    }
}