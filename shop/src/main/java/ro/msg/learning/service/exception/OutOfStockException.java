package ro.msg.learning.service.exception;

public class OutOfStockException extends RuntimeException {
    public static final long serialVersionUID = 1L;

    public OutOfStockException(){
        super("The order cannot be processed. Products unavailable.");
    }

}
