package com.bolsadeideas.springboot.app.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 30)
    private String username;

    @Column(length = 60)
    private String password;

    private Boolean enable;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Role> roles;

    /**
     * Obtiene el identificador del usuario
     *
     * @return el identificador del usuario
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del usuario
     *
     * @param id el identificador del usuario
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del usuario
     *
     * @return el nombre del usuario
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario
     *
     * @param username el nombre del usuario
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene la contraseña del usuario
     *
     * @return la contraseña del usuario
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario
     *
     * @param password la contraseña del usuario
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Indica si el usuario está habilitado o deshabilitado
     *
     * @return TRUE si el usuario está habilitado, FALSE en caso contrario
     */
    public Boolean getEnable() {
        return enable;
    }

    /**
     * Establece el estado del usuario
     *
     * @param enable el estado del usuario
     */
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    /**
     * Obtiene los roles de acceso del usuario
     *
     * @return lista con los roles de acceso del usuario.
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Establece los roles de acceso para el usuario
     *
     * @param roles la lista de roles para el usuario
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * Número de versión de la clase
     */
    private static final long serialVersionUID = 1L;
}
