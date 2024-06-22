package com.sistema.blog.sistema_blog_spring_boot_api_rest.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.blog.sistema_blog_spring_boot_api_rest.Entities.Usuario;
import com.sistema.blog.sistema_blog_spring_boot_api_rest.Repositories.UsuarioRepository;
import com.sistema.blog.sistema_blog_spring_boot_api_rest.dto.UsuarioDTO;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioDTO> obtenerUsuarios() {

        List<Usuario> usuarios = usuarioRepository.findAll();

        List<UsuarioDTO> listadoUsuarios = usuarios.stream()
                .map(usuario -> mapearDTO(usuario)).collect(Collectors.toList());

        return listadoUsuarios;
    }

    private UsuarioDTO mapearDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);

        return usuarioDTO;
    }

    /*private Usuario mapearEntidad(UsuarioDTO usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);

        return usuario;
    }     */   

}
