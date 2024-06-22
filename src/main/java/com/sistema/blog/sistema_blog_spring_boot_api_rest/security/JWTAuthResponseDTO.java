package com.sistema.blog.sistema_blog_spring_boot_api_rest.security;

public class JWTAuthResponseDTO {

    private String tokenDeAccesso;
    private String tipoDeToken = "Bearer";

    public JWTAuthResponseDTO(String tokenDeAccesso, String tipoDeToken) {
        this.tokenDeAccesso = tokenDeAccesso;
        this.tipoDeToken = tipoDeToken;
    }

    public JWTAuthResponseDTO(String tokenDeAccesso) {
        this.tokenDeAccesso = tokenDeAccesso;
    }

    public String getTokenDeAccesso() {
        return tokenDeAccesso;
    }

    public void setTokenDeAccesso(String tokenDeAccesso) {
        this.tokenDeAccesso = tokenDeAccesso;
    }

    public String getTipoDeToken() {
        return tipoDeToken;
    }

    public void setTipoDeToken(String tipoDeToken) {
        this.tipoDeToken = tipoDeToken;
    }

}
