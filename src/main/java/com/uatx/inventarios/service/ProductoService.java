package com.uatx.inventarios.service;

import com.uatx.inventarios.dto.ProductoDTO;

import java.util.List;

public interface ProductoService {

    Long store (ProductoDTO productoDTO);
    List<ProductoDTO> findAll();
    List<ProductoDTO> findByName(String nombre);
    String delete(Long productoId);
    List<ProductoDTO> findProdWithImage();

}
