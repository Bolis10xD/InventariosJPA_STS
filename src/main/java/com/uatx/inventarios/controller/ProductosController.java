package com.uatx.inventarios.controller;

import com.uatx.inventarios.dto.AltaInventarioDTO;
import com.uatx.inventarios.dto.ImagenDTO;
import com.uatx.inventarios.dto.ProductoDTO;
import com.uatx.inventarios.model.Producto;
import com.uatx.inventarios.service.InventarioService;
import com.uatx.inventarios.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/productos")
public class ProductosController {

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private ProductoService productoService;

    @PostMapping("/realizarAlta")
    @ResponseBody
    public Long realizarAlta(@RequestBody AltaInventarioDTO altaInventarioDTO) {
        return inventarioService.storeAltaInventario(altaInventarioDTO);
    }

    @PostMapping("/guardar")
    @ResponseBody
    public Long guardarProducto(@RequestBody ProductoDTO productoDTO) {
        return productoService.store(productoDTO);
    }

    @GetMapping("/all")
    @ResponseBody
    public List<ProductoDTO> findAll() {
        return productoService.findAll();
    }

    @GetMapping("/all/for-table")
    @ResponseBody
    public List<ProductoDTO> findAllWithImg() {
        return productoService.findProdWithImage();
    }

    @GetMapping("find/productodto/{id}")
    @ResponseBody
    public ProductoDTO windProductoWithIdFechtImg(@PathVariable(value = "id") Long id){
        return productoService.findByIdWithImage(id);
    }

    @GetMapping("/alta/{id}")
    public String findById(@PathVariable(value = "id") String id, Map<String, Object> model, RedirectAttributes flash){
        ProductoDTO productoDTO = productoService.findByIdWithImage(Long.parseLong(id));
        ImagenDTO imagenDTO = productoDTO.getImagen();




        if (productoDTO == null) {
            flash.addFlashAttribute("error", "El Producto no existe en la base de datos");
            return "redirect:/consulta-productos";
        }

        model.put("producto", productoDTO);
        model.put("base64",imagenDTO.getDataBase64());
        model.put("type",imagenDTO.getMimeType());
        model.put("titulo", "Detalle del Producto: " + productoDTO.getNombre());
        return "alta-producto";
    }


    @GetMapping("/find/by-name")
    @ResponseBody
    public List<ProductoDTO> findByName(@RequestParam String nombre) {
        return productoService.findByName(nombre);
    }

    @GetMapping("/delete/{productoId}")
    @ResponseBody
    public String findByName(@PathVariable Long productoId) {
        return productoService.delete(productoId);
    }

    @GetMapping("/page/nuevo-producto")
    public String redirectAltaProd() {
        return "nuevo-produto";
    }

    @GetMapping("/page/productos")
    public String redirectConsultaProd() {
        return "consulta-productos";
    }
}
