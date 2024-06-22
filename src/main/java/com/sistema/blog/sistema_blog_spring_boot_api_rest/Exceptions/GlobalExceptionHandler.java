package com.sistema.blog.sistema_blog_spring_boot_api_rest.Exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sistema.blog.sistema_blog_spring_boot_api_rest.dto.ErrorHandlerDetails;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorHandlerDetails> manejarResourceNotFoundException(ResourceNotFoundException exception,
            WebRequest webRequest) {
        ErrorHandlerDetails errorHandlerDetails = new ErrorHandlerDetails(new Date(), exception.getMessage(), webRequest.getDescription(false), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorHandlerDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogAppException.class)
    public ResponseEntity<ErrorHandlerDetails> manejarBlogAppException(BlogAppException exception,
            WebRequest webRequest) {
        ErrorHandlerDetails errorHandlerDetails = new ErrorHandlerDetails(new Date(), exception.getMessage(), webRequest.getDescription(false), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorHandlerDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ErrorHandlerDetails> manejarAuthorizationDeniedException(Exception exception,
            WebRequest webRequest) {

        ErrorHandlerDetails errorHandlerDetails = new ErrorHandlerDetails(new Date(), exception.getMessage(), webRequest.getDescription(false), HttpStatus.FORBIDDEN.value());
        return new ResponseEntity<>(errorHandlerDetails, HttpStatus.FORBIDDEN);
    }
    
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorHandlerDetails> manejarBadCredentialsException(Exception exception,
            WebRequest webRequest) {

        ErrorHandlerDetails errorHandlerDetails = new ErrorHandlerDetails(new Date(), exception.getMessage(), webRequest.getDescription(false), HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(errorHandlerDetails, HttpStatus.UNAUTHORIZED);
    }    

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorHandlerDetails> manejarGlobalException(Exception exception,
            WebRequest webRequest) {

        ErrorHandlerDetails errorHandlerDetails = new ErrorHandlerDetails(new Date(), exception.getMessage(), webRequest.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorHandlerDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    } 

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        
        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String nombreCampo = ((FieldError)error).getField();
            String msg = error.getDefaultMessage();

            errores.put(nombreCampo, msg);
        });

        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }
}
