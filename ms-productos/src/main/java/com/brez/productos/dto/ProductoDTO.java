package com.brez.productos.dto;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String codeError;
    private String paramMensaje;

    public ProductoDTO(long pId, String pNombre, String pDescripcion, double pPrecio, int pStock) {
    }

    public ProductoDTO() {

    }

    public ProductoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ProductoDTO(
                rs.getLong("p_id"),
                rs.getString("p_nombre"),
                rs.getString("p_descripcion"),
                rs.getDouble("p_precio"),
                rs.getInt("p_stock")
        );
    }
}
