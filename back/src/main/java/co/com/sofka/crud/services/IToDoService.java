package co.com.sofka.crud.services;

import co.com.sofka.crud.dto.ToDoDTO;

public interface IToDoService {

    Iterable<ToDoDTO> list();

    ToDoDTO save(ToDoDTO todo);



    void delete(Long id);
}