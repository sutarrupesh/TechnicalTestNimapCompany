package com.assignment.test.response;

import lombok.Data;

@Data
public class ApiResponse {

    private String message;
    private Object data;
    private boolean succes;
    private boolean error;
    
}
