package com.bolsadeideas.springboot.app.models.dao;

import com.bolsadeideas.springboot.app.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

    /**
     * Obtiene un usuario de la BD por su username mediante una consulta JPA
     *
     * @param username nombre del usuario
     * @return el usuario
     */
    public Usuario findByUsername(String username);
}
