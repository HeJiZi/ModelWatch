package exception;

public class UpdateModelException extends RuntimeException {
    public UpdateModelException(String s){
        super(s);
    }
    public UpdateModelException(String s,Throwable throwable){
        super(s,throwable);
    }
}
