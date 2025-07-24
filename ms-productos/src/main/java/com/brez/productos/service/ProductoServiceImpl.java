package com.brez.productos.service;

import com.brez.productos.adapter.ProductoDBAdapter;
import com.brez.productos.dto.ProductoDTO;
import com.brez.productos.entity.Producto;
import com.brez.productos.entity.ProductoEntity;
import com.brez.productos.mapper.ProductoMapper;
import com.brez.productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoDBAdapter productoQueryAdapter;
    @Autowired
    private ProductoRepository repository;

    private ProductoEntity toEntity(Producto productoDto) {
        ProductoEntity entity = new ProductoEntity();
        entity.setNombre(productoDto.getNombre());
        entity.setDescripcion(productoDto.getDescripcion());
        entity.setPrecio(productoDto.getPrecio());
        entity.setStock(productoDto.getStock());
        return entity;
    }
    @Override
    public ProductoDTO crearProducto(Producto ProductoDTO) {
        Producto producto = new Producto();
        producto.setNombre(ProductoDTO.getNombre());
        producto.setDescripcion(ProductoDTO.getDescripcion());
        producto.setPrecio(ProductoDTO.getPrecio());
        producto.setStock(ProductoDTO.getStock()); // <<<< Asegúrate que este set esté presente

        Producto guardado = repository.save(producto);

        ProductoDTO dto = new ProductoDTO();
        dto.setId(guardado.getId());
        dto.setNombre(guardado.getNombre());
        dto.setDescripcion(guardado.getDescripcion());
        dto.setPrecio(guardado.getPrecio());
        dto.setStock(guardado.getStock());

        return dto;
    }

    @Override
    public ProductoDTO obtenerProductoPorId(Long id) {
        Producto producto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        return ProductoMapper.toDTO(producto);
    }

    public ProductoServiceImpl(ProductoDBAdapter productoQueryAdapter) {
        this.productoQueryAdapter = productoQueryAdapter;
    }

    @Override
    public List<ProductoDTO> listarProductos() {
        return productoQueryAdapter.obtenerTodosLosProductos();
    }
    @Override
    public Optional<ProductoDTO> buscarProductoPorId(Long id) {
        try {
            ProductoDTO producto = productoQueryAdapter.obtenerProductoPorId(id); // suponiendo que retorna ProductoDTO
            if (producto != null && "0000".equals(producto.getCodeError())) {
                return Optional.of(producto);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
