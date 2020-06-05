package ro.msg.learning.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class OutOfStockAdvice {

    @ResponseBody
    @ExceptionHandler(OutOfStockException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String outOfStockHandler(OutOfStockException e){
        return e.getMessage();
    }
}
