package application.model.exception;

public class ModelException extends RuntimeException {
    private String message;

    public ModelException(Throwable cause){
        super(cause);
        this.message = cause.getMessage();
    }

    public ModelException(String message) {
        this.message = message;
    }

    public ModelException(){}

    @Override
    public String getMessage(){
        return this.message;
    }
}
