package com.example.test.utils;

import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.HttpStatus;

public class ApiUtils {
	
	// 성공
	public static <T> ApiResult<T> success(T response) {
		return new ApiResult<>(true, response, null);
	}

	// 실패(Exception Throw 시)
	public static ApiResult<?> error(Throwable throwable, HttpStatus status) {
		return new ApiResult<>(false, null, new ApiError(throwable, status));
	}

	// 실패(Error message 존재 시)
	public static ApiResult<?> error(String message, HttpStatus status) {
		return new ApiResult<>(false, null, new ApiError(message, status));
	}

	@Getter
	public static class ApiError {
		private final String message;
		private final int status;

		ApiError(Throwable throwable, HttpStatus status) {
			this(throwable.getMessage(), status);
		}

		ApiError(String message, HttpStatus status) {
			this.message = message;
			this.status = status.value();
		}
		
		@Override
		public String toString() {
			return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
					.append("message", message)
					.append("status", status)
					.toString();
		}
	}

	@Getter
	public static class ApiResult<T> {
		private final boolean success;
		private final T response;
		private final ApiError error;

		private ApiResult(boolean success, T response, ApiError error) {
			this.success = success;
			this.response = response;
			this.error = error;
		}

		public boolean isSuccess() {
			return success;
		}

		@Override
		public String toString() {
			return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
					.append("success", success)
					.append("response", response)
					.append("error", error)
					.toString();
		}
	}
}
