package com.uatx.inventarios.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "baja_inventario")
@SequenceGenerator(name="baja_seq")
public class BajaInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "baja_seq")
    private Long id;

    @Column(name = "fecha", length = 50)
    private Date fecha;

    @Column(name = "cantidad", length = 50)
    private Double cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_imagen")
    private Producto producto;


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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
