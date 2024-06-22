package com.sistema.blog.sistema_blog_spring_boot_api_rest.Services;

import com.sistema.blog.sistema_blog_spring_boot_api_rest.dto.PublicacionDTO;
import com.sistema.blog.sistema_blog_spring_boot_api_rest.dto.PublicacionResponse;

public interface PublicacionService {

    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);

    public PublicacionResponse obtenerTodasLPublicaciones(int numeroDePagina, int registrosPorPagina, String ordenarPorColumna, String sortDir);

    public PublicacionDTO obtenerPublicacionPorId(Long id);

    public PublicacionDTO actualizPublicacion(PublicacionDTO publicacionDTO, Long id);

    public void eliminarPublicacion(Long id);

}
