package com.sistema.blog.sistema_blog_spring_boot_api_rest.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sistema.blog.sistema_blog_spring_boot_api_rest.Entities.Publicacion;
import com.sistema.blog.sistema_blog_spring_boot_api_rest.Exceptions.ResourceNotFoundException;
import com.sistema.blog.sistema_blog_spring_boot_api_rest.Repositories.PublicacionRepository;
import com.sistema.blog.sistema_blog_spring_boot_api_rest.dto.PublicacionDTO;
import com.sistema.blog.sistema_blog_spring_boot_api_rest.dto.PublicacionResponse;

@Service
public class PublicacionServiceImpl implements PublicacionService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
        Publicacion publicacion = mapearEntidad(publicacionDTO);

        Publicacion nuevPublicacion = publicacionRepository.save(publicacion);

        PublicacionDTO publicacionResponse = mapearDTO(nuevPublicacion);

        return publicacionResponse;
    }

    @Override
    public PublicacionResponse obtenerTodasLPublicaciones(int numeroDePagina, int registrosPorPagina,
            String ordenarPorColumna, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPorColumna).ascending()
                : Sort.by(ordenarPorColumna).descending();
        Pageable pageable = PageRequest.of(numeroDePagina, registrosPorPagina, sort);

        Page<Publicacion> publicaciones = publicacionRepository.findAll(pageable);

        List<Publicacion> listaDePublicaciones = publicaciones.getContent();
        List<PublicacionDTO> contenido = listaDePublicaciones.stream()
                .map(publicacion -> mapearDTO(publicacion)).collect(Collectors.toList());

        PublicacionResponse publicacionResponse = new PublicacionResponse();
        publicacionResponse.setContenido(contenido);
        publicacionResponse.setCurrentPage(publicaciones.getNumber());
        publicacionResponse.setRecordsPerPage(publicaciones.getSize());
        publicacionResponse.setTotalRecords(publicaciones.getTotalElements());
        publicacionResponse.setTotalPages(publicaciones.getTotalPages());
        publicacionResponse.setLastPage(publicaciones.isLast());

        return publicacionResponse;

    }

    @Override
    public PublicacionDTO obtenerPublicacionPorId(Long id) {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));

        return mapearDTO(publicacion);
    }

    @Override
    public PublicacionDTO actualizPublicacion(PublicacionDTO publicacionDTO, Long id) {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));

        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setContenido(publicacionDTO.getContenido());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());

        Publicacion publicacionActualizada = publicacionRepository.save(publicacion);

        return mapearDTO(publicacionActualizada);
    }

    @Override
    public void eliminarPublicacion(Long id) {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));

        publicacionRepository.delete(publicacion);
    }

    private PublicacionDTO mapearDTO(Publicacion publicacion) {
        PublicacionDTO publicacionDTO = modelMapper.map(publicacion, PublicacionDTO.class);

        return publicacionDTO;
    }

    private Publicacion mapearEntidad(PublicacionDTO publicacionDTO) {
        Publicacion publicacion = modelMapper.map(publicacionDTO, Publicacion.class);

        return publicacion;
    }    
}
