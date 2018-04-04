package com.bolsadeideas.springboot.app.models.service;

import com.bolsadeideas.springboot.app.models.dao.IClienteDao;
import com.bolsadeideas.springboot.app.models.dao.IFacturaDao;
import com.bolsadeideas.springboot.app.models.dao.IProductoDao;
import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.entity.Factura;
import com.bolsadeideas.springboot.app.models.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Clase basada en el patrón de diseño "fachada", un único punto de acceso hacia distintos DAO o repositorios.
 * Dentro de un clase Service podemos operar con distintos DAO y evita tener que acceder de forma directa a los DAO
 * dentro de los controladores.
 * <p>
 * Por cada método de la clase DAO habrá un método en la clase service.
 * <p>
 * Con la implementación de la clase Service no son necesarias las anotaciones "Transactional" en el DAO. Serán añadidas
 * en la clase Service.
 */
@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired // Resuelve mediante inyección las dependencias de un bean de Spring (final real)
    private IClienteDao clienteDao;

    @Autowired
    private IProductoDao productoDao;

    @Autowired
    private IFacturaDao facturaDao;

    /**
     * Realiza un listado de todos los clientes de la tabla de la base de datos
     *
     * @return lista de clientes
     */
    @Transactional(readOnly = true) // Envuelve el contenido del método en una transacción
    @Override
    public List findAll() {
        // Necesario realizar CAST, findAll() de CrudRepository devuelve un Iterable (más genérico que List)
        return (List) clienteDao.findAll();
    }

    /**
     * Devuelve una página de todos los clientes de la tabla de la base de datos
     *
     * @param pageable
     * @return página de clientes
     */
    @Override
    public Page findAll(Pageable pageable) {
        return clienteDao.findAll(pageable);
    }

    /**
     * Realiza la búsqueda de un cliente por su id
     *
     * @param id el identificador del cliente buscado
     * @return el Cliente encontrado
     */
    @Transactional(readOnly = true)
    @Override
    public Cliente findOne(Long id) {
        return clienteDao.findById(id).orElse(null);
    }

    /**
     * Obtiene un cliente con todas sus facturas (relacion con la tabla)
     * Evita la realización de colsultas perezosas (lazy) múltiples en la vista
     *
     * @param id identificador del cliente a obtener
     * @return el cliente buscado
     */
    @Override
    @Transactional(readOnly = true)
    public Cliente fetchByIdWithFacturas(Long id) {
        return clienteDao.fetchByIdWithFacturas(id);
    }

    /**
     * Guarda los datos de un clienmte en la tabla de la base de datos
     *
     * @param cliente el cliente a almacenar en la base de datos
     */
    @Transactional // No lleva readOnly porque se trata de un método de escritura
    @Override
    public void save(Cliente cliente) {
        clienteDao.save(cliente);
    }

    /**
     * Elimina un cliente de la base de datos
     *
     * @param id identificador del cliente a eliminar
     */
    @Transactional
    @Override
    public void delete(Long id) {
        clienteDao.deleteById(id);
    }

    /**
     * Realiza la búsqueda de productos por el nombre
     *
     * @param term filtro de búsqueda para el nombre
     * @return lista de productos con nombres coincidentes con el filtro de búsqueda
     */
    @Override
    @Transactional(readOnly = true)
    public List<Producto> findByNombre(String term) {
        return productoDao.findByNombreLikeIgnoreCase("%" + term + "%");
    }

    /**
     * Guarda una factura
     *
     * @param factura la factura a guardar
     */
    @Override
    @Transactional
    public void saveFactura(Factura factura) {

        facturaDao.save(factura);
    }

    /**
     * Obtiene un producto mediante su identificador
     * Nota: Los métodos del CrudRepository son transaccionales por defecto pero es buena práctica anotarlos con
     * "@Transactional" en la clase Service.
     *
     * @param id el identificador del producto buscado
     * @return el producto buscado
     */
    @Override
    @Transactional(readOnly = true)
    public Producto findProductoById(Long id) {
        return productoDao.findById(id).orElse(null);
    }

    /**
     * Obtiene una factura mediante su identificador
     *
     * @param id identificador de la factura
     * @return la factura buscada
     */
    @Override
    @Transactional(readOnly = true)
    public Factura findFacturaById(Long id) {
        return facturaDao.findById(id).orElse(null);
    }

    /**
     * Elimina una factura
     *
     * @param id identificador de la factura a eliminar
     */
    @Override
    @Transactional
    public void deleteFactura(long id) {
        facturaDao.deleteById(id); // facturaDao.deleteById(id) // SpringBoot 2
    }

    /**
     * Obtiene una factura con todas sus relaciones entre tablas
     * Evita la realización de colsultas perezosas (lazy) múltiples en la vista
     *
     * @param id identificador de la facura a obtener
     * @return la factura buscada
     */
    @Override
    @Transactional(readOnly = true)
    public Factura fetchByIdWithClienteWithItemFacturaWithProducto(Long id) {
        return facturaDao.fetchByIdWithClienteWithItemFacturaWithProducto(id);
    }

}
