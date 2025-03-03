package com.dailyDeals.dailyDeals_v6.HandleRestException;


import com.dailyDeals.dailyDeals_v6.customExceptions.CustomGlobalException;
import com.dailyDeals.dailyDeals_v6.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@ControllerAdvice
public class CustomRestExceptionHandler {

    @ExceptionHandler(value = CustomGlobalException.class)
    public ResponseEntity<Object> handleCustomGlobalException(CustomGlobalException ex){
        if (ex.getValidation()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError(HttpStatus.BAD_REQUEST,ex.getMessage(), new Date()));
        } else if (ex.getNotFoundError()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(HttpStatus.NOT_FOUND,ex.getMessage(), new Date()));
        }
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ApiError(HttpStatus.SERVICE_UNAVAILABLE,ex.getMessage(), new Date()));
    }
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleRuntimeException(Exception ex){
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ApiError(HttpStatus.BAD_GATEWAY,ex.getMessage(), new Date()));
    }
}
