package com.zzaug.web.support;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class ApiResponse<B> extends ResponseEntity<B> {

	public ApiResponse(final HttpStatus status) {
		super(status);
	}

	public ApiResponse(final B body, final HttpStatus status) {
		super(body, status);
	}

	@Getter
	public static class FailureBody implements Serializable {

		private String code;
		private String message;
		private LocalDateTime timestamp;

		public FailureBody(String code, String message) {
			this.code = code;
			this.message = message;
			this.timestamp = LocalDateTime.now();
		}

		public FailureBody(final String message) {
			this.message = message;
			this.timestamp = LocalDateTime.now();
		}
	}

	@Getter
	public static class SuccessBody<D> implements Serializable {
		private D data;
		private String message;
		private String code;
		private LocalDateTime timestamp;

		public SuccessBody(D data, String message, String code) {
			this.data = data;
			this.message = message;
			this.code = code;
			this.timestamp = LocalDateTime.now();
		}
	}

	@Getter
	public static class Success implements Serializable {
		private String message;
		private String code;
		private LocalDateTime timestamp;

		public Success(String message, String code) {
			this.message = message;
			this.code = code;
			this.timestamp = LocalDateTime.now();
		}
	}
}
