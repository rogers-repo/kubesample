package com.roger.foody.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Order with given id already exists")
public class OrderExistsException extends Exception {
}