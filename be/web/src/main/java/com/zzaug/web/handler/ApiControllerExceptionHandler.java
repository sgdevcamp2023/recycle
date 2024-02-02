package com.zzaug.web.handler;

import static com.zzaug.web.handler.ExceptionMessage.ACCESS_DENIED;
import static com.zzaug.web.handler.ExceptionMessage.FAIL;
import static com.zzaug.web.handler.ExceptionMessage.FAIL_AUTHENTICATION;
import static com.zzaug.web.handler.ExceptionMessage.FAIL_NOT_FOUND;
import static com.zzaug.web.handler.ExceptionMessage.FAIL_REQUEST;
import static com.zzaug.web.handler.ExceptionMessage.REQUEST_INVALID_FORMAT;

import com.zzaug.web.support.ApiResponse;
import com.zzaug.web.support.ApiResponse.FailureBody;
import com.zzaug.web.support.ApiResponseGenerator;
import java.nio.file.AccessDeniedException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

/** API 요청 처리 중 발생하는 예외를 처리하는 핸들러 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ApiControllerExceptionHandler {

	private final LoggingHandler loggingHandler;

	@ExceptionHandler(IllegalArgumentException.class)
	public final ApiResponse<ApiResponse.FailureBody> handleBadRequest(
			final IllegalArgumentException ex, final HttpServletRequest request) {
		loggingHandler.writeLog(ex, request);
		return ApiResponseGenerator.fail(FAIL.getCode(), FAIL.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({
		MethodArgumentTypeMismatchException.class,
		TypeMismatchException.class,
		ServletRequestBindingException.class,
		BindException.class,
		MethodArgumentNotValidException.class,
		HttpRequestMethodNotSupportedException.class,
		HttpMediaTypeNotSupportedException.class,
		HttpMediaTypeNotAcceptableException.class,
		HttpMessageNotReadableException.class,
		MissingServletRequestParameterException.class,
		ConstraintViolationException.class,
		ValidationException.class
	})
	public final ApiResponse<ApiResponse.FailureBody> handleBadRequest(
			final Exception ex, final HttpServletRequest request) {
		loggingHandler.writeLog(ex, request);
		return handleRequestDetails(ex);
	}

	private ApiResponse<FailureBody> handleRequestDetails(Exception ex) {
		if (ex instanceof MethodArgumentTypeMismatchException) {
			return handleRequestDetail((MethodArgumentTypeMismatchException) ex);
		}
		if (ex instanceof ConstraintViolationException) {
			return handleRequestDetail((ConstraintViolationException) ex);
		}

		if (ex instanceof MissingServletRequestParameterException) {
			return handleRequestDetail((MissingServletRequestParameterException) ex);
		}

		if (ex instanceof MethodArgumentNotValidException) {
			return handleRequestDetail((MethodArgumentNotValidException) ex);
		}
		if (ex instanceof ValidationException) {
			return handleRequestDetail((ValidationException) ex);
		}
		return ApiResponseGenerator.fail(
				FAIL_REQUEST.getCode(), FAIL_REQUEST.getMessage(), HttpStatus.BAD_REQUEST);
	}

	private ApiResponse<FailureBody> handleRequestDetail(MethodArgumentTypeMismatchException ex) {
		MethodArgumentTypeMismatchException rex = ex;
		String requestInvalidCode = String.format(REQUEST_INVALID_FORMAT.getCode(), rex.getName());
		return ApiResponseGenerator.fail(
				requestInvalidCode, REQUEST_INVALID_FORMAT.getMessage(), HttpStatus.BAD_REQUEST);
	}

	private ApiResponse<FailureBody> handleRequestDetail(ConstraintViolationException ex) {
		ConstraintViolationException rex = ex;
		String message = rex.getMessage();
		String parameter = message.split("\\.")[1].split(":")[0];
		String requestInvalidCode = String.format(REQUEST_INVALID_FORMAT.getCode(), parameter);
		return ApiResponseGenerator.fail(
				requestInvalidCode, REQUEST_INVALID_FORMAT.getMessage(), HttpStatus.BAD_REQUEST);
	}

	private ApiResponse<FailureBody> handleRequestDetail(MissingServletRequestParameterException ex) {
		MissingServletRequestParameterException rex = ex;
		String message = rex.getMessage();
		String parameter = message.split("\'")[1].split("\'")[0];
		String requestInvalidCode = String.format(REQUEST_INVALID_FORMAT.getCode(), parameter);
		return ApiResponseGenerator.fail(
				requestInvalidCode, REQUEST_INVALID_FORMAT.getMessage(), HttpStatus.BAD_REQUEST);
	}

	private ApiResponse<FailureBody> handleRequestDetail(MethodArgumentNotValidException ex) {
		MethodArgumentNotValidException rex = ex;
		List<ObjectError> errors = rex.getAllErrors();
		if (errors.size() == 1) {
			String parameter = rex.getFieldError().getField();
			String requestInvalidCode = String.format(REQUEST_INVALID_FORMAT.getCode(), parameter);
			return ApiResponseGenerator.fail(
					requestInvalidCode, REQUEST_INVALID_FORMAT.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return ApiResponseGenerator.fail(
				FAIL_REQUEST.getCode(), FAIL_REQUEST.getMessage(), HttpStatus.BAD_REQUEST);
	}

	private ApiResponse<FailureBody> handleRequestDetail(ValidationException ex) {
		ValidationException rex = ex;
		return ApiResponseGenerator.fail(
				FAIL_REQUEST.getCode(), FAIL_REQUEST.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({AuthenticationException.class})
	public ApiResponse<ApiResponse.FailureBody> handle(
			final Exception ex, final HttpServletRequest request) {
		loggingHandler.writeLog(ex, request);
		return ApiResponseGenerator.fail(
				FAIL_AUTHENTICATION.getCode(), FAIL_AUTHENTICATION.getMessage(), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler({NoHandlerFoundException.class})
	public ApiResponse<ApiResponse.FailureBody> handleNotFound(
			final Exception ex, final HttpServletRequest request) {
		loggingHandler.writeLog(ex, request);
		return ApiResponseGenerator.fail(
				FAIL_NOT_FOUND.getCode(), FAIL_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({AccessDeniedException.class})
	public ApiResponse<ApiResponse.FailureBody> handleForbidden(
			final AccessDeniedException ex, final HttpServletRequest request) {
		loggingHandler.writeLog(ex, request);
		return ApiResponseGenerator.fail(
				ACCESS_DENIED.getCode(), ACCESS_DENIED.getMessage(), HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(Exception.class)
	public ApiResponse<ApiResponse.FailureBody> handleInternalServerError(
			final Exception ex, final HttpServletRequest request) {
		loggingHandler.writeLog(ex, request);
		return ApiResponseGenerator.fail(
				FAIL.getCode(), FAIL.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
