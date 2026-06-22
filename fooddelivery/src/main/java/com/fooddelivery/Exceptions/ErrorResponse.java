package com.fooddelivery.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private Date timestamp;
    private int statusCode;
    private HttpStatus status;
    private String error;
    private String message;
    private String path;

    private Map<String, String> fieldErrors;

    public ErrorResponse(HttpStatus status, int statusCode, String error, String message, String path) {

        this.timestamp = new Date();
        this.status = status;
        this.statusCode = statusCode;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
