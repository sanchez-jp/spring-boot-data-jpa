package com.bolsadeideas.springboot.app.models.service;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.entity.Factura;
import com.bolsadeideas.springboot.app.models.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


/**
 * Interfaz basada en el patrón de diseño "fachada", un único punto de acceso hacia distintos DAO o repositorios.
 * Dentro de un clase Service podemos operar con distintos DAO y evita tener que acceder de forma directa a los DAO
 * dentro de los controladores.
 * <p>
 * Por cada método de la clase DAO habrá un método en la clase service.
 */
public interface IClienteService {
    /**
     * Realiza un listado de todos los clientes de la tabla de la base de datos
     *
     * @return lista de clientes
     */
    List findAll();

    /**
     * Devuelve una página de todos los clientes de la tabla de la base de datos
     *
     * @param pageable paginable
     * @return página de clientes
     */
    Page findAll(Pageable pageable);

    /**
     * Realiza la búsqueda de un cliente por su id
     *
     * @param id el identificador del cliente buscado
     * @return el Cliente encontrado
     */
    Cliente findOne(Long id);

    /**
     * Obtiene un cliente con todas sus facturas (relacion con la tabla)
     * Evita la realización de colsultas perezosas (lazy) múltiples en la vista
     *
     * @param id identificador del cliente a obtener
     * @return el cliente buscado
     */
    Cliente fetchByIdWithFacturas(Long id);

    /**
     * Guarda los datos de un clienmte en la tabla de la base de datos
     *
     * @param cliente el cliente a almacenar en la base de datos
     */
    void save(Cliente cliente);

    /**
     * Elimina un cliente de la base de datos
     *
     * @param id identificador del cliente a eliminar
     */
    void delete(Long id);

    /**
     * Realiza la búsqueda de productos por el nombre
     *
     * @param term filtro de búsqueda para el nombre
     * @return lista de productos con nombres coincidentes con el filtro de búsqueda
     */
    List<Producto> findByNombre(String term);

    /**
     * Guarda una factura
     *
     * @param factura la factura a guardar
     */
    void saveFactura(Factura factura);

    /**
     * Obtiene un producto mediante su identificador
     *
     * @param id el identificador del producto
     * @return el producto buscado
     */
    Producto findProductoById(Long id);

    /**
     * Obtiene una factura mediante su identificador
     *
     * @param id identificador de la factura
     * @return la factura buscada
     */
    Factura findFacturaById(Long id);

    /**
     * Elimina una factura
     *
     * @param id identificador de la factura a eliminar
     */
    void deleteFactura(long id);

    /**
     * Obtiene una factura con todas sus relaciones entre tablas
     * Evita la realización de colsultas perezosas (lazy) múltiples en la vista
     *
     * @param id identificador de la facura a obtener
     * @return la factura buscada
     */
    Factura fetchByIdWithClienteWithItemFacturaWithProducto(Long id);
}
