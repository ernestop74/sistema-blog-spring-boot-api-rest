package com.sistema.blog.sistema_blog_spring_boot_api_rest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.blog.sistema_blog_spring_boot_api_rest.Entities.Publicacion;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {

}
