package com.dailyDeals.dailyDeals_v6.dto;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ApiError {

    public HttpStatus getErrorCode() {
        return ErrorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public Date getDate() {
        return date;
    }

    private final HttpStatus ErrorCode;
    private final String errorDesc;
    private final Date date;

    public ApiError(HttpStatus errorCode, String errorDesc, Date date) {
        super();
        ErrorCode = errorCode;
        this.errorDesc = errorDesc;
        this.date = date;
    }
}
