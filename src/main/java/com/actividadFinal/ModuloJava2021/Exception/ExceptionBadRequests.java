package com.actividadFinal.ModuloJava2021.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Ingreso un dato erroneo")
public class ExceptionBadRequests extends Exception{
    public ExceptionBadRequests(HttpStatus badRequest, String mensaje, ExceptionBadRequests ex){
        super(mensaje);
    }
}
