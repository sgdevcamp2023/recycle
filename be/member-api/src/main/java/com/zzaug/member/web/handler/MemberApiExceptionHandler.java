package com.zzaug.member.web.handler;

import static com.zzaug.web.handler.ExceptionMessage.FAIL_NOT_FOUND;
import static com.zzaug.web.handler.ExceptionMessage.FAIL_REQUEST;
import static com.zzaug.web.handler.ExceptionMessage.REQUEST_INVALID;
import static com.zzaug.web.handler.ExceptionMessage.REQUEST_INVALID_FORMAT;

import com.zzaug.member.domain.exception.InvalidRequestException;
import com.zzaug.member.domain.exception.OverMaxTryCountException;
import com.zzaug.member.domain.exception.SourceNotFoundException;
import com.zzaug.web.handler.LoggingHandler;
import com.zzaug.web.support.ApiResponse;
import com.zzaug.web.support.ApiResponseGenerator;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
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

	@ExceptionHandler({InvalidRequestException.class})
	public ApiResponse<ApiResponse.FailureBody> handleInvalidRequest(
			final InvalidRequestException ex, final HttpServletRequest request) {
		loggingHandler.writeLog(ex, request);
		String parameter = ex.getParam();
		if (parameter.equals(Strings.EMPTY)) {
			return ApiResponseGenerator.fail(
					REQUEST_INVALID.getCode(), REQUEST_INVALID.getMessage(), HttpStatus.BAD_REQUEST);
		}
		String requestInvalidCode = String.format(REQUEST_INVALID_FORMAT.getCode(), parameter);
		return ApiResponseGenerator.fail(
				requestInvalidCode, REQUEST_INVALID_FORMAT.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({OverMaxTryCountException.class})
	public ApiResponse<ApiResponse.FailureBody> handleFailRequest(
			final Exception ex, final HttpServletRequest request) {
		loggingHandler.writeLog(ex, request);
		return ApiResponseGenerator.fail(
				FAIL_REQUEST.getCode(), FAIL_REQUEST.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
