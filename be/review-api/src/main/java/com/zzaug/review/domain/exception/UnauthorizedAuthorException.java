package com.zzaug.review.domain.exception;

import com.zzaug.review.support.MessageSourceAccessor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class UnauthorizedAuthorException extends RuntimeException{

    private final String code;

    public UnauthorizedAuthorException(@NotEmpty final String errorCode){
        super(MessageSourceAccessor.getMessage(errorCode));
        this.code = errorCode;
    }

}
