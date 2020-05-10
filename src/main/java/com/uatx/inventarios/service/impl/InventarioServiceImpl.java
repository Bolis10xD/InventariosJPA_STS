package com.uatx.inventarios.service.impl;

import com.uatx.inventarios.dto.AltaInventarioDTO;
import com.uatx.inventarios.dto.BajaInventarioDTO;
import com.uatx.inventarios.dto.ProductoDTO;
import com.uatx.inventarios.exceptions.BusinessException;
import com.uatx.inventarios.model.AltaInventario;
import com.uatx.inventarios.model.Imagen;
import com.uatx.inventarios.model.Producto;
import com.uatx.inventarios.repository.AltaInventarioRepository;
import com.uatx.inventarios.repository.ProductoRepository;
import com.uatx.inventarios.service.InventarioService;
import com.uatx.inventarios.service.ProductoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class InventarioServiceImpl implements InventarioService {

    @Autowired
    private AltaInventarioRepository altaInventarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public Long storeAltaInventario(AltaInventarioDTO altaInventarioDTO) {

        AltaInventario altaInventario = modelMapper.map(altaInventarioDTO, AltaInventario.class);
        altaInventario.setCantidad(altaInventarioDTO.getCantidad());
        altaInventario.setFecha(new Date());



        Producto producto = modelMapper.map(altaInventarioDTO.getProductoDTO(),Producto.class);
        producto = productoRepository.findProductoIdFetchImagen(altaInventarioDTO.getProductoDTO().getId());
        //System.out.println(producto.getId());
        altaInventario.setProducto(productoRepository.findProductoIdFetchImagen(producto.getId()));
        producto.setStock(altaInventario.getCantidad()+producto.getStock());
        //System.out.println(altaInventario.getProducto());
        altaInventarioRepository.save(altaInventario);
        return altaInventario.getId();
    }

    @Override
    public Long storeBajaInventario(BajaInventarioDTO bajaInventarioDTO, Long productoID) {
        return null;
    }

    @Override
    public List<ProductoDTO> findAltasByProducto(Long productoID) {
        return null;
    }

    @Override
    public List<ProductoDTO> findBajasByProducto(Long productoID) {
        return null;
    }
}
