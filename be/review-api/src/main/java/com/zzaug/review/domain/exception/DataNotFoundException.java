package com.zzaug.review.domain.exception;

import com.zzaug.review.support.MessageSourceAccessor;
import lombok.Getter;
import org.springframework.context.NoSuchMessageException;

import javax.validation.constraints.NotEmpty;

@Getter
public class DataNotFoundException extends NoSuchMessageException {

    private final String code;

    public DataNotFoundException(@NotEmpty final String errorCode){
        super(MessageSourceAccessor.getMessage(errorCode));
        this.code = errorCode;
    }
}
