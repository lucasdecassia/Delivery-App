package com.delivery.delivery_tracking.domain.model.exception;

public class DomainException extends RuntimeException{

    public DomainException() {
    }

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
