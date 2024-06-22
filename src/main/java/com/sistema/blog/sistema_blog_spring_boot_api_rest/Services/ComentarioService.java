package com.sistema.blog.sistema_blog_spring_boot_api_rest.Services;

import java.util.List;

import com.sistema.blog.sistema_blog_spring_boot_api_rest.dto.ComentarioDTO;

public interface ComentarioService {

    public ComentarioDTO crearComentario(long publicacionId, ComentarioDTO comentarioDTO);

    //public PublicacionResponse obtenerTodasLPublicaciones(int numeroDePagina, int registrosPorPagina, String ordenarPorColumna, String sortDir);

    public List<ComentarioDTO> obtenerComentariosPorPublicacionId(long publicacionId);

    public ComentarioDTO obtenerComentarioById(Long publicacionId, Long comentarioId);

    public ComentarioDTO actualizarComentario(ComentarioDTO comentarioDTO, Long publicacionId, Long id);

    public void eliminarComentario(Long publicacionId, Long id);    

}
