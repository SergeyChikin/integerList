package exception;

public class IndexNotFoundException extends RuntimeException{
    public IndexNotFoundException() {
    }

    public IndexNotFoundException(String message) {
        super(message);
    }
}
