package com.brez.productos.mapper;

import com.brez.productos.dto.ProductoDTO;
import com.brez.productos.entity.Producto;

public class ProductoMapper {

    public static ProductoDTO toDTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock()
        );
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        return dto;
    }

    public static Producto toEntity(Producto dto) {
        Producto producto = new Producto();
        producto.setId(dto.getId());
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        return producto;
    }
}
