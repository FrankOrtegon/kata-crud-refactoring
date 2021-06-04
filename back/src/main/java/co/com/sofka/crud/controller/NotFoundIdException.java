package co.com.sofka.crud.controller;

public class NotFoundIdException extends RuntimeException {
    public NotFoundIdException(String message){
        super(message);
    }
}