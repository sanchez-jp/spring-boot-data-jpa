package com.bolsadeideas.springboot.app.controllers;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.entity.Factura;
import com.bolsadeideas.springboot.app.models.entity.ItemFactura;
import com.bolsadeideas.springboot.app.models.entity.Producto;
import com.bolsadeideas.springboot.app.models.service.IClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Controlador encargado de gestionar las acciones de las facturas. gestiona las facturas
 */
@Secured("ROLE_ADMIN") // Todos sus mapeos obtienen este permiso de acceso
@Controller
@SessionAttributes("factura") // Guarda en los atributos de la sesión el objeto factura mapeado al formulario
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    private IClienteService clienteService; // Inyección de ClienteService

    /**
     * Logger para hacer debug por consola
     */
    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable(value = "id") Long id,
                      Model model,
                      RedirectAttributes flash) {

        Factura factura = clienteService.fetchByIdWithClienteWithItemFacturaWithProducto(id); //clienteService.findFacyuraById(id);

        if (factura == null) {
            flash.addAttribute("error", "La factura no existe en la base de datos");
            //flash.addFlashAttribute();
            return "redirect:/listar";
        }
        model.addAttribute("factura", factura);
        model.addAttribute("titulo", "Factura: ".concat(factura.getDescripcion()));
        return "factura/ver";
    }

    /**
     * Muestra datos de la factura en el formulario de la vista para crear/modificar facturas.
     *
     * @param clienteId el identificador del cliente al que está asignada la factura
     * @param model     mapa de valores a ser representado en la vista
     * @param flash
     * @return
     */
    @GetMapping("/form/{clienteId}")
    public String crear(@PathVariable(value = "clienteId") Long clienteId, Map<String, Object> model, RedirectAttributes flash) {

        Cliente cliente = clienteService.findOne(clienteId);
        if (cliente == null) { // Si el cliente es nulo
            flash.addFlashAttribute("error", "El cliente no existe en la base de datos"); // lanza mensaje de error
            return "redirect:/listar"; // Redirige nuevamente a la vista de lista
        }

        Factura factura = new Factura();
        factura.setCliente(cliente);

        model.put("factura", factura);
        model.put("titulo", "Crear Factura");

        return "factura/form";
    }

    @GetMapping(value = "/cargar-productos/{term}", produces = {"application/json"})
    /*
     * La notación @ResponseBody suprime el cargar una vista de Thymeleaf y toma el resultado convertido a JSON y la
     * guarda dentro del body de la respuesta.
     */
    public @ResponseBody
    List<Producto> cargarProductos(@PathVariable String term) {
        return clienteService.findByNombre(term);
    }

    /**
     * Guarda todas las líneas de una factura
     *
     * @param factura  la factura a guardar
     * @param itemId   array de identificadores de líneas de factura
     * @param cantidad array de cantidades de cada producto de línea de factura
     * @param flash    almacena los mensajes que se envian a la vista
     * @return la url de la vista
     */
    @PostMapping("/form")
    public String guardar(@Valid Factura factura,
                          BindingResult result,
                          Model model,
                          @RequestParam(name = "item_id[]", required = false) Long[] itemId,
                          @RequestParam(name = "cantidad[]", required = false) Integer[] cantidad,
                          RedirectAttributes flash,
                          SessionStatus status) {

        // Si los campos de la factura contienen algun error, lanza un mensaje
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Crear Factura");
            return "factura/form";
        }

        // Si la factura no contiene líneas lanza un mensaje de error
        if (itemId == null || itemId.length == 0) {
            model.addAttribute("titulo", "Crear Factura");
            model.addAttribute("error", "Error: ¡La factura NO puede no tener líneas!");
            return "factura/form";
        }

        for (int i = 0; i < itemId.length; i++) {
            Producto producto = clienteService.findProductoById(itemId[i]);

            ItemFactura linea = new ItemFactura();
            linea.setCantidad(cantidad[i]);
            linea.setProducto(producto);

            factura.addItem(linea);

            log.info("ID: " + itemId[i] + ", cantidad: " + cantidad[i]);
        }

        clienteService.saveFactura(factura);
        status.setComplete(); // Establece la variable de sesión del formulario como finalizada para borrar los datos que contiene

        flash.addAttribute("succes", "Factura creada con éxito");

        return "redirect:/ver/" + factura.getCliente().getId();
    }

    /**
     * Elimina una factura
     *
     * @param id    identificador de la factura a eliminar
     * @param flash contenedor para el mensaje de redirección
     * @return la vista a la que se redirige
     */
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

        Factura factura = clienteService.findFacturaById(id);

        if (factura != null) {
            clienteService.deleteFactura(id);
            flash.addFlashAttribute("success", "Factura eliminada con éxito");
            return "redirect:/ver/" + factura.getCliente().getId();
        }
        flash.addFlashAttribute("error", "La factura no existe en la base de datos, no se pudo eliminar");

        return "redirect:/listar";
    }
}
