package com.csi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFountException extends RuntimeException {

 public RecordNotFountException(String msg){
     super(msg);
 }

}
