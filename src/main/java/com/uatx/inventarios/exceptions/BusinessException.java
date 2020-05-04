package com.uatx.inventarios.exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException(String mensaje){
        super(mensaje);
    }
}
