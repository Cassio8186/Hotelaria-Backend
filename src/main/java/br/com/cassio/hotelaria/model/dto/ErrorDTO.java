package br.com.cassio.hotelaria.model.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorDTO implements Serializable {

	private static final long serialVersionUID = 8911978209490691030L;

	private String exception;

	private String simpleMessage;

	private String message;

	private int code;

	private HttpStatus statusCode;
	private List<String> errors;
}
