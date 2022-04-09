package com.Haven.exception;

import com.Haven.enums.StatusCodeEnum;

public class MyException extends RuntimeException {
     private Integer code;
     private String message;
     public MyException(String message) {
         this.message = message;
     }
     public MyException(StatusCodeEnum statusCodeEnum) {
         this.code = statusCodeEnum.getCode();
         this.message = statusCodeEnum.getDescribe();
     }
}
