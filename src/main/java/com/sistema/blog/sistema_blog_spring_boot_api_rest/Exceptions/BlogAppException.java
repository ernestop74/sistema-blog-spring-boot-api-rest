package com.sistema.blog.sistema_blog_spring_boot_api_rest.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BlogAppException extends RuntimeException {

    private static final Long serialVersionUID = 1L;
    private HttpStatus estado;
    private String msg;

    public BlogAppException(HttpStatus estado, String msg) {
        super(String.format("%s", msg));
        this.estado = estado;
        this.msg = msg;
    }

    public BlogAppException(HttpStatus estado, String msg, String msg1) {
        this.estado = estado;
        this.msg = msg;
        this.msg = msg1;
    }

    public HttpStatus getEstado() {
        return estado;
    }

    public void setEstado(HttpStatus estado) {
        this.estado = estado;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }    

}
