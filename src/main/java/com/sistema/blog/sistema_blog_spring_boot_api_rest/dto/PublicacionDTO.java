package com.sistema.blog.sistema_blog_spring_boot_api_rest.dto;

import java.util.Set;

import com.sistema.blog.sistema_blog_spring_boot_api_rest.Entities.Comentario;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PublicacionDTO {
    private Long id;

    @NotEmpty
    @Size(min = 3, message = "El título de la publicación debe tener al menos 3 caracteres")
    private String titulo;

    @NotEmpty
    @Size(min = 20, message = "La descripción de la publicación debe tener al menos 20 caracteres")    
    private String descripcion;

    @NotEmpty(message = "El contenido no debe estar vacío") 
    private String contenido;

    private Set<Comentario> comentarios;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getContenido() {
        return contenido;
    }
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    public PublicacionDTO() {
        super();
    }
    public Set<Comentario> getComentarios() {
        return comentarios;
    }
    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
    

}
