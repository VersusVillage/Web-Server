package vv.versusvillage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ForumPostNotFoundException extends RuntimeException {
    public ForumPostNotFoundException(String message) {
        super(message);
    }
}