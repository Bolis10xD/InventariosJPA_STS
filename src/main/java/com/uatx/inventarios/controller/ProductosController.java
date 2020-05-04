package com.uatx.inventarios.controller;

import com.uatx.inventarios.dto.ProductoDTO;
import com.uatx.inventarios.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductosController {


    @Autowired
    private ProductoService productoService;

    @PostMapping("/guardar")
    @ResponseBody
    public Long guardarProducto(@RequestBody ProductoDTO productoDTO) {
        return productoService.store(productoDTO);
    }

    @GetMapping("/all")
    @ResponseBody
    public List<ProductoDTO> findAll() {
        return productoService.findAll();
    }

    @GetMapping("/all/for-table")
    @ResponseBody
    public List<ProductoDTO> findAllWithImg() {
        return productoService.findProdWithImage();
    }

    @GetMapping("/find/by-name")
    @ResponseBody
    public List<ProductoDTO> findByName(@RequestParam String nombre) {
        return productoService.findByName(nombre);
    }

    @GetMapping("/delete/{productoId}")
    @ResponseBody
    public String findByName(@PathVariable Long productoId) {
        return productoService.delete(productoId);
    }

    @GetMapping("/page/nuevo-producto")
    public String redirectAltaProd() {
        return "nuevo-produto";
    }

    @GetMapping("/page/productos")
    public String redirectConsultaProd() {
        return "consulta-productos";
    }
}
