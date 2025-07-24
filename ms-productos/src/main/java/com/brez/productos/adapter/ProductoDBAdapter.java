package com.brez.productos.adapter;

import com.brez.productos.dto.ProductoDTO;

import com.brez.productos.dto.ProductoRequestDTO;
import com.brez.productos.entity.Producto;
import com.brez.productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductoDBAdapter {

    private final JdbcTemplate jdbcTemplate;
    private final ProductoRepository productoRepository;

    @Value("${productos.db.getAllProducts:null}")
    private String getAllProductsQuery;

    @Value("${productos.db.get_producto_by_id:null}")
    private String getProductsById;

    @Autowired
    public ProductoDBAdapter(JdbcTemplate jdbcTemplate, ProductoRepository productoRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.productoRepository = productoRepository;
    }

    public List<ProductoDTO> obtenerTodosLosProductos() {
        String sql = getAllProductsQuery;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ProductoDTO producto = new ProductoDTO();
            producto.setId(rs.getLong("p_id"));
            producto.setNombre(rs.getString("p_nombre"));
            producto.setDescripcion(rs.getString("p_descripcion"));
            producto.setPrecio(rs.getDouble("p_precio"));
            producto.setStock(rs.getInt("p_stock"));
            producto.setCodeError(rs.getString("code_error"));
            producto.setParamMensaje(rs.getString("param_mensaje"));
            return producto;
        });
    }

    public ProductoDTO obtenerProductoPorId(Long id) {
        String sql = getProductsById;

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id.intValue()}, new RowMapper<ProductoDTO>() {
                @Override
                public ProductoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ProductoDTO producto = new ProductoDTO();
                    producto.setId(rs.getLong("p_id"));
                    producto.setNombre(rs.getString("p_nombre"));
                    producto.setDescripcion(rs.getString("p_descripcion"));
                    producto.setPrecio(rs.getDouble("p_precio"));
                    producto.setStock(rs.getInt("p_stock"));
                    producto.setCodeError(rs.getString("code_error"));
                    producto.setParamMensaje(rs.getString("param_mensaje"));
                    return producto;
                }
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    public ProductoDTO guardarProducto(ProductoRequestDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());

        Producto guardado = productoRepository.save(producto);

        ProductoDTO salida = new ProductoDTO(guardado.getId(), guardado.getNombre(), guardado.getDescripcion(), guardado.getPrecio(), guardado.getStock());
        salida.setId(guardado.getId());
        salida.setNombre(guardado.getNombre());
        salida.setDescripcion(guardado.getDescripcion());
        salida.setPrecio(guardado.getPrecio());
        salida.setStock(guardado.getStock());
        salida.setCodeError("0000");
        salida.setParamMensaje("Producto creado exitosamente");

        return salida;
    }
}
