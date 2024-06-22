package com.sistema.blog.sistema_blog_spring_boot_api_rest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.blog.sistema_blog_spring_boot_api_rest.Services.ComentarioService;
import com.sistema.blog.sistema_blog_spring_boot_api_rest.dto.ComentarioDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping("/publicaciones/{publicacionId}/comentarios")
    public List<ComentarioDTO> listarComentariosPorPublicacionId(
            @PathVariable(value = "publicacionId") Long publicacionId) {
        return comentarioService.obtenerComentariosPorPublicacionId(publicacionId);

    }

    @GetMapping("/publicaciones/{publicacionId}/comentarios/{comentarioId}")
    public ResponseEntity<ComentarioDTO> obtenerComentarioById(
            @PathVariable(value = "publicacionId") Long publicacionId,
            @PathVariable(value = "comentarioId") Long comentarioId) {

        ComentarioDTO comentarioDTO = comentarioService.obtenerComentarioById(publicacionId, comentarioId);

        return new ResponseEntity<>(comentarioDTO, HttpStatus.OK);

    }    

    @PostMapping("/publicaciones/{publicacionId}/comentarios")
    public ResponseEntity<ComentarioDTO> guardarComentario(@PathVariable(value = "publicacionId") Long publicacionId,
            @Valid @RequestBody ComentarioDTO comentarioDTO) {
        return new ResponseEntity<>(comentarioService.crearComentario(publicacionId, comentarioDTO),
                HttpStatus.CREATED);
    }

    @PutMapping("/publicaciones/{publicacionId}/comentarios/{comentarioId}")
    public ResponseEntity<ComentarioDTO> actualizarComentario(@Valid
            @PathVariable(value = "publicacionId") Long publicacionId,
            @PathVariable(value = "comentarioId") Long comentarioId,
            @Valid @RequestBody ComentarioDTO comentarioDTO) {
        return new ResponseEntity<>(comentarioService.actualizarComentario(comentarioDTO, publicacionId, comentarioId),
                HttpStatus.OK);
    }

    @DeleteMapping("/publicaciones/{publicacionId}/comentarios/{comentarioId}")
    public ResponseEntity<String> eliminarComentario(
        @PathVariable(value = "publicacionId") Long publicacionId,
        @PathVariable(value = "comentarioId") Long comentarioId) {
            comentarioService.eliminarComentario(publicacionId, comentarioId);
            return new ResponseEntity<>("El comentario se ha eliminado correctamente", HttpStatus.OK);
        }
}
