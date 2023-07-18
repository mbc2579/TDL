package com.tdl.tdl.dto;

import lombok.Getter;

@Getter

public class ApiResponseDto {
    private int statusCode;
    private String errorMessage;
    private Object data;

    public ApiResponseDto(int status, String message) {
        this.statusCode = status;
        this.errorMessage = message;
        this.data = null;
    }

    public ApiResponseDto(int status,String message, Object data) {
        this.statusCode = status;
        this.errorMessage = message;
        this.data = data;
    }
}