package com.sistema.blog.sistema_blog_spring_boot_api_rest.Services;

import java.util.List;

import com.sistema.blog.sistema_blog_spring_boot_api_rest.dto.UsuarioDTO;

public interface UsuarioService {

    public List<UsuarioDTO> obtenerUsuarios();

}
