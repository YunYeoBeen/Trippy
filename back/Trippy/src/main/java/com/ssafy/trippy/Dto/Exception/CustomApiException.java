package com.ssafy.trippy.Dto.Exception;

import java.util.Map;

public class CustomApiException extends RuntimeException{

    public CustomApiException(String message) {
        super(message);
    }
}
