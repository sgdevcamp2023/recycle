package com.zzaug.review.domain.exception;

public class AlreadyDeletedException extends RuntimeException{
    public AlreadyDeletedException(String message) {
        super(message);
    }
}
