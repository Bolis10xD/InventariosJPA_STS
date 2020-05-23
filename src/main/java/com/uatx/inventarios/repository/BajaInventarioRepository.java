package com.uatx.inventarios.repository;

import com.uatx.inventarios.model.AltaInventario;
import com.uatx.inventarios.model.BajaInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BajaInventarioRepository extends JpaRepository<BajaInventario,Long> {

    @Query("SELECT a FROM BajaInventario a JOIN FETCH a.producto where a.producto.id = :ID")
    List<BajaInventario> findBajaIdFetchProducto(Long ID);

}
