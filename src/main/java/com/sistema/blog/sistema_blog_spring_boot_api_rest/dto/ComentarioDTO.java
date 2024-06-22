package com.sistema.blog.sistema_blog_spring_boot_api_rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ComentarioDTO {

    private long Id;

    @NotEmpty(message = "El nombre no puede ser vacío o nulo")
    private String nombre;

    @NotEmpty(message = "El correo no puede ser vacío o nulo")
    @Email
    private String email;

    @NotEmpty
    @Size(min = 10, message = "El contenido del comentario debe tener al menos 10 caracteres")
    private String contenido;
    
    public long getId() {
        return Id;
    }
    public void setId(long id) {
        Id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getContenido() {
        return contenido;
    }
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    public ComentarioDTO() {
    }

}
