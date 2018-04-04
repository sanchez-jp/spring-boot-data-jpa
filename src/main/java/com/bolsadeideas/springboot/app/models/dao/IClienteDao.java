package com.bolsadeideas.springboot.app.models.dao;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.List;

/**
 * La interfaz IClienteDao extiende de CrudRepository<T, Serializable>, una interface propia de SpringData que implementa
 * los métodos típicos para un CRUD en una clase DAO (de repositorio).
 */
//public interface IClienteDao extends CrudRepository<Cliente, Long> {


/**
 * La interfaz IClienteDao extiende de PagingAndSortingRepository<T, Serializable>, una interface propia de SpringData
 * que implementa los métodos típicos para un CRUD en una clase DAO (de repositorio) porque a su vez implementa
 * CrudRepository. Además implementa métodos que ayudan a la paginación de los datos obtenidos en una consulta
 */
public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long> {

    /**
     * Obtiene un cliente con todas sus facturas (relacion con la tabla)
     * Evita la realización de colsultas perezosas (lazy) múltiples en la vista
     *
     * @param id identificador del cliente a obtener
     * @return el cliente buscado
     */
    @Query("select c from Cliente c left join fetch c.facturas where c.id = ?1")
    Cliente fetchByIdWithFacturas(Long id);
}
