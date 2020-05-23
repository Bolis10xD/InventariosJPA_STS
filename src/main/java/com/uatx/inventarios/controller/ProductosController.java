package com.uatx.inventarios.controller;

import com.uatx.inventarios.dto.AltaInventarioDTO;
import com.uatx.inventarios.dto.BajaInventarioDTO;
import com.uatx.inventarios.dto.ImagenDTO;
import com.uatx.inventarios.dto.ProductoDTO;
import com.uatx.inventarios.model.Producto;
import com.uatx.inventarios.repository.ProductoRepository;
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
    private ProductoRepository productoRepository;

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private ProductoService productoService;


    @PostMapping("/realizarAlta")
    @ResponseBody
    public Long realizarAlta(@RequestBody AltaInventarioDTO altaInventarioDTO) {
        return inventarioService.storeAltaInventario(altaInventarioDTO);
    }

    @PostMapping("/realizarBaja")
    @ResponseBody
    public Long realizarBaja(@RequestBody BajaInventarioDTO bajaInventarioDTO) {
        return inventarioService.storeBajaInventario(bajaInventarioDTO);
    }

    @GetMapping("/findBajasById/{id}")
    public String infoBaja(@PathVariable(value = "id") String id, Map<String, Object> model, RedirectAttributes flash){

        List<BajaInventarioDTO> bajaInventarioDTOS = inventarioService.findBajasByProducto(Long.parseLong(id));

            model.put("DTO",bajaInventarioDTOS);
            model.put("id-btn",id);

            return "detalle-bajas";


    }

    @GetMapping("/findAltasById/{id}")
    public String infoAlta(@PathVariable(value = "id") String id, Map<String, Object> model, RedirectAttributes flash){

        List<AltaInventarioDTO> altaInventarioDTOS = inventarioService.findAltasByProducto(Long.parseLong(id));

        model.put("DTO",altaInventarioDTOS);

        return "detalle-altas";
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


    @GetMapping("/buscarAltasbyId/{id}")
    @ResponseBody
    public List<AltaInventarioDTO> buscarAltasById(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash){
        return inventarioService.findAltasByProducto(id);
    };

    @GetMapping("/alta/{id}")
    public String altaById(@PathVariable(value = "id") String id, Map<String, Object> model, RedirectAttributes flash){
        ProductoDTO productoDTO = productoService.findByIdWithImage(Long.parseLong(id));
        ImagenDTO imagenDTO = productoDTO.getImagen();




        if (productoDTO == null) {
            flash.addFlashAttribute("error", "El Producto no existe en la base de datos");
            return "redirect:/consulta-productos";
        }

        model.put("producto", productoDTO);
        model.put("base64",imagenDTO.getDataBase64());
        model.put("type",imagenDTO.getMimeType());
        model.put("Altain","Detalle de altas del producto");
        model.put("titulo", "Detalle del Producto: " + productoDTO.getNombre());
        return "alta-producto";
    }

    @GetMapping("/baja/{id}")
    public String bajaById(@PathVariable(value = "id") String id, Map<String, Object> model, RedirectAttributes flash){
        ProductoDTO productoDTO = productoService.findByIdWithImage(Long.parseLong(id));
        ImagenDTO imagenDTO = productoDTO.getImagen();

        if (productoDTO == null) {
            flash.addFlashAttribute("error", "El Producto no existe en la base de datos");
            return "redirect:/consulta-productos";
        }

        model.put("producto", productoDTO);
        model.put("base64",imagenDTO.getDataBase64());
        model.put("type",imagenDTO.getMimeType());
        model.put("Bajain","Detalle de bajas del producto");
        model.put("titulo", "Detalle del Producto: " + productoDTO.getNombre());
        return "baja-producto";
    }



    @GetMapping("/edit/{id}")
    public String editarProducto(@PathVariable(value = "id") String id, Map<String, Object> model, RedirectAttributes flash){

        ProductoDTO productoDTO = productoService.findByIdWithImage(Long.parseLong(id));

        model.put("productoDTO",productoDTO);

        return "editar-producto";
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
