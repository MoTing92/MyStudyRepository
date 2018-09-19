package com.moting.applyaccount.action;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.http.HttpStatus;

import com.moting.applyaccount.bean.ApiErrorResponse;

@RestControllerAdvice
public class MvcException {

	/*@ExceptionHandler(value = { ConstraintViolationException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorResponse constraintViolationException(ConstraintViolationException ex) {
		return new ApiErrorResponse(500, 5001, ex.getMessage());
	}*/
	  
	 @ExceptionHandler(value = { IllegalArgumentException.class })
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	 public ApiErrorResponse IllegalArgumentException(IllegalArgumentException ex) {
	  return new ApiErrorResponse(501, 5002, ex.getMessage());
	 }
	 
	 @ExceptionHandler(value = { NoHandlerFoundException.class })
	 @ResponseStatus(HttpStatus.NOT_FOUND)
	 public ApiErrorResponse noHandlerFoundException(Exception ex) {
	  return new ApiErrorResponse(404, 4041, ex.getMessage());
	 }
	 
	 @ExceptionHandler(value = { IncorrectCredentialsException.class })
	 @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	 public ApiErrorResponse incorrectCredentialsException(Exception ex) {
	  return new ApiErrorResponse(401, 4001, ex.getMessage());
	 }
	 
	 @ExceptionHandler(value = { Exception.class })
	 @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	 public ApiErrorResponse unknownException(Exception ex) {
	  return new ApiErrorResponse(500, 5002, ex.getMessage());
	 }
	 
	 
}
