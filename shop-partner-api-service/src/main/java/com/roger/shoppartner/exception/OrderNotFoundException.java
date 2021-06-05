package com.roger.shoppartner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Order Not Exist")
public class OrderNotFoundException extends Exception {
}
