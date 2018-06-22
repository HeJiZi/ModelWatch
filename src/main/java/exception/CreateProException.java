package exception;

public class CreateProException extends RuntimeException {
    public CreateProException(String s){
        super(s);
    }
    public CreateProException(String s,Throwable throwable){
        super(s,throwable);
    }

}
