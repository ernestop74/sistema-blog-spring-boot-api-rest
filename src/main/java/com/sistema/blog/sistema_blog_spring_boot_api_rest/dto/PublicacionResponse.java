package com.sistema.blog.sistema_blog_spring_boot_api_rest.dto;

import java.util.List;

public class PublicacionResponse {

    private List<PublicacionDTO> contenido;
    private int currentPage;
    private int recordsPerPage;
    private Long totalRecords;
    private int totalPages;
    private boolean lastPage;

    public List<PublicacionDTO> getContenido() {
        return contenido;
    }
    public void setContenido(List<PublicacionDTO> contenido) {
        this.contenido = contenido;
    }
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getRecordsPerPage() {
        return recordsPerPage;
    }
    public void setRecordsPerPage(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }
    public Long getTotalRecords() {
        return totalRecords;
    }
    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }
    public int getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    public boolean isLastPage() {
        return lastPage;
    }
    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }
    public PublicacionResponse() {
    }
    
}
