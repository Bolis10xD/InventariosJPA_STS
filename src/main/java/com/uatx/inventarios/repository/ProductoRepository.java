package com.uatx.inventarios.repository;

import com.uatx.inventarios.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("SELECT p FROM Producto p where lower(p.nombre) like lower(CONCAT('%',:nombre,'%'))")
    List<Producto> findProductosNombreContaining(String nombre);

    @Query("SELECT p FROM Producto p JOIN FETCH p.imagen")
    List<Producto> findProductosFetchImagen();
}
