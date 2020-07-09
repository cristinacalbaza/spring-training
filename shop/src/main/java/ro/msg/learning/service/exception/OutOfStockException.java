package ro.msg.learning.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OutOfStockException extends RuntimeException {
    public static final long serialVersionUID = 1L;

    public OutOfStockException(){
        super("The order cannot be processed. Products unavailable.");
    }

}
