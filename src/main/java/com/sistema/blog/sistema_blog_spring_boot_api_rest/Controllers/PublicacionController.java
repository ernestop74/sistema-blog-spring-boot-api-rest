package com.sistema.blog.sistema_blog_spring_boot_api_rest.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.blog.sistema_blog_spring_boot_api_rest.Helpers.AppConsts;
import com.sistema.blog.sistema_blog_spring_boot_api_rest.Services.PublicacionService;
import com.sistema.blog.sistema_blog_spring_boot_api_rest.dto.PublicacionDTO;
import com.sistema.blog.sistema_blog_spring_boot_api_rest.dto.PublicacionResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDTO> obtenerPublicacionPorId(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(publicacionService.obtenerPublicacionPorId(id));
    }

    @GetMapping
    public PublicacionResponse listarPublicaciones(
            @RequestParam(value = "pageNumber", defaultValue = AppConsts.PAGE_NUMBER_DEFAULT, required = false) int numeroDePagina,
            @RequestParam(value = "recordsPerPage", defaultValue = AppConsts.RECORDS_PER_PAGE_DEFAULT, required = false) int registrosPorPagina,
            @RequestParam(value = "sortBy", defaultValue = AppConsts.ORDER_BY_COLUMN_DEFAULT, required = false) String ordenarPorColumna,
            @RequestParam(value = "sortDir", defaultValue = AppConsts.ORDER_DIR_DEFAULT, required = false) String sortDir) {
        return publicacionService.obtenerTodasLPublicaciones(numeroDePagina, registrosPorPagina, ordenarPorColumna,
                sortDir);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PublicacionDTO> guardarPublicacion(@Valid @RequestBody PublicacionDTO publicacionDTO) {

        return new ResponseEntity<>(publicacionService.crearPublicacion(publicacionDTO), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')") 
    public ResponseEntity<PublicacionDTO> actualizarPublicacion(@Valid @RequestBody PublicacionDTO publicacionDTO,
            @PathVariable(name = "id") Long id) {
        PublicacionDTO publicacionResponse = publicacionService.actualizPublicacion(publicacionDTO, id);

        return new ResponseEntity<>(publicacionResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") Long id) {
        publicacionService.eliminarPublicacion(id);
        return new ResponseEntity<>("Publicación eliminada de manera exitosa", HttpStatus.OK);
    }

}
