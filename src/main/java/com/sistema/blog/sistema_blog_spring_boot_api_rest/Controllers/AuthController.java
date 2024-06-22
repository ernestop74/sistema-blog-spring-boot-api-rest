package com.sistema.blog.sistema_blog_spring_boot_api_rest.Controllers;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sistema.blog.sistema_blog_spring_boot_api_rest.Entities.Rol;
import com.sistema.blog.sistema_blog_spring_boot_api_rest.Entities.Usuario;
import com.sistema.blog.sistema_blog_spring_boot_api_rest.Repositories.RolRepository;
import com.sistema.blog.sistema_blog_spring_boot_api_rest.Repositories.UsuarioRepository;
import com.sistema.blog.sistema_blog_spring_boot_api_rest.dto.LoginDTO;
import com.sistema.blog.sistema_blog_spring_boot_api_rest.dto.RegistroDTO;
import com.sistema.blog.sistema_blog_spring_boot_api_rest.security.JWTAuthResponseDTO;
import com.sistema.blog.sistema_blog_spring_boot_api_rest.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired 
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired 
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/iniciarSesion")
    public ResponseEntity<JWTAuthResponseDTO> authenticateUser(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generarToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponseDTO(token));
        //return new ResponseEntity<>("Ha iniciado sesión correctamente", HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDTO registroDTO) {
        if(usuarioRepository.existsByUsername(registroDTO.getUsername())) {
            return new ResponseEntity<>("El nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
        }

        if(usuarioRepository.existsByEmail(registroDTO.getEmail())) {
            return new ResponseEntity<>("El email ya existe", HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(registroDTO.getNombre());
        usuario.setUsername(registroDTO.getUsername());
        usuario.setEmail(registroDTO.getEmail());
        usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));

        Rol roles = rolRepository.findByNombre("ADMIN").get();
        usuario.setRoles(Collections.singleton(roles));

        usuarioRepository.save(usuario);

        return new ResponseEntity<>("Usuario registrado de manera exitosa", HttpStatus.OK);
    }
}
