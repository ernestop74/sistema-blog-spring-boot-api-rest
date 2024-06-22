package com.sistema.blog.sistema_blog_spring_boot_api_rest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.blog.sistema_blog_spring_boot_api_rest.Services.UsuarioService;
import com.sistema.blog.sistema_blog_spring_boot_api_rest.dto.UsuarioDTO;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {


    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping
    public List<UsuarioDTO> listarPublicaciones(){
        return usuarioService.obtenerUsuarios();
    }

}
