package com.bolsadeideas.springboot.app.models.dao;

import com.bolsadeideas.springboot.app.models.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProductoDao extends CrudRepository<Producto, Long> {

    /**
     * Realiza una consulta filtrada por el nombre del producto
     *
     * @param term es el filtro de búsqueda para el nombre
     * @return lista de productos cuyos nombres contengan a term
     */
    @Query("select p from Producto p where p.nombre like %?1%")
    List<Producto> findByNombre(String term);

    /**
     * Realiza una búsqueda filtrada por el nombre del producto
     * NOTA: Este método automáticamente realiza la busqueda filtrada ignorando mayúsculas y minúsculas por mantener
     * un estándar en el nombrado del método.
     *
     * @param term es el filtro de búsqueda para el nombre
     * @return la lista de productos cuyos mnombres contengan a term
     */
    List<Producto> findByNombreLikeIgnoreCase(String term);
}
