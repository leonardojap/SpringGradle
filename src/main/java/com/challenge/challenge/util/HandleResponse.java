package com.challenge.challenge.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.challenge.challenge.models.CustomResponse;

/*
     * Custom response, for all services, we'll return the same object:
     * {
     * success: true/false
     * message: 'any message',
     * data: ...{here we can put any type dynamic object}
     * }
     */
public class HandleResponse {
  public static <T> ResponseEntity<CustomResponse<T>> createResponse(T data, String message, HttpStatus status) {
    CustomResponse<T> response = new CustomResponse<>();
    response.setData(data);
    response.setSuccess(status.is2xxSuccessful());
    response.setMessage(message);
    return new ResponseEntity<>(response, status);
  }
}