package exception;

public class ItemIsNullException extends RuntimeException{
    public ItemIsNullException() {
    }

    public ItemIsNullException(String message) {
        super(message);
    }
}
