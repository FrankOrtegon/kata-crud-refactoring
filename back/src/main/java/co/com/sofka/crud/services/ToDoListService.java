package co.com.sofka.crud.services;

import co.com.sofka.crud.dao.ToDoListRepository;
import co.com.sofka.crud.dao.ToDoRepository;
import co.com.sofka.crud.dto.ToDoModelDTO;
import co.com.sofka.crud.dto.ToDoListModelDTO;
import co.com.sofka.crud.entitys.ToDo;
import co.com.sofka.crud.entitys.ToDoList;
import co.com.sofka.crud.controller.NotFoundIdException;
import co.com.sofka.crud.controller.ToDoBusinessException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ToDoListService {

    public static final String NO_FAULT_ID = "No existe el id de la lista";
    private ToDoListRepository toDoListRepository;
    private ToDoRepository toDoRepository;

    @Autowired
    public ToDoListService(ToDoListRepository toDoListRepository, ToDoRepository toDoRepository) {
        this.toDoListRepository = toDoListRepository;
        this.toDoRepository = toDoRepository;
    }

    public Set<ToDoModelDTO> getToDosByListId(Long id) {
        return toDoListRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdException(NO_FAULT_ID))
                .getToDos().stream()
                .map(item -> new ToDoModelDTO(item.getId(), item.getName(), item.isCompleted(), id))
                .collect(Collectors.toSet());
    }

    public ToDoModelDTO addNewToDoByListId(Long listId, ToDoModelDTO aToDoModel) {
        var listToDo = toDoListRepository.findById(listId)
                .orElseThrow(() -> new NotFoundIdException(NO_FAULT_ID));
        var toDo = new ToDo();

        toDo.setCompleted(aToDoModel.isCompleted());
        toDo.setName(Objects.requireNonNull(aToDoModel.getName()));
        toDo.setId(aToDoModel.getId());

        if(toDo.getName().isEmpty() || toDo.getName().length() < 3){
            throw new ToDoBusinessException("No valid entity To-Do to be save");
        }

        //addition new to-do
        listToDo.getToDos().add(toDo);

        var listUpdated = toDoListRepository.save(listToDo);
        //last item
        var lastToDo = listUpdated.getToDos()
                .stream()
                .max(Comparator.comparingInt(item -> item.getId().intValue()))
                .orElseThrow();
        aToDoModel.setId(lastToDo.getId());
        aToDoModel.setListId(listId);
        return aToDoModel;
    }

    public ToDoModelDTO updateAToDoByListId(Long listId, ToDoModelDTO aToDoModel) {
        var listToDo = toDoListRepository.findById(listId)
                .orElseThrow(() -> new NotFoundIdException(NO_FAULT_ID));

        //edit to-do
        for(var item : listToDo.getToDos()){
            if(item.getId().equals(aToDoModel.getId())){
                item.setCompleted(aToDoModel.isCompleted());
                item.setName(Objects.requireNonNull(aToDoModel.getName()));
                item.setId(Objects.requireNonNull(aToDoModel.getId()));
            }
        }

        toDoListRepository.save(listToDo);

        return aToDoModel;
    }


    public ToDoListModelDTO newListToDo(ToDoListModelDTO aToDoListModel) {
        var listToDo = new ToDoList();
        listToDo.setName(Objects.requireNonNull(aToDoListModel.getName()));
        if(listToDo.getName().isEmpty() || listToDo.getName().length() < 3){
            throw new ToDoBusinessException("No valid entity List To-Do to be save");
        }
        var id = toDoListRepository.save(listToDo).getId();
        aToDoListModel.setId(id);
        return aToDoListModel;
    }

    public Set<ToDoListModelDTO> getAllListToDos() {
        return StreamSupport
                .stream(toDoListRepository.findAll().spliterator(), false)
                .map(toDoList -> {
                    var listDto = toDoList.getToDos()
                            .stream()
                            .map(item -> new ToDoModelDTO(item.getId(), item.getName(), item.isCompleted(), toDoList.getId()))
                            .collect(Collectors.toSet());
                    return new ToDoListModelDTO(toDoList.getId(), toDoList.getName(), listDto);
                })
                .collect(Collectors.toSet());
    }

    public void deleteListById(Long listId){
        var listToDo = toDoListRepository.findById(listId)
                .orElseThrow(() -> new NotFoundIdException(NO_FAULT_ID));
        toDoListRepository.delete(listToDo);
    }

    public void deleteAToDoById(Long id) {
        var toDo = toDoRepository.findById(id).orElseThrow();
        toDoRepository.delete(toDo);
    }
}
