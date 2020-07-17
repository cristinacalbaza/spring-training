package ro.msg.learning.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsernameNotFoundException extends RuntimeException{
    public static final long serialVersionUID = 1L;

    public UsernameNotFoundException(String username){
        super("Username " + username + "not found!");
    }
}
