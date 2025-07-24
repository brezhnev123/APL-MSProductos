package com.brez.productos.service;

import com.brez.productos.dto.ProductoDTO;
import com.brez.productos.entity.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoService {
    ProductoDTO crearProducto(Producto ProductoDTO);
    ProductoDTO obtenerProductoPorId(Long id);
    List<ProductoDTO> listarProductos();
    public Optional<ProductoDTO> buscarProductoPorId(Long id);
}
