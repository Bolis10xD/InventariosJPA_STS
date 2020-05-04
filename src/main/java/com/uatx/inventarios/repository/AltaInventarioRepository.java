package com.uatx.inventarios.repository;

import com.uatx.inventarios.model.AltaInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AltaInventarioRepository extends JpaRepository<AltaInventario,Long> {

}
