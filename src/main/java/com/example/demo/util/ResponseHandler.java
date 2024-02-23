package com.example.demo.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> response(
            HttpStatus status,
            Object response,
            String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status.value());
        map.put("data", response);
        map.put("message",message);

        return new ResponseEntity<>(map, status);
    }
}
