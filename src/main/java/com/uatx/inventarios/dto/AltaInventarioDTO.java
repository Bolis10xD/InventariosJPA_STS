package com.uatx.inventarios.dto;

import com.uatx.inventarios.model.Producto;

import java.util.Date;

public class AltaInventarioDTO {

    private Long id;
    private Date fecha;
    private Double cantidad;
    private ProductoDTO producto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }
}
