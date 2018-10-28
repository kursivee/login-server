package com.kursivee.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class InvalidCredentialsHandler {

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void handleConflict(AccessDeniedException e, HttpServletResponse response) throws IOException{
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid login credentials");
    }

}
