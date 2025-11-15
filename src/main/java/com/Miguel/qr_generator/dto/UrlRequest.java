package com.Miguel.qr_generator.dto;

// Esta clase es un simple "POJO" (Plain Old Java Object)
// Sirve para que Spring convierta automáticamente el JSON
// de la petición en un objeto Java.
public class UrlRequest {

    private String url;

    // Spring necesita los getters para poder leer los datos
    // y un constructor vacío o setters para crear el objeto.
    // Un getter simple es suficiente por ahora.

    public String getUrl() {
        return url;
    }

    // (Opcional, pero buena práctica) un setter
    public void setUrl(String url) {
        this.url = url;
    }
}