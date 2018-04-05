package com.bolsadeideas.springboot.app.models.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "authority"})})
public class Rol implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authority;

    /**
     * Obtiene el identificador del rol
     *
     * @return el identificador del rol
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del rol
     *
     * @param id el identificador del rol
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del rol
     *
     * @return el nombre del rol
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * Establece el nombre del rol
     *
     * @param authority el nombre que recibe el rol
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    /**
     * Número de versión de la clase
     */
    private static final long serialVersionUID = 1L;
}
