package com.brez.productos.dto;

import lombok.Data;

@Data
public class ProductoDBResponseDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String codeError;
    private String paramMensaje;
}
