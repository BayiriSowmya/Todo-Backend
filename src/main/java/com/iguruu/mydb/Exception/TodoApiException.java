package com.iguruu.mydb.Exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class TodoApiException extends RuntimeException {

	private HttpStatus status;
	private String message;
	
}
