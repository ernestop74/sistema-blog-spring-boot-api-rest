package com.sistema.blog.sistema_blog_spring_boot_api_rest.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sistema.blog.sistema_blog_spring_boot_api_rest.Entities.Comentario;
import com.sistema.blog.sistema_blog_spring_boot_api_rest.Entities.Publicacion;
import com.sistema.blog.sistema_blog_spring_boot_api_rest.Exceptions.BlogAppException;
import com.sistema.blog.sistema_blog_spring_boot_api_rest.Exceptions.ResourceNotFoundException;
import com.sistema.blog.sistema_blog_spring_boot_api_rest.Repositories.ComentarioRepository;
import com.sistema.blog.sistema_blog_spring_boot_api_rest.Repositories.PublicacionRepository;
import com.sistema.blog.sistema_blog_spring_boot_api_rest.dto.ComentarioDTO;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public ComentarioDTO crearComentario(long publicacionId, ComentarioDTO comentarioDTO) {
        Comentario comentario = mapearEntidad(comentarioDTO);
        Publicacion publicacion = publicacionRepository.findById(publicacionId)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));
        comentario.setPublicacion(publicacion);
        Comentario newComentario = comentarioRepository.save(comentario);

        return mapearDTO(newComentario);
    }

    @Override
    public List<ComentarioDTO> obtenerComentariosPorPublicacionId(long publicacionId) {
        List<Comentario> comentarios = comentarioRepository.findByPublicacionId(publicacionId);

        return comentarios.stream().map(comentario -> mapearDTO(comentario)).collect(Collectors.toList());

    }

    @Override
    public ComentarioDTO obtenerComentarioById(Long publicacionId, Long comentarioId) {
        Publicacion publicacion = publicacionRepository.findById(publicacionId)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));
        
        Comentario comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));

        if(!comentario.getPublicacion().getId().equals(publicacion.getId())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Este comentario no pertenece a la publicación");
        }

        return mapearDTO(comentario);
    }

    @Override
    public ComentarioDTO actualizarComentario(ComentarioDTO comentarioDTO, Long publicacionId, Long id) {
        Publicacion publicacion = publicacionRepository.findById(publicacionId)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));
        
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", id));

        if(!comentario.getPublicacion().getId().equals(publicacion.getId())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Este comentario no pertenece a la publicación");
        }

        comentario.setNombre(comentarioDTO.getNombre());
        comentario.setEmail(comentarioDTO.getEmail());
        comentario.setContenido(comentarioDTO.getContenido());

        Comentario comentarioActualizado = comentarioRepository.save(comentario);

        return mapearDTO(comentarioActualizado);
    }

    @Override
    public void eliminarComentario(Long publicacionId, Long id) {
        Publicacion publicacion = publicacionRepository.findById(publicacionId)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));
        
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", id));

        if(!comentario.getPublicacion().getId().equals(publicacion.getId())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Este comentario no pertenece a la publicación");
        }
        
        comentarioRepository.delete(comentario);
    }

    private ComentarioDTO mapearDTO(Comentario comentario) {
        ComentarioDTO comentarioDTO = modelMapper.map(comentario, ComentarioDTO.class);

        return comentarioDTO;
    }

    private Comentario mapearEntidad(ComentarioDTO comentarioDTO) {
        Comentario comentario = modelMapper.map(comentarioDTO, Comentario.class);

        return comentario;
    }
}
