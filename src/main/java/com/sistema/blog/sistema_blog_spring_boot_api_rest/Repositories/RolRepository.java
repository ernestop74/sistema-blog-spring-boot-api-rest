package com.sistema.blog.sistema_blog_spring_boot_api_rest.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.blog.sistema_blog_spring_boot_api_rest.Entities.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {

    public Optional<Rol> findByNombre(String nombre);
}
