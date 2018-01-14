package application.model.exception;

public class ModelException extends RuntimeException {

    public ModelException(Throwable cause){
        super(cause);
    }

    public ModelException(String message) {
        super(message);
    }

}
