package com.uatx.inventarios.repository;

import com.uatx.inventarios.model.BajaInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BajaInventarioRepository extends JpaRepository<BajaInventario,Long> {

}
