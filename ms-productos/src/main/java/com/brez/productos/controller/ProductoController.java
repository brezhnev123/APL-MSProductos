package com.brez.productos.controller;

import com.brez.productos.dto.ProductoDTO;
import com.brez.productos.entity.Producto;
import com.brez.productos.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.*;

@RestController
//@RequestMapping("/api/v1/productos")
@RequestMapping(value = "${controller.properties.base-path}")
public class ProductoController {

    private final ProductoService service;
    private final ProductoService productoService;

    public ProductoController(ProductoService service, ProductoService productoService) {
        this.service = service;
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crear(@RequestBody Producto producto) {
        return ok(service.crearProducto(producto));
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listar() {
        return ok(productoService.listarProductos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<ProductoDTO> productoOpt = productoService.buscarProductoPorId(id);

        if (productoOpt.isPresent()) {
            return ResponseEntity.ok(productoOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
    }
}
