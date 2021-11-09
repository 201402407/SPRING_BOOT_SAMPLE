package common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse extends CustomResponse {
	private String errorMessage;
	private int errorCode;

	public ErrorResponse(String errorMessage) {
		this.errorMessage = errorMessage;
		this.errorCode = 404;
	}
	public ErrorResponse(String errorMessage, int errorCode) {
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}
}
