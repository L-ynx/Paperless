package at.fhtw.swen3.paperless.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class RestServerException extends RuntimeException{
    public RestServerException(String message) {
        super(message);
    }
}
