package co.com.sofka.crud.controller;


public class ToDoBusinessException extends RuntimeException{
    public ToDoBusinessException(String message){
        super(message);
    }
}
