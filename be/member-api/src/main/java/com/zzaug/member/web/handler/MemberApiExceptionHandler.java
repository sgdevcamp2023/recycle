package com.zzaug.member.web.handler;

import static com.zzaug.web.handler.ExceptionMessage.FAIL_NOT_FOUND;

import com.zzaug.member.domain.exception.SourceNotFoundException;
import com.zzaug.web.handler.LoggingHandler;
import com.zzaug.web.support.ApiResponse;
import com.zzaug.web.support.ApiResponseGenerator;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class MemberApiExceptionHandler {

	private final LoggingHandler loggingHandler;

	@ExceptionHandler({SourceNotFoundException.class})
	public ApiResponse<ApiResponse.FailureBody> handleNotFound(
			final Exception ex, final HttpServletRequest request) {
		loggingHandler.writeLog(ex, request);
		return ApiResponseGenerator.fail(
				FAIL_NOT_FOUND.getCode(), FAIL_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
	}
}
