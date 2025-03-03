package com.dailyDeals.dailyDeals_v6.customExceptions;

public class CustomGlobalException extends RuntimeException{
    private final Boolean isValidation;
    private final Boolean isNotFoundError;


    public CustomGlobalException(String message, Boolean isValidation, Boolean isNotFoundError){
        super(message);
        this.isValidation = isValidation;
        this.isNotFoundError = isNotFoundError;
    }

    public Boolean getValidation() {
        return isValidation;
    }

    public Boolean getNotFoundError() {
        return isNotFoundError;
    }
}
