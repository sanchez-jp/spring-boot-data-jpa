package com.bolsadeideas.springboot.app.models.dao;

import com.bolsadeideas.springboot.app.models.entity.Factura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IFacturaDao extends CrudRepository<Factura, Long> {

    /**
     * Obtiene una factura con todas sus relaciones entre tablas.
     * Evita la realización de colsultas perezosas (lazy) múltiples en la vista
     *
     * @param id el identificador de la factura a obtener
     * @return la factura buscada
     */
    @Query("select f from Factura f join fetch f.cliente c join fetch f.items l join fetch l.producto p where f.id = ?1")
    Factura fetchByIdWithClienteWithItemFacturaWithProducto(Long id);

}
