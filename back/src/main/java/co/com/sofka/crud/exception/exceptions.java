package co.com.sofka.crud.exception;


public class exceptions extends RuntimeException {

    public exceptions() {
    }

    public exceptions(String message) {
        super(message);
    }

    public exceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public exceptions(Throwable cause) {
        super(cause);
    }
}