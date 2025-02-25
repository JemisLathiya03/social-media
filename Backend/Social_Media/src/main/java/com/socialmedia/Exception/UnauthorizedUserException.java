package com.socialmedia.Exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedUserException extends RuntimeException {

    String unauthorizedErrorMessage = "The user is not found.";

    public UnauthorizedUserException(String message, String unauthorizedErrorMessage) {
        super(message);
        this.unauthorizedErrorMessage = unauthorizedErrorMessage;
    }

    public UnauthorizedUserException(String message) {
        super(message);
    }

}
