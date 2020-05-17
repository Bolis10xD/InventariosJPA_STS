package com.uatx.inventarios.repository;

import com.uatx.inventarios.model.AltaInventario;
import com.uatx.inventarios.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AltaInventarioRepository extends JpaRepository<AltaInventario,Long> {

    @Query("SELECT a FROM AltaInventario a JOIN FETCH a.producto where a.producto.id = :ID")
    List<AltaInventario> findAltaIdFetchProducto(Long ID);
}
