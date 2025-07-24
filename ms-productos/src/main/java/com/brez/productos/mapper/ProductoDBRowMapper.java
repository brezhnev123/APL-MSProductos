package com.brez.productos.mapper;

import com.brez.productos.dto.ProductoDBResponseDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDBRowMapper implements RowMapper<ProductoDBResponseDTO> {

    @Override
    public ProductoDBResponseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductoDBResponseDTO dto = new ProductoDBResponseDTO();
        dto.setId(rs.getLong("p_id"));
        dto.setNombre(rs.getString("p_nombre"));
        dto.setDescripcion(rs.getString("p_descripcion"));
        dto.setPrecio(rs.getDouble("p_precio"));
        dto.setStock(rs.getInt("p_stock"));
        dto.setCodeError(rs.getString("code_error"));
        dto.setParamMensaje(rs.getString("param_mensaje"));
        return dto;
    }
}
