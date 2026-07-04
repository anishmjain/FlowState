package com.anish.flowstate.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String message) {
    super(message);
}
}
