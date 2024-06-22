package com.sistema.blog.sistema_blog_spring_boot_api_rest.dto;

import java.util.Date;

public class ErrorHandlerDetails {

    private Date marcaDeTiempo;
    private String msg;
    private String details;
    private int statusCode;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public ErrorHandlerDetails(Date marcaDeTiempo, String msg, String details, int statusCode) {
        this.marcaDeTiempo = marcaDeTiempo;
        this.msg = msg;
        this.details = details;
        this.statusCode = statusCode;
    }

    public Date getMarcaDeTiempo() {
        return marcaDeTiempo;
    }

    public void setMarcaDeTiempo(Date marcaDeTiempo) {
        this.marcaDeTiempo = marcaDeTiempo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }    
}
