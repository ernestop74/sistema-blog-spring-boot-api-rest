package com.sistema.blog.sistema_blog_spring_boot_api_rest.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.blog.sistema_blog_spring_boot_api_rest.Entities.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    public List<Comentario> findByPublicacionId(Long publicacionId);

}
