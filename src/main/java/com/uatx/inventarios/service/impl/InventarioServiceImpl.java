package com.uatx.inventarios.service.impl;

import com.uatx.inventarios.dto.AltaInventarioDTO;
import com.uatx.inventarios.dto.BajaInventarioDTO;
import com.uatx.inventarios.dto.ProductoDTO;
import com.uatx.inventarios.exceptions.BusinessException;
import com.uatx.inventarios.model.AltaInventario;
import com.uatx.inventarios.model.BajaInventario;
import com.uatx.inventarios.model.Imagen;
import com.uatx.inventarios.model.Producto;
import com.uatx.inventarios.repository.AltaInventarioRepository;
import com.uatx.inventarios.repository.BajaInventarioRepository;
import com.uatx.inventarios.repository.ProductoRepository;
import com.uatx.inventarios.service.InventarioService;
import com.uatx.inventarios.service.ProductoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class InventarioServiceImpl implements InventarioService {

    @Autowired
    private AltaInventarioRepository altaInventarioRepository;

    @Autowired
    private BajaInventarioRepository bajaInventarioRepository;

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


        Producto producto = productoRepository.findProductoIdFetchImagen(altaInventarioDTO.getProducto().getId());

        altaInventario.setProducto(productoRepository.findProductoIdFetchImagen(producto.getId()));
        producto.setStock(altaInventario.getCantidad()+producto.getStock());
        //System.out.println(altaInventario.getProducto());
        altaInventarioRepository.save(altaInventario);
        return altaInventario.getId();
    }

    @Override
    @Transactional
    public Long storeBajaInventario(BajaInventarioDTO bajaInventarioDTO) {
        BajaInventario bajaInventario = modelMapper.map(bajaInventarioDTO, BajaInventario.class);
        bajaInventario.setCantidad(bajaInventarioDTO.getCantidad());
        bajaInventario.setFecha(new Date());



        Producto producto = productoRepository.findProductoIdFetchImagen(bajaInventarioDTO.getProducto().getId());

        bajaInventario.setProducto(productoRepository.findProductoIdFetchImagen(producto.getId()));
        producto.setStock(producto.getStock()-bajaInventario.getCantidad());
        //System.out.println(bajaInventario.getProducto());
        bajaInventarioRepository.save(bajaInventario);
        return bajaInventario.getId();
    }


    @Override
    public List<AltaInventarioDTO> findAltasByProducto(Long productoID) {

        List<AltaInventario> altaInventario = altaInventarioRepository.findAltaIdFetchProducto(productoID);
        List<AltaInventarioDTO> altaInventarioDTOS = trasnformToListDTO(altaInventario);
        System.out.println();

        return altaInventarioDTOS;
    }

    @Override
    public List<BajaInventarioDTO> findBajasByProducto(Long productoID) {

        List<BajaInventario> bajaInventario = bajaInventarioRepository.findBajaIdFetchProducto(productoID);
        List<BajaInventarioDTO> bajaInventarioDTOS = trasnformToListDTO1(bajaInventario);
        System.out.println();

        return bajaInventarioDTOS;

    }


    private List<AltaInventarioDTO> trasnformToListDTO(List<AltaInventario> altaInventarios) {
        List<AltaInventarioDTO> altaInventariosDTOs = new ArrayList<>();
        for (AltaInventario altaInventario : altaInventarios) {
            AltaInventarioDTO altaInventarioDTO = modelMapper.map(altaInventario, AltaInventarioDTO.class);
            altaInventariosDTOs.add(altaInventarioDTO);
        }
        return altaInventariosDTOs;
    }

    private List<BajaInventarioDTO> trasnformToListDTO1(List<BajaInventario> bajaInventarios) {
        List<BajaInventarioDTO> bajaInventariosDTOs = new ArrayList<>();
        for (BajaInventario bajaInventario : bajaInventarios) {
            BajaInventarioDTO bajaInventarioDTO = modelMapper.map(bajaInventario, BajaInventarioDTO.class);
            bajaInventariosDTOs.add(bajaInventarioDTO);
        }
        return bajaInventariosDTOs;
    }



    }
