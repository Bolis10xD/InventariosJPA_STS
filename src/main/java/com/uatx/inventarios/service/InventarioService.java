package com.uatx.inventarios.service;

import com.uatx.inventarios.dto.AltaInventarioDTO;
import com.uatx.inventarios.dto.BajaInventarioDTO;
import com.uatx.inventarios.dto.ProductoDTO;

import java.util.List;

public interface InventarioService {

    Long storeAltaInventario(AltaInventarioDTO altaInventarioDTO);
    Long storeBajaInventario(BajaInventarioDTO bajaInventarioDTO, Long productoID);
    List<ProductoDTO> findAltasByProducto(Long productoID);
    List<ProductoDTO> findBajasByProducto(Long productoID);


}
