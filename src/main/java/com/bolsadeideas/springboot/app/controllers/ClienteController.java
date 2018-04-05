package com.bolsadeideas.springboot.app.controllers;

import com.bolsadeideas.springboot.app.controllers.util.paginator.PageRender;
import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.service.IClienteService;
import com.bolsadeideas.springboot.app.models.service.IUploadFileService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.Map;

/**
 * Controlador encargado de gestionar las acciones de los clientes. Gestiona los clientes
 */
@Controller
@SessionAttributes("cliente") // Guarda en los atributos de la sesión el objeto cliente mapeado al formulario
public class ClienteController {

    @Autowired // Resuelve mediante inyección las dependencias de un bean de Spring (final real)
    //@Qualifier("clienteDaoJPA") // Identifica el bean concreto que se dedea utilizar
    private IClienteService clienteService; // Siempre se importa el tipo más genérico

    @Autowired // Inyecta el componente UploadFileService
    private IUploadFileService uploadFileService;

    protected final Log logger = LogFactory.getLog(this.getClass());

    /**
     * Indica el lugar de donde se deben obtener las imagenes de los clientes
     * Es una alternativa para cargar recursos diferente a la dispuesta en la clase MvcConfig.java
     *
     * @param filename el nombre del archivo que se desea obtener de los recursos
     * @return
     */
    @Secured("ROLE_USER") // Sólo los usuarios autenticados tienen acceso
    @GetMapping(value = "/uploads/{filename:.+}")
    // filename:.+ => Necesario para que Spring no trunque la extensión del archivo
    public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

        Resource recurso = null;
        try {
            recurso = uploadFileService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                + recurso.getFilename() + "\"").body(recurso);
    }

    /**
     * Muestra información en detalle de un cliente
     *
     * @param id    identificador del cliente
     * @param model mapa de valores a ser representados en la vista
     * @param flash pasa mensajes a la vista para ser mostrados.
     * @return
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/ver/{id}")
    public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

        Cliente cliente = clienteService.fetchByIdWithFacturas(id); //clienteService.findOne(id);
        if (cliente == null) {
            flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
            return "redirect:/listar";
        }
        model.put("cliente", cliente); // Pasa el cliente a la vista
        model.put("titulo", "Detalle cliente: " + cliente.getNombre() + " " + cliente.getApellido()); // Pasa título a la vista

        return "ver";
    }

    /**
     * Muestra los datos de los clientes en la vista de tabla de clientes
     *
     * @param model Modelo de valores a ser representados en la vista
     * @return el nombre de la vista con la que se comunica el método
     */
    @RequestMapping(value = {"/listar", "/"}, method = RequestMethod.GET)
    // Por defecto el método es GET, no sería necesario de indicarlo de forma explícita
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
                         Authentication authentication,
                         HttpServletRequest request) {

        // Obtención de autenticación. Método 1
        if (authentication != null) {
            logger.info("Hola usuario autenticado, tu username es: ".concat(authentication.getName()));
        }

        // Obtención de autenticación. Método 2
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            logger.info("*(Forma estática) Hola usuario autenticado, tu username es: ".concat(auth.getName()));
        }

        // Validación de rol. Método 1. Uso de método hasRole implementado (forma programática)
        if (hasRole("ROLE_ADMIN")) {
            logger.info("Hola ".concat(auth.getName()).concat(". Tienes acceso."));
        } else {
            logger.info("Hola ".concat(auth.getName()).concat(". No tienes acceso."));
        }

        // Validación de rol. Método 2. Usando SecurityContextHolderAwareRequestWrapper
        SecurityContextHolderAwareRequestWrapper securityContext = new SecurityContextHolderAwareRequestWrapper(request, "");

        if (securityContext.isUserInRole("ROLE_ADMIN")) {
            logger.info("Forma usando SecurityContextHolderAwareRequestWrapper: Hola ".concat(auth.getName()).concat(". Tienes acceso."));
        } else {
            logger.info("Forma usando SecurityContextHolderAwareRequestWrapper: Hola ".concat(auth.getName()).concat(". No tienes acceso."));
        }

        // Validación de rol. Método 3. (más simple) Usando HttpServletRequest (request) de forma nativa
        if (request.isUserInRole("ROLE_ADMIN")) {
            logger.info("Forma usando HttpServletRequest: Hola ".concat(auth.getName()).concat(". Tienes acceso."));
        } else {
            logger.info("Forma usando HttpServletRequest: Hola ".concat(auth.getName()).concat(". No tienes acceso."));
        }

        // PageRequest(page, size) => page: número de página actual; size:número de elementos por página
        Pageable pageRequest = PageRequest.of(page, 5);

        Page<Cliente> clientes = clienteService.findAll(pageRequest);

        PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);

        model.addAttribute("titulo", "Listado de clientes");
        model.addAttribute("clientes", clientes); // Retornamos clientes con paginación
        model.addAttribute("page", pageRender);
        return "listar";
    }

    /**
     * Valida el rol del usuario
     *
     * @param role nombre del rol del usuario
     * @return TRUE si se verifica el rol del usuario, FALSE en caso comtrario
     */
    private boolean hasRole(String role) {
        SecurityContext context = SecurityContextHolder.getContext();

        if (context == null) {
            return false;
        }

        Authentication auth = context.getAuthentication();

        if (auth == null) {
            return false;
        }

        // Coleccion de roles (authorities) -> Colección <Cualquier objeto que implemente la interfaz GrantedAuthority>
        // Almacena los roles del usuario autenticado
        // Toda clase rol o que represente un rol en Spring Security tiene que implementar la interfaz GrantedAuthority
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

        /*for (GrantedAuthority authority : authorities) {
            if (role.equals(authority.getAuthority())) {
                logger.info("Hola usuario ".concat(auth.getName()).concat(" tu rol es: ").concat(authority.getAuthority()));
                return true;
            }
        }

        return false;
        */

        /* El método contains(GrantedAuthority) retorna un booleano, TRUE o FALSE, si contiene o no el elemento en la
         * colección */
        return authorities.contains(new SimpleGrantedAuthority(role));
    }

    /**
     * Muestra los datos de un cliente en el formulario de la vista
     *
     * @param model mapa de valores para el modelo de la vista
     * @return el nombre de la vista con la que se comunica el método
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/form") // Este método es de comunicación bidireccional
    public String crear(Map<String, Object> model) {

        Cliente cliente = new Cliente();
        model.put("cliente", cliente);
        model.put("titulo", "Formulario de Cliente");
        return "form";
    }

    /**
     * PathVariable inyecta el valor del parámetro de la ruta
     *
     * @param id    identificador del cliente a editar
     * @param model mapa de valores a ser representados en la vista
     * @return el nombre de la vista a mostrar
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/form/{id}") // Inserción de parámetro en ruta mediante el patrón WildCard
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

        Cliente cliente = null;

        if (id > 0) {
            cliente = clienteService.findOne(id);

            if (cliente == null) {
                flash.addFlashAttribute("error", "El id del cliente no existe en la BD");
                return "redirect:/listar"; // Redirige al listar en caso de que no exista el id
            }
        } else {
            flash.addFlashAttribute("error", "El id del cliente no puede ser cero");
            return "redirect:/listar"; // Redirige al listar en caso de que no exista el id
        }
        model.put("cliente", cliente);
        model.put("titulo", "Editar Cliente");
        return "/form";
    }

    /**
     * Guarda los datos de un cliente en la base de datos
     *
     * @param cliente el cliente a guardar en la BD
     * @return el nombre de la vista a mostrar tras guardar los datos de cliente (redirección)
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model, @RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {

        if (result.hasErrors()) {
            // Añadimos nuevamente el título aquí para que no desaparezca al refrescar (validar) el formulario
            model.addAttribute("titulo", "Formulario de cliente");
            return "form";
        }

        // Si la imágen no está vacía guardar en la ruta especificada
        if (!foto.isEmpty()) {

            if (cliente.getId() != null && cliente.getId() > 0 && cliente.getFoto() != null && cliente.getFoto().length() > 0) {

                uploadFileService.delete(cliente.getFoto());
            }

            String uniqueFilename = null;
            try {
                uniqueFilename = uploadFileService.copy(foto);
            } catch (IOException e) {
                e.printStackTrace();
            }

            flash.addFlashAttribute("info", "'" + uniqueFilename + "' subida correctamente"); // Paso de mensaje al flash

            cliente.setFoto(uniqueFilename); // Paso de nombre de foto al cliente
        }
        // Si el id no es nulo, el cliente guardado es un cliente editado, si es nulo, es un nuevo cliente
        String mensajeFlash = (cliente.getId() != null) ? "¡Cliente editado con éxito!" : "¡Cliente guardado con éxito!";

        clienteService.save(cliente);
        status.setComplete(); // Elimina el objeto cliente de la sesión
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:/listar";
    }

    /**
     * Elimina un cliente
     *
     * @param id    el identificador del cliente a eliminar
     * @param flash mensaje de redirección
     * @return url de la vista a la que se redirige
     */
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            Cliente cliente = clienteService.findOne(id); // Se obtiene previamente el cliente para poder eliminar su foto de los recursos
            clienteService.delete(id);
            flash.addFlashAttribute("success", "¡Cliente eliminado con éxito!");

            if (uploadFileService.delete(cliente.getFoto())) {
                flash.addFlashAttribute("info", "Imagen " + cliente.getFoto() + " eliminada con éxito");
            }
        }
        return "redirect:/listar";
    }

}
